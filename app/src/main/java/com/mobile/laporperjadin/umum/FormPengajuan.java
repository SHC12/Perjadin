package com.mobile.laporperjadin.umum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.mobile.laporperjadin.R;
import com.mobile.laporperjadin.SuksesActivity;

public class FormPengajuan extends AppCompatActivity {
    MaterialButton btnAjukan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pengajuan);
    btnAjukan = findViewById(R.id.btnAjukanUmum);
    final String resultAjukan = "ajukan";
    btnAjukan.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(FormPengajuan.this, SuksesActivity.class);
            intent.putExtra("result",resultAjukan);
            startActivity(intent);
        }
    });
    }
}
