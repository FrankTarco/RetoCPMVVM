package com.example.myapplication;

import androidx.fragment.app.Fragment;

import com.example.myapplication.view.DulceriaFragment;
import com.example.myapplication.view.Home;
import com.example.myapplication.view.LoginFragment;

public class InicioCaseUse {

    public static Fragment selectFragment(int codigo){
        Fragment frag;

        if(codigo == R.id.menu_inicio){
         frag = new Home();
        }
        else if(codigo == R.id.menu_buses){
            frag = new DulceriaFragment();
        }
        else{
            frag = new LoginFragment();
        }

        return frag;
    }

}
