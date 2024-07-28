package com.example.myapplication.domain;

import com.example.myapplication.model.ProductModel;

import java.util.List;

public class GetTotalPrice {

    public static double getTotal(List<ProductModel> lst){
        double mount = 0.0;
        if(lst != null){
            for(ProductModel item: lst){
                if(item != null){
                    mount += item.totalPagar();
                }
            }
        }
        return mount;
    }

}
