package com.example.amikomhilang;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amikomhilang.Model.Laporan;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InputActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private DatabaseReference database;

    private Button btSumbit;
    private EditText etNama, etNim, etKeluhan,idMhs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        etNama = (EditText) findViewById(R.id.et_namaMahasiswa);
        etNim = (EditText) findViewById(R.id.et_nimMahasiswa);
        etKeluhan = (EditText) findViewById(R.id.et_keluhanMahasiswa);
        btSumbit = (Button) findViewById(R.id.bt_submit);
        idMhs = (EditText) findViewById(R.id.et_idMahasiswa);

        database = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        final String userID = auth.getUid();

        final Laporan laporan = (Laporan) getIntent().getSerializableExtra("data");

        if (laporan != null) {

            idMhs.setText(laporan.getIdmhs());
            etNama.setText(laporan.getNama());
            etNim.setText(laporan.getNim());
            etKeluhan.setText(laporan.getKeluhan());
            btSumbit.setText("Update");
            btSumbit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    laporan.setIdmhs(idMhs.getText().toString());
                    laporan.setNama(etNama.getText().toString());
                    laporan.setNim(etNim.getText().toString());
                    laporan.setKeluhan(etKeluhan.getText().toString());

                    updateLaporan(laporan);
                }
            });
        } else {
            idMhs.setText(userID);
            btSumbit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isEmpty(etNama.getText().toString()) && !isEmpty(etNim.getText().toString()) && !isEmpty(etKeluhan.getText().toString()) && !isEmpty(idMhs.getText().toString()))
                        submitBarang(new Laporan(idMhs.getText().toString(), etNama.getText().toString(), etNim.getText().toString(), etKeluhan.getText().toString()));
                    else
                        Snackbar.make(findViewById(R.id.bt_submit), "Data barang tidak boleh kosong", Snackbar.LENGTH_LONG).show();

                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(
                            etNama.getWindowToken(), 0);
                }
            });
        }
    }

    private boolean isEmpty(String s) {

        return TextUtils.isEmpty(s);
    }

    private void updateLaporan(Laporan laporan) {
        String userID = auth.getUid();


        database.child("Laporan")
                .child(laporan.getKey())
                .setValue(laporan)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Toast.makeText(getApplicationContext(),"Data berhasil diperbarui", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void submitBarang(Laporan laporan) {
        final String getUserID = auth.getCurrentUser().getUid();

        database.child("Laporan").push()
                .setValue(laporan)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                idMhs.setText(getUserID);
                etNama.setText("");
                etNim.setText("");
                etKeluhan.setText("");
                Snackbar.make(findViewById(R.id.bt_submit), "Laporan berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public static Intent getActIntent(Activity activity) {

        return new Intent(activity, InputActivity.class);
    }

}
