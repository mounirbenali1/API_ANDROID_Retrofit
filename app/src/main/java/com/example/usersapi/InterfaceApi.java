package com.example.usersapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface InterfaceApi {
    @GET("users/{id}") // get the user with id n
    public Call<JsonDataHolder> getUsers(@Path("id") int userId);
    @GET("users/")
    public Call<JsonDataHolderAll> getAll();
}
