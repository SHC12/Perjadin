package com.mobile.laporperjadin.admin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mobile.laporperjadin.AccountActivity;
import com.mobile.laporperjadin.LoginActivity;
import com.mobile.laporperjadin.R;

public class HomeActivityAdmin extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
    }

    public void toKelolaUser(View view) {
        startActivity(new Intent(HomeActivityAdmin.this, KelolaUserActivity.class));
    }
    public void toAccountUmum(View view) {
        startActivity(new Intent(HomeActivityAdmin.this, AccountActivity.class));
    }
    public void toLogoutUmum(View view) {
        startActivity(new Intent(HomeActivityAdmin.this, LoginActivity.class));
    }

    public void toPengajuanPerjadin(View view) {
        SharedPreferences sharedPrefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.clear();
        editor.commit();
        startActivity(new Intent(HomeActivityAdmin.this, PengajuanPerjadiinAdmin.class));
    }
}
