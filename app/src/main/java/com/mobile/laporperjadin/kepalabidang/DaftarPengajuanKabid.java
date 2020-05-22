package com.mobile.laporperjadin.kepalabidang;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.interfaces.DownloadProgressListener;
import com.mobile.laporperjadin.R;
import com.mobile.laporperjadin.adapter.TableAdapter;
import com.mobile.laporperjadin.model.Pengajuan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DaftarPengajuanKabid extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
    RecyclerView recyclerView;
    TableAdapter adapter;
    String id_user;
    ArrayList<Pengajuan> listData;
    private String url_export_riwayat = "http://muhyudi.my.id/api_android/export_riwayat_pengajuan.php";
    private String URL = "http://muhyudi.my.id/api_android/get_pengajuan_admin.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_pengajuan);
        recyclerView = findViewById(R.id.rvDaftarKabid);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        listData = new ArrayList<Pengajuan>();
        getRiwayatUmum();


    }


    private void getRiwayatUmum() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
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


/*
                            Intent intent = new Intent(getActivity().getBaseContext(), DetailDisposisiMasuk.class);
                            intent.putExtra("id_surat", id_surat);
                            getActivity().startActivity(intent);
*/


                            listData.add(data);
                            adapter = new TableAdapter(listData, DaftarPengajuanKabid.this);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } else {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }

    public void toExportRiwayat(View view) {
        download_riwayat_pdf(url_export_riwayat);
    }

    public void download_riwayat_pdf(String aUrl) {
        AndroidNetworking.download(aUrl, "/storage/emulated/0/Download/", "riwayat_perjadin.pdf")
                .setTag("downloadTest")
                .setPriority(Priority.MEDIUM)
                .addHeaders("Authorization", "Basic YnNyZTpzZWN1cmV0cmFuc2FjdGlvbnMhISE=")
                .build()
                .setDownloadProgressListener(new DownloadProgressListener() {
                    @Override
                    public void onProgress(long bytesDownloaded, long totalBytes) {
                        Toast.makeText(DaftarPengajuanKabid.this, "Mengunduh File", Toast.LENGTH_SHORT).show();
                        // do anything with progress
                    }
                })
                .startDownload(new DownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        // do anything after completion
//                        progressDialog.dismiss();
                        Toast.makeText(DaftarPengajuanKabid.this, "Unduhan Telah Selesai", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }
}
