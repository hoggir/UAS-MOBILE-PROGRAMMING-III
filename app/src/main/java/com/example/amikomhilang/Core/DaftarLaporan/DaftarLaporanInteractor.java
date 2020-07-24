package com.example.amikomhilang.Core.DaftarLaporan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.amikomhilang.Model.Laporan;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class DaftarLaporanInteractor implements DaftarLaporanContract.Interactor{

    private DaftarLaporanContract.onOperationListener mListener;
    private ArrayList<Laporan> laporans = new ArrayList<>();

    public DaftarLaporanInteractor(DaftarLaporanContract.onOperationListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public void performCreateLaporan(DatabaseReference reference, Laporan laporan) {

    }

    @Override
    public void performReadLaporan(DatabaseReference reference) {
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Laporan laporan = snapshot.getValue(Laporan.class);
                laporans.add(laporan);
                mListener.onRead(laporans);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
