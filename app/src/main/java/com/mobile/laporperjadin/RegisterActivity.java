package com.mobile.laporperjadin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class RegisterActivity extends AppCompatActivity {
    private String result = "register";
    private MaterialButton btnToVerif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnToVerif = findViewById(R.id.btnRegisterToVerif);
        btnToVerif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,SuksesActivity.class);
                intent.putExtra("result",result);
                startActivity(intent);
            }
        });

    }
}
