package com.example.amikomhilang.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amikomhilang.Core.Register.RegistrationContract;
import com.example.amikomhilang.Core.Register.RegistrationPresenter;
import com.example.amikomhilang.R;
import com.google.firebase.auth.FirebaseUser;

public class DaftarActivity extends AppCompatActivity implements View.OnClickListener, RegistrationContract.View {

    Button btnDaftar;
    EditText etEmail, etPass;
    private RegistrationPresenter mRegisterPresenter;
    ProgressDialog mPrgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        initViews();
    }

    private void initViews() {
        btnDaftar = findViewById(R.id.btn_daftarkan);
        btnDaftar.setOnClickListener(this);
        etEmail = findViewById(R.id.et_emailDaftar);
        etPass = findViewById(R.id.et_passwordDaftar);

        mRegisterPresenter = new RegistrationPresenter(this);

        mPrgressDialog = new ProgressDialog(this);
        mPrgressDialog.setMessage("Tunggu sebentar...");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_daftarkan:
                checkRegistrationDetails();
                break;
            case R.id.btn_bcklogin:
                moveToLoginActivity();
                break;
        }

    }

    private void moveToLoginActivity() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    private void checkRegistrationDetails() {
        if(!TextUtils.isEmpty(etEmail.getText().toString()) && !TextUtils.isEmpty(etPass.getText().toString())){
            initLogin(etEmail.getText().toString(), etPass.getText().toString());
        }else{
            if(TextUtils.isEmpty(etEmail.getText().toString())){
                etEmail.setError("Masukkan email!");
            }if(TextUtils.isEmpty(etPass.getText().toString())){
                etPass.setError("Massukkan password!");
            }
        }
    }

    private void initLogin(String email, String password) {
        mPrgressDialog.show();
        mRegisterPresenter.register(this, email, password);
    }

    @Override
    public void onRegistrationSuccess(FirebaseUser firebaseUser) {
        mPrgressDialog.dismiss();
        Toast.makeText(getApplicationContext(), "Berhasil di daftarkan" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegistrationFailure(String message) {
        mPrgressDialog.dismiss();
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
