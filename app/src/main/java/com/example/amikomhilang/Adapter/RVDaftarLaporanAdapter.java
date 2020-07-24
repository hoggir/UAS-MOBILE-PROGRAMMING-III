package com.example.amikomhilang.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amikomhilang.Model.Laporan;
import com.example.amikomhilang.R;

import java.util.ArrayList;

public class RVDaftarLaporanAdapter extends RecyclerView.Adapter<RVDaftarLaporanAdapter.RecyclerViewAdapterHolder> {

    public ArrayList<Laporan> laporans;

    public RVDaftarLaporanAdapter(ArrayList<Laporan> laporans) {
        this.laporans = laporans;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_design,parent,false);
        RecyclerViewAdapterHolder recyclerViewHolder = new RecyclerViewAdapterHolder(view);

        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterHolder holder, int position) {

        final String Nim = laporans.get(position).getNim();
        final String Nama = laporans.get(position).getNama();
        final String Keluhan = laporans.get(position).getKeluhan();

        //Memasukan Nilai/Value kedalam View (TextView: NIM, Nama, Jurusan)
        holder.tvNama.setText("Nama : "+ Nama);
        holder.tvNim.setText("Nim : "+ Nim);
        holder.tvLaporan.setText("Laporan : "+ Keluhan);
    }

    @Override
    public int getItemCount() {
        return laporans.size();
    }

    public class RecyclerViewAdapterHolder extends RecyclerView.ViewHolder{

        public TextView tvNama, tvNim, tvLaporan;
        public CardView listItem;

        public RecyclerViewAdapterHolder(@NonNull View itemView) {
            super(itemView);

            tvNama = itemView.findViewById(R.id.tv_nama);
            tvNim = itemView.findViewById(R.id.tv_nim);
            tvLaporan = itemView.findViewById(R.id.tv_keluhan);
            listItem = itemView.findViewById(R.id.list_item);
        }
    }
}
