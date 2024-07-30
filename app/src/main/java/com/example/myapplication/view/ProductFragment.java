package com.example.myapplication.view;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ProductoAdapter;
import com.example.myapplication.databinding.FragmentProductBinding;
import com.example.myapplication.model.ProductoModel;
import com.example.myapplication.viewmodel.ProductoViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {

    FragmentProductBinding binding;
    ProductoAdapter adapter;
    Dialog dialog;
    ProductoViewModel viewModel;


    public ProductFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProductBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        //INICIAR VIEW MODEL
        viewModel = new ViewModelProvider(this).get(ProductoViewModel.class);
        binding.rcListProducts.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ProductoAdapter(new ArrayList<>(),viewModel);
        binding.rcListProducts.setAdapter(adapter);

        binding.btnNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reemplazarFragment(new NewProductRegisterFragment());

            }
        });

        viewModel.getProductEliminated().observe(getViewLifecycleOwner(), new Observer<ProductoModel>() {
            @Override
            public void onChanged(ProductoModel productoModel) {
                Snackbar.make(view,"Se elimino el producto: "+ productoModel.getTitle(),Snackbar.LENGTH_SHORT).show();
            }
        });

        viewModel.getListProducts().observe(getViewLifecycleOwner(), new Observer<List<ProductoModel>>() {
            @Override
            public void onChanged(List<ProductoModel> productoModels) {
                if(productoModels != null){
                    Snackbar.make(view,"Entro a la data",Snackbar.LENGTH_SHORT).show();
                    adapter.setProductList(productoModels);
                    adapter.notifyDataSetChanged();
                }
                else{
                    Snackbar.make(view,"Error",Snackbar.LENGTH_SHORT).show();
                }

            }
        });

        binding.btnBusquedaProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.txtBusquedaProducto.getText().toString().isEmpty()){
                   viewModel.setListProducts();
                   return;
                }
                int idProducto = Integer.parseInt(binding.txtBusquedaProducto.getText().toString());
                viewModel.setListProductsById(idProducto);
            }
        });

        return view;
    }

    private void reemplazarFragment(Fragment f){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,f);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}