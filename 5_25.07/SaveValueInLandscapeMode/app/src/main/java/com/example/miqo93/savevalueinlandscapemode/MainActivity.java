package com.example.miqo93.savevalueinlandscapemode;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private static Integer count = 0;
    private static final String MY_KEY = "key1";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textView);
        mTextView.setText(count.toString());
        final Button btnMax = findViewById(R.id.button1);
        final Button btnMin = findViewById(R.id.button2);
        btnMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++count;
                mTextView.setText(count.toString());
            }
        });
        btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                --count;
                mTextView.setText(count.toString());
            }
        });
        if (savedInstanceState != null) {
            count = Integer.parseInt(savedInstanceState.getString(MY_KEY));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(MY_KEY, mTextView.getText().toString());
    }
}
