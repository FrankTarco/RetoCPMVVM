package com.example.myapplication.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemChangeListener;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.model.PremierModel;
import com.example.myapplication.model.PremieresModel;
import com.example.myapplication.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;

    public Home() {
    }
/*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
 */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        viewModel.getLista().observe(getViewLifecycleOwner(), new Observer<PremieresModel>() {
            @Override
            public void onChanged(PremieresModel premieresModel) {
                ArrayList<SlideModel> imageList = new ArrayList<>();
                for(PremierModel bean: premieresModel.getPremieres()){
                    imageList.add(new SlideModel(bean.getImage(), bean.getDescription(), ScaleTypes.CENTER_CROP));
                }
                binding.imageSlider.setImageList(imageList);
                binding.imageSlider.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onItemSelected(int i) {
                        viewModel.getFragment().observe(getViewLifecycleOwner(), new Observer<Fragment>() {
                            @Override
                            public void onChanged(Fragment fra) {
                                reemplazarFragment(fra);
                            }
                        });
                    }

                    @Override
                    public void doubleClick(int i) {

                    }
                });
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