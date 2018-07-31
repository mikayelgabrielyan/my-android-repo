package instigatemobile.com.amot;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<User> userList;
    private Context context;

    public MyAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.userdata, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView imageView;
        private TextView userName;
        private TextView userDes;
        private ImageButton userPhone;
        private ImageButton userEmail;
        private ImageButton userDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.userImage);
            userName = itemView.findViewById(R.id.userName);
            userDes = itemView.findViewById(R.id.description);
            userPhone = itemView.findViewById(R.id.userPhone);
            userEmail = itemView.findViewById(R.id.userMail);
            userDelete = itemView.findViewById(R.id.userDelete);
        }

        void bind(int position) {
            final User user = userList.get(position);
            userName.setText(user.getName());
            userDes.setText(user.getDescription());
            Picasso.get().load(user.getImageURL()).into(imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ScrollingActivity.class);
                    context.startActivity(intent);
                }
            });
            userEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/html");
                    intent.putExtra(Intent.EXTRA_EMAIL, user.getEmailAdress());
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Subject of this mail");
                    intent.putExtra(Intent.EXTRA_TEXT, "This is simple text");
                    context.startActivity(Intent.createChooser(intent, "Send Mail"));
                }
            });
            userPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse(user.getPhoneNumber()));
                    context.startActivity(intent);
                }
            });
            userDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
