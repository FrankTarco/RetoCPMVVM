package com.example.myapplication.view;

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

import com.example.myapplication.R;
import com.example.myapplication.adapter.ReporteAdapter;
import com.example.myapplication.databinding.FragmentReporteBinding;
import com.example.myapplication.model.ProductModel;
import com.example.myapplication.viewmodel.DulceriaViewModel;

import java.util.List;

public class ReporteFragment extends Fragment {

    private FragmentReporteBinding binding;
    private ReporteAdapter adapter;
    private DulceriaViewModel viewModel;

    public ReporteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentReporteBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        binding.rcResumenCompras.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModel = new ViewModelProvider(requireActivity()).get(DulceriaViewModel.class);

        binding.btnCerrarReporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reemplazarFragment(new DulceriaFragment());
            }
        });
        viewModel.getTotal().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                binding.tvReporteTotalPagar.setText("S/."+ aDouble);
                binding.tvReporteSubTotal.setText("S/."+aDouble);
            }
        });

        viewModel.getRep().observe(getViewLifecycleOwner(), new Observer<List<ProductModel>>() {
            @Override
            public void onChanged(List<ProductModel> productModels) {
                adapter = new ReporteAdapter(productModels);
                binding.rcResumenCompras.setAdapter(adapter);
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