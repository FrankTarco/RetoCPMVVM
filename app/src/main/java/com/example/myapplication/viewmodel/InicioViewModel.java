package com.example.myapplication.viewmodel;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.InicioCaseUse;

public class InicioViewModel extends ViewModel {

    public final MutableLiveData<Fragment> select;

    public InicioViewModel(){
        select = new MutableLiveData<>();
    }

    public LiveData<Fragment>getFragment(){
        return select;
    }

    public void setFragment(int codigo){
        select.setValue(InicioCaseUse.selectFragment(codigo));
    }





}
