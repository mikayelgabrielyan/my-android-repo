package com.project.itc9.taskmanageraplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterOfTaskList extends RecyclerView.Adapter<AdapterOfTaskList.TaskViewHolder> {

    private List<SimpleTaskItem> mTasksList;
    private Context mContext;

    public AdapterOfTaskList(List<SimpleTaskItem> mTasksList, Context mContext) {
        this.mTasksList = mTasksList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public AdapterOfTaskList.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOfTaskList.TaskViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mTasksList.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView mTaskImage;
        private TextView mTaskTitle;
        private TextView mTaskDescription;
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            mTaskImage = itemView.findViewById(R.id.task_image);
            mTaskTitle = itemView.findViewById(R.id.task_title);
            mTaskDescription = itemView.findViewById(R.id.task_description);
        }
        public void bind(int position) {
            SimpleTaskItem simpleTaskItem = mTasksList.get(position);
            mTaskTitle.setText(TasksListFragment.mTaskItemList.get(position).getmTaskTitle());
            mTaskDescription.setText(TasksListFragment.mTaskItemList.get(position).getmTaskDescription());
            Picasso.get().load(simpleTaskItem.getmTaskImageUrl()).into(mTaskImage);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    RegistrationFragment registrationFragment = new RegistrationFragment();
//                    FragmentManager manager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
//                    FragmentTransaction transaction = manager.beginTransaction();
//                    transaction.replace(R.id.m_container, registrationFragment);
//                    transaction.addToBackStack("SIGNIN_FRAGMENT");
//                    transaction.commit();
//                }
//            });
        }
    }
}
