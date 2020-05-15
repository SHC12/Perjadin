package com.mobile.laporperjadin.umum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.mobile.laporperjadin.R;
import com.mobile.laporperjadin.SuksesActivity;

public class AccountActivityUmum extends AppCompatActivity {
    private MaterialButton btnGantiPassword;
    private String results ="gantipassword";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        btnGantiPassword = findViewById(R.id.btnGantiPassword);
        btnGantiPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivityUmum.this, SuksesActivity.class);
                intent.putExtra("result",results);
                startActivity(intent);
            }
        });
    }
}
