package com.example.miqo93.passbackchangedvalue;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {
    private EditText secondEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        secondEditText = findViewById(R.id.secondEditText);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        secondEditText.setText(MainActivity.editText.getText().toString());
        Intent intent = new Intent();
        intent.putExtra("MY_KEY", secondEditText.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
