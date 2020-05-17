package com.mobile.laporperjadin.umum;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.mobile.laporperjadin.R;
import com.mobile.laporperjadin.SuksesActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FormPengajuan extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
    public static String URL = "http://muhyudi.my.id/api_android/buat_pengajuan.php";
    MaterialButton btnAjukan;
    EditText nama, kota, berangkat, kembali, pesawat, penginapan, taksi_bandara, taksi_daerah, uang_harian;
    String s_nama, s_kota, s_berangkat, s_kembali, s_pesawat, s_penginapan, s_taksi_bandara, s_taksi_daerah, s_uang_harian;
    String id_user;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pengajuan);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        id_user = settings.getString("id_user", "default");

        btnAjukan = findViewById(R.id.btnAjukanUmum);
        nama = findViewById(R.id.in_namePengajuan);
        kota = findViewById(R.id.in_kotaTujuan);
        berangkat = findViewById(R.id.in_tanggalBerangkat);
        kembali = findViewById(R.id.in_tanggalKembali);
        pesawat = findViewById(R.id.in_biayaPesawat);
        penginapan = findViewById(R.id.in_biayaPenginapan);
        taksi_bandara = findViewById(R.id.in_biayaTaksi);
        taksi_daerah = findViewById(R.id.in_biayaTaksiDaerah);
        uang_harian = findViewById(R.id.in_uangHarian);
        progressDialog = new ProgressDialog(FormPengajuan.this);
        final String resultAjukan = "ajukan";
        btnAjukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s_nama = nama.getText().toString();
                s_kota = kota.getText().toString();
                s_berangkat = berangkat.getText().toString();
                s_kembali = kembali.getText().toString();
                s_pesawat = pesawat.getText().toString();
                s_penginapan = penginapan.getText().toString();
                s_taksi_bandara = taksi_bandara.getText().toString();
                s_taksi_daerah = taksi_daerah.getText().toString();
                s_uang_harian = uang_harian.getText().toString();

                progressDialog.setMessage("Mohon Tunggu....");
                progressDialog.show();

                if(s_nama.equals("") || s_kota.equals("") || s_berangkat.equals("") || s_kembali.equals("") || s_pesawat.equals("")|| s_penginapan.equals("")||s_taksi_bandara.equals("")
                ||s_taksi_daerah.equals("") || s_uang_harian.equals("")
                ){ progressDialog.dismiss();
                    Toast.makeText(FormPengajuan.this, "Silahkan lengkapi data", Toast.LENGTH_SHORT).show();
                }else{
                    buat_pengaduan(id_user, s_nama, s_kota, s_berangkat, s_kembali, s_pesawat, s_penginapan, s_taksi_bandara, s_taksi_daerah, s_uang_harian);
                }


            }
        });
    }


    public void buat_pengaduan(final String id_user, final String nl, final String kt, final String tb,
                               final String tk,
                               final String bp,
                               final String bp2,
                               final String bt,
                               final String bt2,
                               final String uh ) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressDialog.dismiss();
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("status");

//                    JSONArray jsonArray = jsonObject.getJSONArray("response");
                    if (success.equals("1")) {
                        Intent intent = new Intent(getApplicationContext(), SuksesActivity.class);
                        intent.putExtra("result", "ajukan");
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Gagal, silahkan coba kembali ",
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error  : " + e.toString(),
                            Toast.LENGTH_SHORT)
                            .show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_user", id_user);
                params.put("nama_lengkap", nl);
                params.put("kota_tujuan", kt);
                params.put("tgl_berangkat", tb);
                params.put("tgl_kembali", tk);
                params.put("biaya_pesawat", bp);
                params.put("biaya_penginapan", bp2);
                params.put("biaya_taksi_bandara", bt);
                params.put("biaya_taksi_daerah", bt2);
                params.put("uang_harian", uh);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
