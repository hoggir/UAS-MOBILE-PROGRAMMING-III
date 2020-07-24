package com.example.amikomhilang.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.amikomhilang.Adapter.RVDaftarLaporanAdapter;
import com.example.amikomhilang.Core.DaftarLaporan.DaftarLaporanContract;
import com.example.amikomhilang.Core.DaftarLaporan.DaftarLaporanPresenter;
import com.example.amikomhilang.Model.Laporan;
import com.example.amikomhilang.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DaftarLaporanActivity extends AppCompatActivity implements DaftarLaporanContract.View {

    public DaftarLaporanPresenter mPresenter;
    public ProgressBar mProgressbar;
    public DatabaseReference mReference;
    public RecyclerView mRecyclerView;
    public RecyclerView.Adapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list_data);
        mRecyclerView = findViewById(R.id.datalist);
        getSupportActionBar().setTitle("Laporan Kehilangan");

        mReference = FirebaseDatabase.getInstance().getReference().child("Laporan");

        mPresenter = new DaftarLaporanPresenter(this);
        mPresenter.readLaporans(mReference);
    }

    @Override
    public void onCreateLaporanSuccessful() {

    }

    @Override
    public void onCreateLaporanFailure() {

    }

    @Override
    public void onProcessStart() {

    }

    @Override
    public void onProcessEnd() {

    }

    @Override
    public void onLaporanRead(ArrayList<Laporan> laporans) {

        recyclerViewAdapter = new RVDaftarLaporanAdapter(laporans);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(recyclerViewAdapter);
    }
    }
