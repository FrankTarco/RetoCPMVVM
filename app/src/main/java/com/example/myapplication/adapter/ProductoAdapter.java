package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.ProductoModel;
import com.example.myapplication.viewmodel.ProductoViewModel;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder> {

    private List<ProductoModel> listado;
    private ProductoViewModel viewModel;

    public ProductoAdapter(List<ProductoModel> listado,ProductoViewModel viewModel){
        this.listado = listado;
        this.viewModel = viewModel;
    }


    public void setProductList(List<ProductoModel> listado) {
        this.listado = listado;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_producto,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoAdapter.ViewHolder holder, int position) {
        ProductoModel pro = listado.get(position);
        holder.hind(pro);
    }

    @Override
    public int getItemCount() {
        return listado.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title,price,category;
        ImageView imgProduct;
        Button btnEditar,btnEliminar;
        Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            title = itemView.findViewById(R.id.tvTitleItemProducto);
            price = itemView.findViewById(R.id.tvPrecioItemProducto);
            category = itemView.findViewById(R.id.tvCategoriaItemProducto);
            imgProduct = itemView.findViewById(R.id.imgItemProducto);
            btnEditar = itemView.findViewById(R.id.btnEditItemProducto);
            btnEliminar = itemView.findViewById(R.id.btnDeleteItemProducto);

        }

        public void hind(ProductoModel pro){
            title.setText(pro.getTitle());
            price.setText(String.valueOf(pro.getPrice()));
            category.setText(pro.getCategory());
            Glide.with(context).load(pro.getImage()).into(imgProduct);

            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewModel.deleteProduct(pro.getId());
                }
            });
        }
    }
}
