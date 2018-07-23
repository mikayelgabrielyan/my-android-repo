package com.example.miqo93.passbackchangedvalue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {
    private EditText secondEditText;
    private Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        secondEditText = findViewById(R.id.secondEditText);
        goBack = findViewById(R.id.goBackBtn);
        secondEditText.setText(getIntent().getStringExtra(MainActivity.MY_KEY));
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(MainActivity.MY_KEY, secondEditText.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

}
