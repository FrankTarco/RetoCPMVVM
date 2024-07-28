package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.ItemModel;
import com.example.myapplication.model.ProductModel;
import com.example.myapplication.viewmodel.DulceriaViewModel;
import com.google.android.material.button.MaterialButton;

public class CandyAdapter extends RecyclerView.Adapter<CandyAdapter.ViewHolder> {


    private ItemModel lista;
    private DulceriaViewModel viewModel;

    public CandyAdapter(ItemModel lista,DulceriaViewModel viewModel){
        this.lista = lista;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public CandyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_candystore,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CandyAdapter.ViewHolder holder, int position) {
        ProductModel pro = lista.getItems().get(position);
        holder.bind(pro);
    }

    @Override
    public int getItemCount() {
        return lista.getItems().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title,description,quantity,price;
        private MaterialButton btnAdd,btnMinus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvItemTitle);
            description = itemView.findViewById(R.id.tvItemDescription);
            quantity = itemView.findViewById(R.id.tvItemQuantity);
            price = itemView.findViewById(R.id.tvItemPrice);
            btnAdd = itemView.findViewById(R.id.btnItemAdd);
            btnMinus = itemView.findViewById(R.id.btnItemMinus);
        }

        public void bind(ProductModel obj){
            title.setText(obj.getName());
            description.setText(obj.getDescription());
            price.setText(obj.getPrice());
            quantity.setText(String.valueOf(obj.getQuantity()));

            btnMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(obj.getQuantity()>0){
                        obj.setQuantity(obj.getQuantity()-1);
                        quantity.setText(String.valueOf(obj.getQuantity()));
                        viewModel.setTotal();
                        viewModel.setReporte();
                    }
                }
            });

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    obj.setQuantity(obj.getQuantity()+1);
                    quantity.setText(String.valueOf(obj.getQuantity()));
                    viewModel.setTotal();
                    viewModel.setReporte();
                }
            });
        }

    }
}
