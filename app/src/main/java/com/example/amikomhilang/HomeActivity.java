package com.example.amikomhilang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.amikomhilang.Activity.LaporanUserActivity;
import com.example.amikomhilang.Activity.DaftarLaporanActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private CardView cv_input,cv_lihat,cv_info, cv_about;
    private TextView tvDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

        cv_input = (CardView) findViewById(R.id.cv_input);
        cv_input.setOnClickListener(this);
        cv_lihat = (CardView) findViewById(R.id.cv_lihat);
        cv_lihat.setOnClickListener(this);
        cv_info = findViewById(R.id.cv_info);
        cv_info.setOnClickListener(this);
        cv_about = findViewById(R.id.cv_faq);
        cv_about.setOnClickListener(this);
        tvDashboard= findViewById(R.id.tv_dashboard);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cv_input:
                startActivity(InputActivity.getActIntent(HomeActivity.this));
                break;

            case R.id.cv_lihat:
                HomeActivity.this.startActivity(new Intent(HomeActivity.this, DaftarLaporanActivity.class));
                break;
            case  R.id.cv_info:
                HomeActivity.this.startActivity(new Intent(HomeActivity.this, LaporanUserActivity.class));
                break;
            case  R.id.cv_faq:
                HomeActivity.this.startActivity(new Intent(HomeActivity.this, InfoActivity.class));
                break;
        }
    }
}
