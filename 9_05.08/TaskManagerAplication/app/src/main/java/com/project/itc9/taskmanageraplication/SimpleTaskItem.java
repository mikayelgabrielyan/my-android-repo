package com.project.itc9.taskmanageraplication;

public class SimpleTaskItem {
    private String mTaskTitle;
    private String mTaskDescription;
    private String mTaskImageUrl;
    private String mTaskDate;
    private String mTaskTime;

    public SimpleTaskItem(String mTaskTitle, String mTaskDescription, String mTaskImageUrl) {
        this.mTaskTitle = mTaskTitle;
        this.mTaskDescription = mTaskDescription;
        this.mTaskImageUrl = mTaskImageUrl;
    }

    public SimpleTaskItem() {
    }

    public String getmTaskTitle() {
        return mTaskTitle;
    }

    public void setmTaskTitle(String mTaskTitle) {
        this.mTaskTitle = mTaskTitle;
    }

    public String getmTaskDescription() {
        return mTaskDescription;
    }

    public void setmTaskDescription(String mTaskDescription) {
        this.mTaskDescription = mTaskDescription;
    }

    public String getmTaskImageUrl() {
        return mTaskImageUrl;
    }

    public void setmTaskImageUrl(String mTaskImageUrl) {
        this.mTaskImageUrl = mTaskImageUrl;
    }

    public String getmTaskDate() {
        return mTaskDate;
    }

    public void setmTaskDate(String mTaskDate) {
        this.mTaskDate = mTaskDate;
    }

    public String getmTaskTime() {
        return mTaskTime;
    }

    public void setmTaskTime(String mTaskTime) {
        this.mTaskTime = mTaskTime;
    }
}
