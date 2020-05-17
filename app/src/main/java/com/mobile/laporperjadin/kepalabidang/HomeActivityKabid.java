package com.mobile.laporperjadin.kepalabidang;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mobile.laporperjadin.AccountActivity;
import com.mobile.laporperjadin.LoginActivity;
import com.mobile.laporperjadin.R;

public class HomeActivityKabid extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_kabid);
    }

    public void toDaftarPengajuanKabid(View view) {
        startActivity(new Intent(HomeActivityKabid.this, DaftarPengajuanKabid.class));
    }
    public void toAkunKabid(View view) {
        startActivity(new Intent(HomeActivityKabid.this, AccountActivity.class));
    }
    public void toLogoutKabid(View view) {
        SharedPreferences sharedPrefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.clear();
        editor.commit();
        startActivity(new Intent(HomeActivityKabid.this, LoginActivity.class));
    }
}
