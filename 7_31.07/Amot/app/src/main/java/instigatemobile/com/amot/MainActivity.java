package instigatemobile.com.amot;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MainActivity extends AppCompatActivity {

//    private Button buttonPhone;
//    private Button buttonMail;
//    private Button buttonActivity;
//    private ImageView imageView;
    private static final String IMAGEURL  =
            "https://www.gettyimages.ie/gi-resources/images/" +
                    "Homepage/Hero/UK/CMS_Creative_164657191_Kingfisher.jpg";
    public static final String KEY = "KEY";

    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView recyclerView = findViewById(R.id.RView);
        recyclerView.setLayoutManager(layoutManager);
        DataProvider.fillInitialData();
        List<User> users = DataProvider.users;
        adapter = new MyAdapter(users, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchView search = (SearchView) menu.findItem(R.id.action_search).getActionView();
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}

//    private void openImage() {
//        imageView = findViewById(R.id.userImage);
//        Picasso.get().load(IMAGEURL).into(imageView);
//    }
//
//
//    private void openActivity() {
//        buttonActivity = findViewById(R.id.buttonActivity);
//        buttonActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, ScrollingActivity.class);
//                intent.putExtra(KEY, IMAGEURL);
//                startActivity(intent);
//            }
//        });
//    }

//    private void openMail() {
//        openPhone();
//        buttonMail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_SEND);
//                intent.setType("text/html");
//                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"stepanchaparyan@example.com"});
//                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject of this mail");
//                intent.putExtra(Intent.EXTRA_TEXT, "This is simple text");
//                startActivity(Intent.createChooser(intent, "Send Mail"));
//            }
//        });
//    }
//
//    private void openPhone() {
//        buttonPhone = findViewById(R.id.userPhone);
//        buttonPhone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:0988004177"));
//                startActivity(intent);
//            }
//        });
//    }


