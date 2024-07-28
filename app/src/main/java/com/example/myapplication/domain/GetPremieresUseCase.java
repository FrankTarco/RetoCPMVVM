package com.example.myapplication.domain;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.model.PremierModel;
import com.example.myapplication.model.PremieresModel;
import com.example.myapplication.service.PremieresService;
import com.example.myapplication.utils.ConnectionRest;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetPremieresUseCase {

    private PremieresService service;

    public GetPremieresUseCase(){
        service = ConnectionRest.getConnection().create(PremieresService.class);
    }

    public LiveData<PremieresModel> getList(){
        final MutableLiveData<PremieresModel> data = new MutableLiveData<>();

        service.listado().enqueue(new Callback<PremieresModel>() {
            @Override
            public void onResponse(Call<PremieresModel> call, Response<PremieresModel> response) {
                if(response.isSuccessful() && response.body() != null){
                    data.postValue(response.body());
                }
                else {
                    data.postValue(new PremieresModel());
                }
            }

            @Override
            public void onFailure(Call<PremieresModel> call, Throwable t) {
                data.postValue(new PremieresModel());
            }
        });
        return data;
    }

}
