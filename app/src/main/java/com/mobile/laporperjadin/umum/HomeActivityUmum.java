package com.mobile.laporperjadin.umum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mobile.laporperjadin.LoginActivity;
import com.mobile.laporperjadin.R;

public class HomeActivityUmum extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_umum);
    }

    public void toPengajuanUmum(View view) {
        startActivity(new Intent(HomeActivityUmum.this,FormPengajuan.class));
    }
    public void toRiwayatUmum(View view) {
        startActivity(new Intent(HomeActivityUmum.this,RiwayatPengaduanActivity.class));
    }
    public void toAccountUmum(View view) {
        startActivity(new Intent(HomeActivityUmum.this, AccountActivityUmum.class));
    }
    public void toLogoutUmum(View view) {
        startActivity(new Intent(HomeActivityUmum.this, LoginActivity.class));
    }
}
