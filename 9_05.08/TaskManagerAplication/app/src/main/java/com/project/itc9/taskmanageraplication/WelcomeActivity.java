package com.project.itc9.taskmanageraplication;

import java.util.List;
import android.view.View;
import android.os.Bundle;
import java.util.LinkedList;
import android.widget.Button;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    private List<SimpleSliderItem> previewObjectsList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        setObjectList();
        SliderAdapter adapter = new SliderAdapter(previewObjectsList);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);
        Button btn = findViewById(R.id.btn_skip);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setObjectList() {
        previewObjectsList.add(new SimpleSliderItem(R.drawable.logo_oval,  "FIRST", "HELLO"));
        previewObjectsList.add(new SimpleSliderItem(R.drawable.logo_oval, "SECOND", "WORLD"));
    }
}
