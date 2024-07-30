package com.example.myapplication.domain;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.model.ProductoModel;
import com.example.myapplication.model.ProductoResponse;
import com.example.myapplication.service.ProductoService;
import com.example.myapplication.utils.ConnectionRestFakeApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteProductUseCase {

    private ProductoService service;

    public DeleteProductUseCase(){
        service = ConnectionRestFakeApi.getConnection().create(ProductoService.class);
    }

    public LiveData<ProductoModel>deleteProduct(int id){
        final MutableLiveData<ProductoModel>data = new MutableLiveData<>();
        service.deleteProduct(id).enqueue(new Callback<ProductoModel>() {
            @Override
            public void onResponse(Call<ProductoModel> call, Response<ProductoModel> response) {
                if(response.isSuccessful() && response != null){
                    data.postValue(response.body());
                }
                else{
                    data.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ProductoModel> call, Throwable t) {
                    data.postValue(null);
            }
        });
        return data;
    }

}
