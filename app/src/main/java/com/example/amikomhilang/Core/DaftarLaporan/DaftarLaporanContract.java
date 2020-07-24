package com.example.amikomhilang.Core.DaftarLaporan;

import com.example.amikomhilang.Model.Laporan;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public interface DaftarLaporanContract {
    interface View{
        void onCreateLaporanSuccessful();
        void onCreateLaporanFailure();
        void onProcessStart();
        void onProcessEnd();
        void onLaporanRead(ArrayList<Laporan> laporans);
    }

    interface Presenter{
        void onCreateNewLaporan (DatabaseReference reference, Laporan laporan);
        void readLaporans(DatabaseReference reference);
    }

    interface Interactor{
        void performCreateLaporan (DatabaseReference reference, Laporan laporan);
        void performReadLaporan(DatabaseReference reference);
    }

    interface onOperationListener{
        void onSucces();
        void onFailure();
        void onStart();
        void onEnd();
        void onRead(ArrayList<Laporan> laporans);
    }
}
