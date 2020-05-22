package com.mobile.laporperjadin.umum;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.mobile.laporperjadin.R;
import com.mobile.laporperjadin.adapter.TableAdapter;
import com.mobile.laporperjadin.model.Pengajuan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RiwayatPengajuanActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
    RecyclerView recyclerView;
    TableAdapter adapter;
    String URL_LENGKAP;
    String id_user;
    MaterialButton btnExport;
    ArrayList<Pengajuan> listData;
    private String URL = "http://muhyudi.my.id/api_android/get_pengajuan.php?id_user=";
    private TextView rvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_pengajuan);

        btnExport = findViewById(R.id.btnIntentPdfPengajuanKabid);


        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        id_user = settings.getString("id_user", "default");
        URL_LENGKAP = URL + id_user;
        recyclerView = findViewById(R.id.rvRiwayatPengajuanUmum);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        listData = new ArrayList<Pengajuan>();
        getRiwayatUmum();
        rvEmpty = findViewById(R.id.rv_empty);


    }

    private void getRiwayatUmum() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL_LENGKAP, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response.length() > 0) {
                    for (int i = 0; i < response.length(); i++) {
                        try {

                            JSONObject jsonObject = response.getJSONObject(i);


                            Pengajuan data = new Pengajuan();
                            data.setNo(jsonObject.getString("no"));
                            data.setId(jsonObject.getString("id_pengajuan"));
                            data.setId_user(jsonObject.getString("id_user"));
                            data.setNama(jsonObject.getString("nama_lengkap"));
                            data.setKotaTujuan(jsonObject.getString("kota_tujuan"));
                            data.setTglBerangkat(jsonObject.getString("tanggal_berangkat"));
                            data.setTglKembali(jsonObject.getString("tanggal_kembali"));
                            data.setBiayaPesawat(jsonObject.getString("biaya_pesawat"));
                            data.setBiayaPenginapan(jsonObject.getString("biaya_penginapan"));
                            data.setBiayaTaksiBandara(jsonObject.getString("biaya_taksi_bandara"));
                            data.setBiayaTaksiDaerah(jsonObject.getString("biaya_taksi_daerah"));
                            data.setUangTunai(jsonObject.getString("uang_harian"));
                            data.setTglPengajuan(jsonObject.getString("tanggal_pengajuan"));
                            data.setStatusPengajuan(jsonObject.getString("status"));



                            listData.add(data);
                            if (!listData.isEmpty()) {
                                adapter = new TableAdapter(listData, RiwayatPengajuanActivity.this);
                                recyclerView.setAdapter(adapter);
                                rvEmpty.setVisibility(View.GONE);
                            } else {
                                recyclerView.setVisibility(View.GONE);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }
}
