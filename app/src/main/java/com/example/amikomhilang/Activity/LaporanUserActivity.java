package com.example.amikomhilang.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.amikomhilang.Adapter.RVLaporanUserAdapter;
import com.example.amikomhilang.Model.Laporan;
import com.example.amikomhilang.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LaporanUserActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private DatabaseReference reference;
    private ArrayList<Laporan> datalaporan;
    private FirebaseAuth auth;

    //public FirebaseAuth auth;

    /**
     * public LaporanUserPresenter mPresenter;
     * public ProgressBar mProgressbar;
     * public DatabaseReference mReference;
     * public RecyclerView mRecyclerView;
     * public RecyclerView.Adapter recyclerViewAdapter;
     * public FirebaseAuth auth;
     **/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list_data);
        recyclerView = findViewById(R.id.datalist);
        getSupportActionBar().setTitle("Laporan Saya");
        auth = FirebaseAuth.getInstance();
        MyRecyclerView();
        GetData();
    }

    private void MyRecyclerView() {

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }


    private void GetData() {

        reference = FirebaseDatabase.getInstance().getReference();
        String userID = auth.getUid();
        reference.child("Laporan").orderByChild("idmhs").equalTo(userID)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        datalaporan = new ArrayList<>();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {


                            Laporan laporan = snapshot.getValue(Laporan.class);

                            laporan.setKey(snapshot.getKey());
                            datalaporan.add(laporan);

                        }


                        adapter = new RVLaporanUserAdapter(datalaporan, LaporanUserActivity.this);


                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                        Toast.makeText(getApplicationContext(), "Data Gagal Dimuat", Toast.LENGTH_LONG).show();
                        Log.e("LaporanUserActivity", databaseError.getDetails() + " " + databaseError.getMessage());
                    }
                });
    }


    /**
    @Override
    public void onDeleteData(Laporan laporan, int position) {

        if (reference != null) {
            reference.child("Laporan")
                    .child(laporan.getKey())
                    .removeValue()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(LaporanUserActivity.this, "Berhasil Dihapus", Toast.LENGTH_LONG).show();
                        }
                    });

        }
    }
    **/
}