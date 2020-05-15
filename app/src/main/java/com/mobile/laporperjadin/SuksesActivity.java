package com.mobile.laporperjadin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.mobile.laporperjadin.umum.HomeActivityUmum;

public class SuksesActivity extends AppCompatActivity {
    private MaterialButton btnSukses;
    private TextView title,desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succes);
        btnSukses = findViewById(R.id.btnSuccess);
        title = findViewById(R.id.title_success);
        desc = findViewById(R.id.desc_sukses);
        String result = getIntent().getStringExtra("result");
        if(result.equals("register")){
            title.setText("Registrasi Berhasil");
            desc.setText(R.string.successRegister);
            btnSukses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        }
        if(result.equals("ajukan")) {
            title.setText("Pengajuan Berhasil");
            desc.setText(R.string.successPengajuan);
            btnSukses.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(SuksesActivity.this, HomeActivityUmum.class));
                }
            });
        }
        if(result.equals("gantipassword")) {
            title.setText("Ganti Password Berhasil");
            desc.setText("Password berhasil di ganti");
            btnSukses.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(SuksesActivity.this, HomeActivityUmum.class));
                }
            });
        }
    }
}
