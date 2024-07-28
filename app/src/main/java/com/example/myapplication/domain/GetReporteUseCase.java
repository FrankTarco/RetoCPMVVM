package com.example.myapplication.domain;

import com.example.myapplication.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class GetReporteUseCase {

    public static List<ProductModel>getCompras(List<ProductModel>data){
        List<ProductModel> salida = new ArrayList<>();
        if(data!=null){
            for (ProductModel pm: data){
                if(pm.getQuantity() > 0){
                    salida.add(pm);
                }
            }
        }
        return salida;
    }
}
