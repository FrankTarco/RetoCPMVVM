package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.ProductModel;

import java.util.List;

public class ReporteAdapter extends RecyclerView.Adapter<ReporteAdapter.ViewHolder> {

    private List<ProductModel>listado;

    public ReporteAdapter(List<ProductModel> listado){
        this.listado = listado;
    }

    @NonNull
    @Override
    public ReporteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_reporte,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReporteAdapter.ViewHolder holder, int position) {
        ProductModel pro = listado.get(position);
        holder.bind(pro);
    }

    @Override
    public int getItemCount() {
        return listado.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title,description,quantity,price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvReporteTitle);
            description = itemView.findViewById(R.id.tvReporteDescription);
            quantity = itemView.findViewById(R.id.tvReporteCantidad);
            price = itemView.findViewById(R.id.tvReporteMonto);
        }

        public void bind(ProductModel pro){
            title.setText(pro.getName());
            description.setText(pro.getDescription());
            quantity.setText("Cant." + pro.getQuantity());
            price.setText("S/." + pro.totalPagar());
        }
    }
}
