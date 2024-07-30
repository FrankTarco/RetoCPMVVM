package com.example.myapplication.service;

import com.example.myapplication.model.ProductoModel;
import com.example.myapplication.model.ProductoRequest;
import com.example.myapplication.model.ProductoResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductoService {

    @GET("products")
    public Call<List<ProductoModel>> getAllProducts();

    @GET("products/{id}")
    public Call<ProductoModel>getFindByIdProduct(@Path("id") int id);

    @GET("products")
    public Call<ProductoModel>getLimitProducts(@Query("limit") int limit);

    @POST("products")
    public Call<ProductoResponse>insertProduct(@Body ProductoRequest obj);

    @PUT("products/{id}")
    public Call<ProductoResponse>updateProduct(@Path("id") int id, @Body ProductoRequest obj);

    @DELETE("products/{id}")
    public Call<ProductoModel>deleteProduct(@Path("id") int id);



}
