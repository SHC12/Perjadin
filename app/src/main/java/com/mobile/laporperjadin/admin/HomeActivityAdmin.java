package com.mobile.laporperjadin.admin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mobile.laporperjadin.AccountActivity;
import com.mobile.laporperjadin.MenuLoginActivity;
import com.mobile.laporperjadin.R;

public class HomeActivityAdmin extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
    private TextView username;
    private String mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        mUsername = settings.getString("username", "default");
        String[] first = mUsername.split(" ", 2);
        username = findViewById(R.id.userHomeAdmin);
        username.setText(first[0]);
    }

    public void toKelolaUser(View view) {
        startActivity(new Intent(HomeActivityAdmin.this, KelolaUserActivity.class));
    }

    public void toAccountUmum(View view) {
        startActivity(new Intent(HomeActivityAdmin.this, AccountActivity.class));
    }

    public void toLogoutUmum(View view) {
        SharedPreferences sharedPrefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.clear();
        editor.commit();
        startActivity(new Intent(HomeActivityAdmin.this, MenuLoginActivity.class));
    }

    public void toPengajuanPerjadin(View view) {

        startActivity(new Intent(HomeActivityAdmin.this, PengajuanPerjadiinAdmin.class));

    }


}
