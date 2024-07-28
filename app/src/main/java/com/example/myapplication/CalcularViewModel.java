package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalcularViewModel extends ViewModel {

    private MutableLiveData<String> resultado;

    CalcularViewModel(){
        resultado = new MutableLiveData<>();
    }

    public LiveData<String> getResultado(){
        return resultado;
    }

    public void alCuadrado(String data){
        resultado.setValue(CalcularUseCase.alCuadrado(data));
    }

}
