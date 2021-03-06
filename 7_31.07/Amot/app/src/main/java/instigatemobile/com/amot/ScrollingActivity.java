package instigatemobile.com.amot;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ScrollingActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final int mValue = getIntent().getIntExtra(MyAdapter.KEY, -1);
        ImageView imageView = findViewById(R.id.scrollingImage);
        TextView textView = findViewById(R.id.scr_user_name);
        user = DataProvider.users.get(mValue);
        textView.setText(user.getName());
        Picasso.get().load(user.getImageURL()).into(imageView);
        btnClick();
    }

    private void btnClick() {
        FloatingActionButton userPhone = findViewById(R.id.scr_phone);
        FloatingActionButton userEmail = findViewById(R.id.scr_email);
        userEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL,new String[]{user.getEmailAdress()});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject of this mail");
                intent.putExtra(Intent.EXTRA_TEXT, "This is simple text");
                startActivity(Intent.createChooser(intent, "Send Mail"));
            }
        });
        userPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+ user.getPhoneNumber()));
                startActivity(intent);
            }
        });
    }
}
