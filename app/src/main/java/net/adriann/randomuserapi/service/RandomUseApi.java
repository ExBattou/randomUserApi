package net.adriann.randomuserapi.service;

import net.adriann.randomuserapi.model.Response;

import retrofit2.Call;

import retrofit2.http.GET;

public interface RandomUseApi {


    @GET("api/?results=1")
    public Call<Response> get1User();

    @GET("api/?results=50")
    public Call<Response> getUsers();

}
