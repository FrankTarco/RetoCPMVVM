package com.example.myapplication;

public class CalcularUseCase {

    public static String alCuadrado(String data){
        return String.valueOf(Double.parseDouble(data) * Double.parseDouble(data));
    }


}
