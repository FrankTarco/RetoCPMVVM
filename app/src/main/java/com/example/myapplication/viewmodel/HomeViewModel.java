package com.example.myapplication.viewmodel;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.domain.GetPremieresUseCase;
import com.example.myapplication.model.PremierModel;
import com.example.myapplication.model.PremieresModel;
import com.example.myapplication.view.DulceriaFragment;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private GetPremieresUseCase getPremieres;
    public LiveData<PremieresModel> lista;

    private LiveData<Fragment>selectImage;

    public HomeViewModel(){
        getPremieres = new GetPremieresUseCase();
        lista = getPremieres.getList();
        selectImage = new MutableLiveData<>(new DulceriaFragment());
    }

    public LiveData<Fragment>getFragment(){
        return selectImage;
    }

    public LiveData<PremieresModel> getLista(){
        return lista;
    }



}
