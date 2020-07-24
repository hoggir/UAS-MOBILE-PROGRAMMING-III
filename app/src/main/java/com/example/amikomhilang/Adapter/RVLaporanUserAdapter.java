package com.example.amikomhilang.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amikomhilang.InputActivity;
import com.example.amikomhilang.Model.Laporan;
import com.example.amikomhilang.R;

import java.util.ArrayList;

public class RVLaporanUserAdapter extends RecyclerView.Adapter<RVLaporanUserAdapter.RecyclerViewSayaAdapterHolder>{

    public ArrayList<Laporan> laporans;
    private Context context;

    public RVLaporanUserAdapter(ArrayList<Laporan> laporans, Context context) {
        this.laporans = laporans;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewSayaAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_design,parent,false);
        RecyclerViewSayaAdapterHolder recyclerViewSayaAdapterHolder = new RecyclerViewSayaAdapterHolder(view);

        return recyclerViewSayaAdapterHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewSayaAdapterHolder holder, final int position) {

        final String Nim = laporans.get(position).getNim();
        final String Nama = laporans.get(position).getNama();
        final String Keluhan = laporans.get(position).getKeluhan();

        //Memasukan Nilai/Value kedalam View (TextView: NIM, Nama, Jurusan)
        holder.tvNama.setText("Nama : "+ Nama);
        holder.tvNim.setText("Nim : "+ Nim);
        holder.tvLaporan.setText("Laporan : "+ Keluhan);

        holder.listItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
                final String[] action = {"Update", "Delete"};
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setItems(action,  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i) {
                            case 0:
                                context.startActivity(InputActivity.getActIntent((Activity) context).putExtra("data", laporans.get(position)));
                                break;
                            case 1:
                                dialog.dismiss();
                                break;
                        }
                    }
                });
                alert.create();
                alert.show();
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return laporans.size();
    }

    public class RecyclerViewSayaAdapterHolder extends RecyclerView.ViewHolder{

        public TextView tvNama, tvNim, tvLaporan;
        public CardView listItem;


        public RecyclerViewSayaAdapterHolder(@NonNull View itemView) {
            super(itemView);

            tvNama = itemView.findViewById(R.id.tv_nama);
            tvNim = itemView.findViewById(R.id.tv_nim);
            tvLaporan = itemView.findViewById(R.id.tv_keluhan);
            listItem = itemView.findViewById(R.id.list_item);
        }
    }
}
/**
        holder.ListItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
                final String [] action = {"Update", "Hapus"};
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setItems(action, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i) {
                            case 0:
                                context.startActivity(InputActivity.getActIntent((Activity) context).putExtra("data", listLaporan.get(position)));
                                break;
                            case 1:
                                dialog.dismiss();
                                listener.onDeleteData(listLaporan.get(position), position);
                                break;
                        }
                    }
                });
                alert.create();
                alert.show();
                return true;
            }
        });**/
