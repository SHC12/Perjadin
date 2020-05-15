package com.mobile.laporperjadin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;
import com.mobile.laporperjadin.umum.HomeActivityUmum;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private MaterialButton btnLogin;
    String mUsername="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.in_username);
         mUsername= username.getText().toString();
        btnLogin = findViewById(R.id.btnLoginToHome);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, HomeActivityUmum.class));
            }
        });

    }
}

