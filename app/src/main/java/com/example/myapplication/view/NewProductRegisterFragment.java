package com.example.myapplication.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentNewProductRegisterBinding;
import com.example.myapplication.model.ProductoRequest;
import com.example.myapplication.model.ProductoResponse;
import com.example.myapplication.viewmodel.ProductoViewModel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class NewProductRegisterFragment extends Fragment {

    private FragmentNewProductRegisterBinding binding;
    private ProductoViewModel viewModel;

    public NewProductRegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentNewProductRegisterBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        viewModel = new ViewModelProvider(requireActivity()).get(ProductoViewModel.class);

        binding.btnRegistarProductoAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertProduct();
            }
        });

        viewModel.getProductoInsert().observe(getViewLifecycleOwner(), new Observer<ProductoResponse>() {
            @Override
            public void onChanged(ProductoResponse productoResponse) {
                Snackbar.make(view,"Se inserto: "+productoResponse.getTitle(), BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void insertProduct(){
        String titulo,descripcion,image,categoria;
        double precio;

        titulo = binding.txtTituloInsert.getText().toString();
        descripcion = binding.txtDescripcionInsert.getText().toString();
        image = binding.txtImageInsert.getText().toString();
        categoria = binding.txtCategoriaInsert.getText().toString();
        precio = Double.parseDouble(binding.txtPrecioInsert.getText().toString());

        ProductoRequest bean = new ProductoRequest();
        bean.setTitle(titulo);
        bean.setDescription(descripcion);
        bean.setImage(image);
        bean.setCategory(categoria);
        bean.setPrice(precio);

        viewModel.insertProduct(bean);
    }


    private void limpieza(){
        binding.txtTituloInsert.setText("");
        binding.txtDescripcionInsert.setText("");
        binding.txtImageInsert.setText("");
        binding.txtCategoriaInsert.setText("");
        binding.txtPrecioInsert.setText("");
    }

}