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

import com.example.amikomhilang.Core.Login.LoginContract;
import com.example.amikomhilang.Core.Login.LoginPresenter;
import com.example.amikomhilang.HomeActivity;
import com.example.amikomhilang.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginContract.View{

    EditText etEmail, etPass;
    Button btnLogin, btnSignin;
    private LoginPresenter mLoginPresenter;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }

    private void initViews() {
        Toast.makeText(getApplicationContext(), "Jangan lupa pake internet!" , Toast.LENGTH_SHORT).show();

        etEmail = (EditText) findViewById(R.id.et_email);
        etPass = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnSignin = (Button) findViewById(R.id.btn_signin);

        btnLogin.setOnClickListener(this);
        btnSignin.setOnClickListener(this);

        mLoginPresenter = new LoginPresenter(this);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Tunggu, sedang login...");
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_login) {
            checkLoginDetails();
        } else if (i == R.id.btn_signin) {
            moveToRegisterActivity();
        }
    }

    private void moveToRegisterActivity() {
        Intent intent = new Intent(getApplicationContext(), DaftarActivity.class);
        startActivity(intent);
    }

    private void initLogin(String email, String password) {
        mProgressDialog.show();
        mLoginPresenter.login(this, email, password);
    }

    private void checkLoginDetails() {
        if(!TextUtils.isEmpty(etEmail.getText().toString()) && !TextUtils.isEmpty(etPass.getText().toString())){
            initLogin(etEmail.getText().toString(), etPass.getText().toString());
        }else{
            if(TextUtils.isEmpty(etEmail.getText().toString())){
                etEmail.setError("Mohon email diisi!");
            }if(TextUtils.isEmpty(etPass.getText().toString())){
                etPass.setError("Mohon password diisi!");
            }
        }
    }


    @Override
    public void onLoginSuccess(String message) {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onLoginFailure(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(getApplicationContext(),message , Toast.LENGTH_SHORT).show();
    }
}
