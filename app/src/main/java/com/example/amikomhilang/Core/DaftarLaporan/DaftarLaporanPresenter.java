package com.example.amikomhilang.Core.DaftarLaporan;

import com.example.amikomhilang.Model.Laporan;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class DaftarLaporanPresenter implements DaftarLaporanContract.Presenter, DaftarLaporanContract.onOperationListener{

    DaftarLaporanContract.View view;
    DaftarLaporanInteractor interactor;

    public DaftarLaporanPresenter(DaftarLaporanContract.View view) {
        this.view = view;
        interactor = new DaftarLaporanInteractor(this);
    }

    @Override
    public void onCreateNewLaporan(DatabaseReference reference, Laporan laporan) {

    }

    @Override
    public void readLaporans(DatabaseReference reference) {
        interactor.performReadLaporan(reference);

    }

    @Override
    public void onSucces() {

    }

    @Override
    public void onFailure() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onEnd() {

    }

    @Override
    public void onRead(ArrayList<Laporan> laporans) {
        view.onLaporanRead(laporans);

    }
}
