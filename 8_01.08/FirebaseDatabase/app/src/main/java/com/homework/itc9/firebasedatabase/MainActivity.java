package com.homework.itc9.firebasedatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private AppCompatButton saveBtn;
    private ListView listView;
    private ArrayList<String> listItems;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("test");
        editText = findViewById(R.id.input_text);
        listView = findViewById(R.id.sampleListView);
        saveBtn = findViewById(R.id.save_btn);
        listItems = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String text = editText.getText().toString();
                if (!text.isEmpty()) {
                    myRef.setValue(text);
                }
            }
        });
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String data = dataSnapshot.getValue(String.class);
                editText.setText(null);
                listItems.add(data);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }

        });
    }
}

