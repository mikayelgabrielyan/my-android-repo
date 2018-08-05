package com.homework.itc9.firebasedatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private AppCompatButton saveBtn;
    private ListView listView;
    private ArrayList<String> listItems;
    private ArrayAdapter<String> adapter;
    final List<String> strKeys = new ArrayList<>();
    final List<String> strVals = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.input_text);
        DatabaseReference zonesRef = FirebaseDatabase.getInstance().getReference("test");
        DatabaseReference zone1Ref = zonesRef.child("zone");
        DatabaseReference zone2Ref = zone1Ref.child("itc");
        zonesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot i: dataSnapshot.getChildren()) {
                    strKeys.add(String.valueOf(i.getKey()));
                    editText.setText(String.valueOf(strKeys.size()));

//                    strVals.add(String.valueOf(i.getValue()));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Canceled", Toast.LENGTH_SHORT);
            }
        });

//        final FirebaseDatabase database = FirebaseDatabase.getInstance();
//        final DatabaseReference myRef = database.getReference("test");
//        editText = findViewById(R.id.input_text);
//        listView = findViewById(R.id.sampleListView);
//        saveBtn = findViewById(R.id.save_btn);
//        listItems = new ArrayList<>();
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
//        listView.setAdapter(adapter);
//        saveBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final String text = editText.getText().toString();
//                if (!text.isEmpty()) {
//                    myRef.setValue(text);
//                }
//            }
//        });
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String data = dataSnapshot.getValue(String.class);
//                editText.setText(null);
//                listItems.add(data);
//                adapter.notifyDataSetChanged();
//            }
//            @Override
//            public void onCancelled(DatabaseError error) {
//            }
//
//        });





    }
}

