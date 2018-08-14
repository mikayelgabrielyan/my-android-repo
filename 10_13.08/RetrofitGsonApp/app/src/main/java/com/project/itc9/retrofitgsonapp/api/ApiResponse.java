package com.project.itc9.retrofitgsonapp.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.itc9.retrofitgsonapp.UserModel;
import java.util.List;

public class ApiResponse {

    @SerializedName("results")
    @Expose
    private List<UserModel> users = null;

    public List<UserModel> getResults() {
        return users;
    }

    public void setResults(List<UserModel> results) {
        this.users = results;
    }
}