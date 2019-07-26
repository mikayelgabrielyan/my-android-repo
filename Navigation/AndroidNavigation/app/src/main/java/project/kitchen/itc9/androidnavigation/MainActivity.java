package project.kitchen.itc9.androidnavigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FragmentManager fm = getSupportFragmentManager();
        final BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        fm.beginTransaction().replace(R.id.container, new FirstFragment()).commit();
                        return true;
                    case R.id.navigation_dashboard:
                        fm.beginTransaction().replace(R.id.container, new SecondFragment()).commit();
                        return true;
                    case R.id.navigation_notifications:
                        fm.beginTransaction().replace(R.id.container, new ThreedFragment()).commit();
                        return true;
                }
                return false;
            }
        });


    }

}
