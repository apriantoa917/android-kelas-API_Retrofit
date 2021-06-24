package com.aprianto.p13apiandroid.Barang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aprianto.p13apiandroid.API_config.Barang.ModelBarang;
import com.aprianto.p13apiandroid.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterBarang extends RecyclerView.Adapter<AdapterBarang.ViewHolder> {

    List<ModelBarang> list_barang = new ArrayList<>();
    Context context;
    listenerBarang listener;

    public AdapterBarang(List<ModelBarang> list_barang, Context context, listenerBarang listener) {
        this.list_barang = list_barang;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_rv_barang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ModelBarang model = list_barang.get(position);
        holder.tv_kode.setText(model.getKode());
        holder.tv_nama.setText(model.getNama());
        holder.tv_satuan.setText(model.getSatuan());
        holder.tv_hjual.setText(model.getHargajual());
        holder.tv_hbeli.setText(model.getHargabeli());
        holder.tv_diskon.setText(model.getDiskon());
    }

    @Override
    public int getItemCount() {
        return list_barang.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_kode, tv_nama, tv_satuan, tv_hjual, tv_hbeli, tv_diskon;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_kode =itemView.findViewById(R.id.tv_kode);
            tv_nama =itemView.findViewById(R.id.tv_nama);
            tv_satuan =itemView.findViewById(R.id.tv_satuan);
            tv_hjual =itemView.findViewById(R.id.tv_hjual);
            tv_hbeli =itemView.findViewById(R.id.tv_hbeli);
            tv_diskon =itemView.findViewById(R.id.tv_diskon);

            itemView.setOnClickListener(view -> {
                listener.onItemClicked(
                        tv_kode.getText().toString(),
                        tv_nama.getText().toString(),
                        tv_satuan.getText().toString(),
                        tv_hjual.getText().toString(),
                        tv_hbeli.getText().toString(),
                        tv_diskon.getText().toString()
                );
            });
        }
    }
}

interface listenerBarang{
    public void onItemClicked(String kode, String nama, String satuan, String hjual, String hbeli, String diskon);
}
