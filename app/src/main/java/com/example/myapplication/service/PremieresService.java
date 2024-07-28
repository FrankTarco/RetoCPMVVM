package com.example.myapplication.service;

import com.example.myapplication.model.PremierModel;
import com.example.myapplication.model.PremieresModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PremieresService {

    @GET("premieres")
    public Call<PremieresModel> listado();
}
