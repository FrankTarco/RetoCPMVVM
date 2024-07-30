package com.example.myapplication.domain;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.model.ProductoModel;
import com.example.myapplication.service.ProductoService;
import com.example.myapplication.utils.ConnectionRest;
import com.example.myapplication.utils.ConnectionRestFakeApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAllProducsUseCase {

    private ProductoService service;

    public GetAllProducsUseCase(){
        service = ConnectionRestFakeApi.getConnection().create(ProductoService.class);
    }

    public MutableLiveData<List<ProductoModel>>getList(){
        final MutableLiveData<List<ProductoModel>>data = new MutableLiveData<>();

        service.getAllProducts().enqueue(new Callback<List<ProductoModel>>() {
            @Override
            public void onResponse(Call<List<ProductoModel>> call, Response<List<ProductoModel>> response) {
                if(response.isSuccessful() && response.body()!=null){
                    data.postValue(response.body());
                }
                else{
                    data.postValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<List<ProductoModel>> call, Throwable t) {
                data.postValue(new ArrayList<>());
            }
        });
        return  data;
    }

}
