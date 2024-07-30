package com.example.myapplication.domain;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.model.ProductoRequest;
import com.example.myapplication.model.ProductoResponse;
import com.example.myapplication.service.ProductoService;
import com.example.myapplication.utils.ConnectionRestFakeApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProductUseCase {

    private ProductoService service;

    public UpdateProductUseCase(){
        service = ConnectionRestFakeApi.getConnection().create(ProductoService.class);
    }

    public LiveData<ProductoResponse> updateProd(ProductoRequest obj,int codigo){
        final MutableLiveData<ProductoResponse> data = new MutableLiveData<>();
        service.updateProduct(codigo,obj).enqueue(new Callback<ProductoResponse>() {
            @Override
            public void onResponse(Call<ProductoResponse> call, Response<ProductoResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    data.postValue(response.body());
                }
                else{
                    data.postValue(null);
                }
            }
            @Override
            public void onFailure(Call<ProductoResponse> call, Throwable t) {
                data.postValue(null);
            }
        });
        return data;
    }


}
