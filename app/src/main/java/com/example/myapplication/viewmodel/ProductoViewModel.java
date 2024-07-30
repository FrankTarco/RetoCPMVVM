package com.example.myapplication.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.domain.DeleteProductUseCase;
import com.example.myapplication.domain.GetAllProducsUseCase;
import com.example.myapplication.domain.GetProductByIdUseCase;
import com.example.myapplication.domain.SaveProductUseCase;
import com.example.myapplication.model.ProductoModel;
import com.example.myapplication.model.ProductoRequest;
import com.example.myapplication.model.ProductoResponse;

import java.util.ArrayList;
import java.util.List;

public class ProductoViewModel extends ViewModel {

    //LIVECYCLE
    private MutableLiveData<List<ProductoModel>> lstProductos;
    private MutableLiveData<ProductoResponse>productoRegistrado;
    private MutableLiveData<ProductoModel>productoEliminado;

    //USE CASE
    private SaveProductUseCase saveProduct;
    private GetAllProducsUseCase useCase;
    private GetProductByIdUseCase productByIdUseCase;
    private DeleteProductUseCase deleteUseCase;

    public ProductoViewModel(){
        useCase = new GetAllProducsUseCase();
        deleteUseCase = new DeleteProductUseCase();
        productByIdUseCase = new GetProductByIdUseCase();
        productoRegistrado = new MutableLiveData<>();
        productoEliminado = new MutableLiveData<>();
        saveProduct = new SaveProductUseCase();
        lstProductos = useCase.getList();
    }

    public LiveData<List<ProductoModel>>getListProducts(){
        return lstProductos;
    }

    public LiveData<ProductoResponse>getProductoInsert(){
        return productoRegistrado;
    }
    public LiveData<ProductoModel>getProductEliminated(){
        return productoEliminado;
    }

    public void deleteProduct(int codigo){
        deleteUseCase.deleteProduct(codigo).observeForever(new Observer<ProductoModel>() {
            @Override
            public void onChanged(ProductoModel productoModel) {
                if(productoModel != null){
                    productoEliminado.postValue(productoModel);
                }
                productoEliminado.removeObserver(this);
            }
        });
    }

    public void insertProduct(ProductoRequest obj){
        LiveData<ProductoResponse>addProduct = saveProduct.registerProd(obj);
        addProduct.observeForever(new Observer<ProductoResponse>() {
            @Override
            public void onChanged(ProductoResponse productoResponse) {
                if(productoResponse != null){
                    productoRegistrado.postValue(productoResponse);
                }
                addProduct.removeObserver(this);
            }
        });
    }

    public void setListProducts(){
        LiveData<List<ProductoModel>>productAllLiveData = useCase.getList();
        productAllLiveData.observeForever(new Observer<List<ProductoModel>>() {
            @Override
            public void onChanged(List<ProductoModel> productoModels) {
                if(productoModels != null){
                    lstProductos.postValue(productoModels);
                }
                productAllLiveData.removeObserver(this);
            }
        });
    }

    public void setListProductsById(int id) {
        LiveData<ProductoModel> productLiveData = productByIdUseCase.getProductById(id);

        productLiveData.observeForever(new Observer<ProductoModel>() {
            @Override
            public void onChanged(ProductoModel productoModel) {
                if (productoModel != null) {
                    List<ProductoModel> currentList = new ArrayList<>();
                    currentList.add(productoModel);
                    lstProductos.setValue(currentList);
                }
                productLiveData.removeObserver(this);
            }
        });
    }
}
