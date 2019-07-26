package project.homework.itc9.tabbednavigationbar;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.container);
        mySetAdapter(viewPager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("FIRST");
        tabLayout.getTabAt(1).setText("SECOND");
        tabLayout.getTabAt(2).setText("THREED");

    }

    public void mySetAdapter(ViewPager viewPager) {
        CustomAdapter customAdapter = new CustomAdapter(getSupportFragmentManager());
        customAdapter.addFragment(new FirstFragment());
        customAdapter.addFragment(new SecondFragment());
        customAdapter.addFragment(new ThreesFragment());
        viewPager.setAdapter(customAdapter);
    }
}
