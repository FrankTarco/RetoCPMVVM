package com.example.myapplication.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.domain.GetProductUseCase;
import com.example.myapplication.domain.GetReporteUseCase;
import com.example.myapplication.domain.GetTotalPrice;
import com.example.myapplication.model.ItemModel;
import com.example.myapplication.model.ProductModel;

import java.util.List;

public class DulceriaViewModel extends ViewModel {

    private GetProductUseCase getProduct;
    private LiveData<ItemModel> data;
    private MutableLiveData<Double>totalPagar;
    private MutableLiveData<List<ProductModel>> reporte;

    public DulceriaViewModel(){
        getProduct = new GetProductUseCase();
        data = getProduct.getProducts();
        totalPagar = new MutableLiveData<>(0.0);
        reporte = new MutableLiveData<>();
    }

    public LiveData<List<ProductModel>> getRep(){
        return reporte;
    }

    public void setReporte(){
        reporte.postValue(GetReporteUseCase.getCompras(data.getValue().getItems()));
    }

    public LiveData<Double>getTotal(){
        return totalPagar;
    }

    public void setTotal(){
        totalPagar.setValue(GetTotalPrice.getTotal(data.getValue().getItems()));
    }

    public LiveData<ItemModel>getList(){
        return data;
    }

}
