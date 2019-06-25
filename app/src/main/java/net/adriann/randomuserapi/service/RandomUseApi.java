package net.adriann.randomuserapi.service;

import retrofit2.http.GET;

public interface RandomUseApi {


    @GET("api/?results=1")
    public Call<UserResponse> get1User();

    @GET("api/?results=50")
    public Call<UserResponse> getUsers();

}
