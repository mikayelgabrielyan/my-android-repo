package com.project.itc9.retrofitgsonapp.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {
    @GET("/api/")
    Call<ApiResponse> fetchUsers(@Query("results") int results);
}