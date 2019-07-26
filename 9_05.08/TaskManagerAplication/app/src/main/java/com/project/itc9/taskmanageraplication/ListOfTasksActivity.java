package com.project.itc9.taskmanageraplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListOfTasksActivity extends AppCompatActivity implements View.OnClickListener{
    public static List<SimpleTaskItem> mTaskItemList = new ArrayList<>();
    public static List<String> mTitleList = new ArrayList<>();
    private int mYear, mMonth, mDay, mHour, mMinute;
    private StorageReference mStorageRef;
    private LinearLayout dialogLinearLayout;
    private CircleImageView mTeskImage;
    private FloatingActionButton mAddTaskBtn;
    private Button mChooseImageBtn, mChooseDateBtn, mChooseTimeBtn, mSaveBtn, mCancelBtn;
    private EditText mTitleTV;
    private EditText mDescriptionTV;
    private SimpleTaskItem simpleTaskItem;
    private String downloadUrl;
    private FirebaseAuth mAuth;
    private RecyclerView recyclerView;
    private DatabaseReference tasksRef = FirebaseDatabase.getInstance().getReference();
    private String userId;
    private AdapterOfTaskList adapter;
    private static int mCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_tasks);
        simpleTaskItem = new SimpleTaskItem();
        findElements();
        mAuth = FirebaseAuth.getInstance();
        userId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        recyclerView = findViewById(R.id.rv_task_list);
        adapter = new AdapterOfTaskList(mTaskItemList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        showTasks();
    }

    private void showTasks() {
        int size = mTaskItemList.size();
        if(size > 0) {
            for (int i = size-1; i >= 0; --i){
                mTaskItemList.remove(i);
            }
        }
        tasksRef.child(userId).child("tasks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot i : dataSnapshot.getChildren()) {
                    SimpleTaskItem newItem = i.getValue(SimpleTaskItem.class);
                    mTaskItemList.add(newItem);
                }
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void findElements() {
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mTeskImage = findViewById(R.id.dialog_image);
        dialogLinearLayout = findViewById(R.id.custom_dialog);
        mAddTaskBtn = findViewById(R.id.add_task_btn);
        mChooseImageBtn = findViewById(R.id.dialog_btn_img);
        mChooseDateBtn = findViewById(R.id.dialog_date);
        mChooseTimeBtn = findViewById(R.id.dialog_time);
        mSaveBtn = findViewById(R.id.dialog_save);
        mCancelBtn = findViewById(R.id.dialog_cancel);
        mTitleTV = findViewById(R.id.dialog_title);
        mDescriptionTV = findViewById(R.id.dialog_description);
        mCancelBtn.setOnClickListener(this);
        mAddTaskBtn.setOnClickListener(this);
        mChooseDateBtn.setOnClickListener(this);
        mChooseImageBtn.setOnClickListener(this);
        mChooseTimeBtn.setOnClickListener(this);
        mSaveBtn.setOnClickListener(this);
        mTitleTV.setOnClickListener(this);
        mDescriptionTV.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_task_btn:
                addNewTask();
                break;
            case R.id.dialog_btn_img:
                chooseImage();
                break;
            case R.id.dialog_date:
                chooseDate();
                break;
            case R.id.dialog_time:
                chooseTime();
                break;
            case R.id.dialog_save:
                saveTask();
                break;
            case R.id.dialog_cancel:
                cancelTask();
        }
    }

    private void cancelTask() {
        dialogLinearLayout.setVisibility(View.GONE);
        mAddTaskBtn.setActivated(true);
        dialogLinearLayout.setActivated(true);
        mTitleTV.setText("");
        mDescriptionTV.setText("");
        mTeskImage.setVisibility(View.GONE);
    }

    private void saveTask() {
        String titleStr = String.valueOf(mTitleTV.getText());
        String descStr = String.valueOf(mDescriptionTV.getText());
        if(titleStr.isEmpty() || descStr.isEmpty() || simpleTaskItem.getmTaskDate().isEmpty() ||
                simpleTaskItem.getmTaskTime().isEmpty()) {
            Toast.makeText(this, "Dont selected all ", Toast.LENGTH_LONG);
        } else {
            simpleTaskItem.setmTaskTitle(titleStr);
            simpleTaskItem.setmTaskDescription(descStr);
            Toast.makeText(this, "uri: " + downloadUrl, Toast.LENGTH_LONG);
            mTitleList.add(simpleTaskItem.getmTaskTitle());
            simpleTaskItem.setmTaskImageUrl("https://firebasestorage.googleapis.com/v0/b/taskmanager-1o1.appspot.com/o/image1?alt=media&token=322a3750-050b-4c09-9b70-f450b9bbfc87");
            mTaskItemList.add(simpleTaskItem);
            tasksRef.child(userId).child("tasks").child(titleStr).setValue(simpleTaskItem);
            showTasks();
        }
        dialogLinearLayout.setVisibility(View.GONE);
        mAddTaskBtn.setActivated(true);
        dialogLinearLayout.setActivated(true);
    }

    private void chooseTime() {
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        simpleTaskItem.setmTaskTime(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    private void chooseDate() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        simpleTaskItem.setmTaskDate(dayOfMonth + "." + (monthOfYear + 1) + "." + year);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void chooseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    private void addNewTask() {
        dialogLinearLayout.setVisibility(View.VISIBLE);
        mAddTaskBtn.setActivated(false);
        dialogLinearLayout.setActivated(false);
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                String id = "1image" + mCount;
                final StorageReference riversRef = mStorageRef.child(id);
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                mTeskImage.setVisibility(View.VISIBLE);
                mTeskImage.setImageBitmap(selectedImage);
                riversRef.putFile(imageUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // Get a URL to the uploaded content
//                                downloadUrl =  taskSnapshot.getDownloadUrl();
                                simpleTaskItem.setmTaskImageUrl(downloadUrl);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle unsuccessful uploads
                            }
                        });
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }
}
