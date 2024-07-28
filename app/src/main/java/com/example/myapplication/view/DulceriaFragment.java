package com.example.myapplication.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CandyAdapter;
import com.example.myapplication.databinding.FragmentDulceriaBinding;
import com.example.myapplication.model.ItemModel;
import com.example.myapplication.viewmodel.DulceriaViewModel;

public class DulceriaFragment extends Fragment {

    FragmentDulceriaBinding binding;
    private CandyAdapter adapter;
    private DulceriaViewModel viewModel;

    public DulceriaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDulceriaBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        binding.btnFinalizarCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reemplazarFragment(new ReporteFragment());
            }
        });

        binding.rcLstCandy.setLayoutManager(new GridLayoutManager(getContext(),2));
        viewModel = new ViewModelProvider(requireActivity()).get(DulceriaViewModel.class);
        viewModel.getTotal().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double total) {
                binding.tvTotal.setText("Calculado: " + total);
            }
        });
        viewModel.getList().observe(getViewLifecycleOwner(), new Observer<ItemModel>() {
            @Override
            public void onChanged(ItemModel itemModel) {
                adapter = new CandyAdapter(itemModel,viewModel);
                binding.rcLstCandy.setAdapter(adapter);
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