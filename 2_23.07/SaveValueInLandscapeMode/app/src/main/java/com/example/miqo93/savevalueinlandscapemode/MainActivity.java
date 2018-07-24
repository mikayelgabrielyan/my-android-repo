package com.example.miqo93.savevalueinlandscapemode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    protected EditText mEditText;
    private static TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = findViewById(R.id.editText);
        mTextView = findViewById(R.id.textView);
    }

    public void btnClick(View view) {
        mTextView.setText(mEditText.getText().toString());
        float size = mTextView.getTextSize();
        switch (view.getId()) {
            case R.id.button1:
                size -= 1.0f;
                mTextView.setTextSize(size);
                break;
            case R.id.button2:
                size -= 1.0f;
                mTextView.setTextSize(size);
                break;
        }
    }

}
