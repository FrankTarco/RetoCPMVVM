package com.example.myapplication.domain;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.model.ItemModel;
import com.example.myapplication.service.CandyService;
import com.example.myapplication.utils.ConnectionRest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetProductUseCase {

    private CandyService service;

    public GetProductUseCase(){
        service = ConnectionRest.getConnection().create(CandyService.class);
    }

    public LiveData<ItemModel>getProducts(){

        final MutableLiveData<ItemModel> data = new MutableLiveData<>();
        service.lstProducts().enqueue(new Callback<ItemModel>() {
            @Override
            public void onResponse(Call<ItemModel> call, Response<ItemModel> response) {
                if(response.isSuccessful() && response.body() != null){
                    data.postValue(response.body());
                }
                else{
                    data.postValue(new ItemModel());
                }
            }

            @Override
            public void onFailure(Call<ItemModel> call, Throwable t) {
                data.postValue(new ItemModel());
            }
        });

        return data;
    }

}
