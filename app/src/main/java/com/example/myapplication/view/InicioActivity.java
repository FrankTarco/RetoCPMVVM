package com.example.myapplication.view;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityInicioBinding;
import com.example.myapplication.viewmodel.InicioViewModel;

public class InicioActivity extends AppCompatActivity {

    private ActivityInicioBinding binding;

    private InicioViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInicioBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(InicioViewModel.class);
        reemplazarFragment(new Home());
        setContentView(binding.getRoot());

        viewModel.getFragment().observe(InicioActivity.this, new Observer<Fragment>() {
            @Override
            public void onChanged(Fragment fra) {
                reemplazarFragment(fra);
            }
        });

        binding.bottomNavigationView.setOnItemSelectedListener(item ->{
            viewModel.setFragment(item.getItemId());
            return true;
        });

    }

    private void reemplazarFragment(Fragment f){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,f);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}