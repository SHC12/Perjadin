package com.mobile.laporperjadin.umum;

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

import java.text.DateFormat;
import java.util.Date;

public class HomeActivityUmum extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
    private TextView name;
    private String mname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_umum);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        mname = settings.getString("nama", "default");
        String[] first = mname.split(" ", 2);
        name = findViewById(R.id.userHomeUmum);
        name.setText(first[0]);

        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        TextView timeNow = findViewById(R.id.timeNowUmum);
        timeNow.setText(currentDateTimeString);


    }

    public void toPengajuanUmum(View view) {
        startActivity(new Intent(HomeActivityUmum.this, FormPengajuan.class));
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
        startActivity(new Intent(HomeActivityUmum.this, MenuLoginActivity.class));
    }
}
