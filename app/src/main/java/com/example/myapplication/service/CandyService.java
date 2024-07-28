package com.example.myapplication.service;

import com.example.myapplication.model.ItemModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CandyService {

    @GET("candystore")
    public Call<ItemModel> lstProducts();

}
