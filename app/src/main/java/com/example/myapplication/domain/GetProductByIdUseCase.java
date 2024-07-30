package com.example.myapplication.domain;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.model.ProductoModel;
import com.example.myapplication.service.ProductoService;
import com.example.myapplication.utils.ConnectionRestFakeApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetProductByIdUseCase {

    private ProductoService service;

    public GetProductByIdUseCase(){
        service = ConnectionRestFakeApi.getConnection().create(ProductoService.class);
    }

    public LiveData<ProductoModel> getProductById(int id) {
        final MutableLiveData<ProductoModel> data = new MutableLiveData<>();
        service.getFindByIdProduct(id).enqueue(new Callback<ProductoModel>() {
            @Override
            public void onResponse(Call<ProductoModel> call, Response<ProductoModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    data.postValue(response.body());
                } else {
                    data.postValue(null); // Manejar caso de error
                }
            }

            @Override
            public void onFailure(Call<ProductoModel> call, Throwable t) {
                data.postValue(null); // Manejar fallo
            }
        });

        return data;
    }

}
