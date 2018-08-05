package com.project.itc9.taskmanageraplication;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Random;


import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class TasksListFragment extends Fragment implements View.OnClickListener {
    public static List<SimpleTaskItem> mTaskItemList = new ArrayList<>();
    private Context mContext;
    public final static List<String> mTitleList = new ArrayList<>();
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
    private DatabaseReference tasksRef;
    private String userId;
    private static int mCount = 1;

    public TasksListFragment() {
    }

    @SuppressLint("ValidFragment")
    public TasksListFragment(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasks_list, container, false);
        findElements(view);
        mAuth = FirebaseAuth.getInstance();
        userId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        recyclerView = view.findViewById(R.id.rv_task_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        tasksRef = FirebaseDatabase.getInstance().getReference();
            showTasks();
//        tasksRef.child(userId).child("tasks").child("Errord task").removeValue();

        //        mTaskElements.add("title");
//        mTaskElements.add("description");
//        mTaskElements.add("image");
//        mTitleList.add("fdvhjxsrfv");
//        mTitleList.add("gfrrfghjjjhvffff");
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 1);
//        recyclerView = view.findViewById(R.id.rv_task_list);
//        recyclerView.setLayoutManager(gridLayoutManager);
//        tasksRef = FirebaseDatabase.getInstance().getReference("tasks");
//        for(int i = 0; i < mTitleList.size(); ++i){
//            tasksRef.child(mTitleList.get(i)).addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    for(DataSnapshot i: dataSnapshot.getChildren()) {
//                        SimpleTaskItem item = i.getValue(SimpleTaskItem.class);
//                        mTaskItemList.add(item);
//                        AdapterOfTaskList adapter = new AdapterOfTaskList(mTaskItemList, mContext);
//                        recyclerView.setAdapter(adapter);
//                    }
//                    AdapterOfTaskList adapter = new AdapterOfTaskList(mTaskItemList, mContext);
//                    recyclerView.setAdapter(adapter);
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//            });
//        }
        return view;
    }

    private void showTasks() {
        tasksRef.child(userId).child("tasks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot i : dataSnapshot.getChildren()) {
                    SimpleTaskItem newItem = i.getValue(SimpleTaskItem.class);
                    mTaskItemList.add(newItem);
                }
                AdapterOfTaskList adapter = new AdapterOfTaskList(mTaskItemList, mContext);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showNewTask(String newRef) {
        tasksRef.child(userId).child("tasks").child(newRef).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                SimpleTaskItem newItem = dataSnapshot.getValue(SimpleTaskItem.class);
                mTaskItemList.add(newItem);
                AdapterOfTaskList newAdapter = new AdapterOfTaskList(mTaskItemList, mContext);
                recyclerView.setAdapter(newAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void findElements(View view) {
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mTeskImage = view.findViewById(R.id.dialog_image);
        dialogLinearLayout = view.findViewById(R.id.custom_dialog);
        mAddTaskBtn = view.findViewById(R.id.add_task_btn);
        mChooseImageBtn = view.findViewById(R.id.dialog_btn_img);
        mChooseDateBtn = view.findViewById(R.id.dialog_date);
        mChooseTimeBtn = view.findViewById(R.id.dialog_time);
        mSaveBtn = view.findViewById(R.id.dialog_save);
        mCancelBtn = view.findViewById(R.id.dialog_cancel);
        mTitleTV = view.findViewById(R.id.dialog_title);
        mDescriptionTV = view.findViewById(R.id.dialog_description);
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
                addNewTask(view);
                break;
            case R.id.dialog_btn_img:
                chooseImage(view);
                break;
            case R.id.dialog_date:
                chooseDate(view);
                break;
            case R.id.dialog_time:
                chooseTime(view);
                break;
            case R.id.dialog_save:
                saveTask(view);
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

    private void saveTask(View view) {
        String titleStr = String.valueOf(mTitleTV.getText());
        String descStr = String.valueOf(mDescriptionTV.getText());
        if(titleStr.isEmpty() || descStr.isEmpty() || simpleTaskItem.getmTaskDate().isEmpty() ||
                simpleTaskItem.getmTaskTime().isEmpty()) {
            Toast.makeText(mContext, "Dont selected all ", Toast.LENGTH_LONG);
        } else {
            simpleTaskItem.setmTaskTitle(titleStr);
            simpleTaskItem.setmTaskDescription(descStr);
            simpleTaskItem.setmTaskImageUrl(downloadUrl);
            Toast.makeText(mContext, "uri: " + downloadUrl, Toast.LENGTH_LONG);
            mTitleList.add(simpleTaskItem.getmTaskTitle());
            showNewTask(titleStr);
        }
        dialogLinearLayout.setVisibility(View.GONE);
        mAddTaskBtn.setActivated(true);
        dialogLinearLayout.setActivated(true);
    }

    private void chooseTime(View view) {
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(mContext,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        simpleTaskItem.setmTaskTime(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    private void chooseDate(View view) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        simpleTaskItem.setmTaskDate(dayOfMonth + "." + (monthOfYear + 1) + "." + year);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void chooseImage(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    private void addNewTask(View view) {
        simpleTaskItem = new SimpleTaskItem();
        dialogLinearLayout.setVisibility(View.VISIBLE);
        mAddTaskBtn.setActivated(false);
        dialogLinearLayout.setActivated(false);
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            final Uri imageUri = data.getData();
            Random random = new Random();
            String id = "1image" + String.valueOf(random.nextInt()); // generate random string
            final StorageReference riversRef = mStorageRef.child(id);
            mTeskImage.setVisibility(View.VISIBLE);
            mTeskImage.setImageURI(imageUri);
            riversRef.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            downloadUrl =  taskSnapshot.getStorage().getDownloadUrl().toString();
                            Toast.makeText(mContext, "Posted", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            Toast.makeText(mContext, "Failed", Toast.LENGTH_LONG).show();
                        }
                    });
        } else {
            Toast.makeText(mContext, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }
}
