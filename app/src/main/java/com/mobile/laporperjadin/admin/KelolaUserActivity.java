package com.mobile.laporperjadin.admin;

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
import com.mobile.laporperjadin.adapter.TableAdapterKelolaUser;
import com.mobile.laporperjadin.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class KelolaUserActivity extends AppCompatActivity {
    private String URL_GET_USER = "http://muhyudi.my.id/api_android/get_user.php";
    private String url_export_user = "http://muhyudi.my.id/api_android/export_user.php";
    private RecyclerView recyclerView;
    private TableAdapterKelolaUser adapter;
    private String resultPdf = "user";
    private ArrayList<User> listUser = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_user);

        recyclerView = findViewById(R.id.rvKelolaUser);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        getUser();

    }

    private void getUser() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL_GET_USER, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response.length() > 0) {
                    for (int i = 0; i < response.length(); i++) {
                        try {

                            JSONObject jsonObject = response.getJSONObject(i);


                            User user = new User(
                                    jsonObject.getString("no"),
                                    jsonObject.getString("id_user"),
                                    jsonObject.getString("nama"),
                                    jsonObject.getString("jabatan"),
                                    jsonObject.getString("nip"),
                                    jsonObject.getString("email"),
                                    jsonObject.getString("username"),
                                    jsonObject.getString("status"),
                                    jsonObject.getString("password")
                            );

/*
                            Intent intent = new Intent(getActivity().getBaseContext(), DetailDisposisiMasuk.class);
                            intent.putExtra("id_surat", id_surat);
                            getActivity().startActivity(intent);
*/


                            listUser.add(user);
                            adapter = new TableAdapterKelolaUser(listUser, KelolaUserActivity.this);
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


    public void expoerUser(View view) {
        download_user_pdf(url_export_user);

    }

    public void download_user_pdf(String aUrl) {
        AndroidNetworking.download(aUrl, "/storage/emulated/0/Download/", "user.pdf")
                .setTag("downloadTest")
                .setPriority(Priority.MEDIUM)
                .addHeaders("Authorization", "Basic YnNyZTpzZWN1cmV0cmFuc2FjdGlvbnMhISE=")
                .build()
                .setDownloadProgressListener(new DownloadProgressListener() {
                    @Override
                    public void onProgress(long bytesDownloaded, long totalBytes) {
                        Toast.makeText(KelolaUserActivity.this, "Mengunduh File", Toast.LENGTH_SHORT).show();
                        // do anything with progress
                    }
                })
                .startDownload(new DownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        // do anything after completion
//                        progressDialog.dismiss();
                        Toast.makeText(KelolaUserActivity.this, "Unduhan Telah Selesai", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }
}
