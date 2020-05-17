package com.mobile.laporperjadin.umum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.mobile.laporperjadin.AccountActivity;
import com.mobile.laporperjadin.LoginActivity;
import com.mobile.laporperjadin.R;

public class HomeActivityUmum extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_umum);
    }

    public void toPengajuanUmum(View view) {
        startActivity(new Intent(HomeActivityUmum.this,FormPengajuan.class));
    }
    public void toRiwayatUmum(View view) {
        startActivity(new Intent(HomeActivityUmum.this, RiwayatPengajuanActivity.class));
    }
    public void toAccountUmum(View view) {
        startActivity(new Intent(HomeActivityUmum.this, AccountActivity.class));
    }
    public void toLogoutUmum(View view) {
        SharedPreferences sharedPrefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.clear();
        editor.commit();
        startActivity(new Intent(HomeActivityUmum.this, LoginActivity.class));
    }
}
