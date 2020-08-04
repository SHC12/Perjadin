package com.mobile.laporperjadin.umum;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.mobile.laporperjadin.R;
import com.mobile.laporperjadin.SuksesActivity;
import com.mobile.laporperjadin.adapter.TableAdapter;
import com.mobile.laporperjadin.api.ApiClient;
import com.mobile.laporperjadin.api.ApiInterface;
import com.mobile.laporperjadin.model.Pengajuan;
import com.mobile.laporperjadin.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import me.abhinay.input.CurrencyEditText;
import me.abhinay.input.CurrencySymbols;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class FormPengajuan extends AppCompatActivity implements View.OnClickListener {
    public static final String PREFS_NAME = "MyPrefsFile";
    public static String URL = "http://muhyudi.my.id/api_android/buat_pengajuan.php";
    public static String URLNAMA = "http://muhyudi.my.id/api_android/get_nama.php";
    public static String URLKOTA = "http://muhyudi.my.id/api_android/get_kota.php";
    MaterialButton btnAjukan;
    CurrencyEditText pesawat, penginapan, taksi_bandara, taksi_daerah, uang_harian;
    EditText  berangkat, kembali;
    String s_nama, s_kota, s_berangkat, s_kembali, s_pesawat, s_penginapan, s_taksi_bandara, s_taksi_daerah, s_uang_harian;
    String id_user;
    AutoCompleteTextView nama, kota;
    private ProgressDialog progressDialog;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat simpleDateFormat;
    private ArrayList<String> namaList = new ArrayList<>();
    private ArrayList<String> kotaList = new ArrayList<>();
    private List<User> dataUserList = new ArrayList<>();
    String id_users;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pengajuan);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        btnAjukan = findViewById(R.id.btnAjukanUmum);
        nama = findViewById(R.id.in_namePengajuan);
        kota = findViewById(R.id.in_kotaTujuan);
        berangkat = findViewById(R.id.in_tanggalBerangkat);
        berangkat.setOnClickListener(this);
        kembali = findViewById(R.id.in_tanggalKembali);
        kembali.setOnClickListener(this);
        pesawat = findViewById(R.id.in_biayaPesawat);
        setEdt(pesawat);
        penginapan = findViewById(R.id.in_biayaPenginapan);
        setEdt(penginapan);
        taksi_bandara = findViewById(R.id.in_biayaTaksi);
        setEdt(taksi_bandara);
        taksi_daerah = findViewById(R.id.in_biayaTaksiDaerah);
        setEdt(taksi_daerah);
        uang_harian = findViewById(R.id.in_uangHarian);
        setEdt(uang_harian);

        nama.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
         id_users =  dataUserList.get(position).getIdUser();
            }
        });
        progressDialog = new ProgressDialog(FormPengajuan.this);

        btnAjukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s_nama = nama.getText().toString();
                s_kota = kota.getText().toString();
                s_berangkat = berangkat.getText().toString();
                s_kembali = kembali.getText().toString();
                s_pesawat = "" + pesawat.getCleanIntValue();
                s_penginapan = "" + penginapan.getCleanIntValue();
                s_taksi_bandara = "" + taksi_bandara.getCleanIntValue();
                s_taksi_daerah = "" + taksi_daerah.getCleanIntValue();
                s_uang_harian = "" + uang_harian.getCleanIntValue();


                progressDialog.setMessage("Mohon Tunggu....");
                progressDialog.show();

                if (s_nama.equals("") || s_kota.equals("") || s_berangkat.equals("") || s_kembali.equals("") || s_pesawat.equals("") || s_penginapan.equals("") || s_taksi_bandara.equals("")
                        || s_taksi_daerah.equals("") || s_uang_harian.equals("")
                ) {
                    progressDialog.dismiss();
                    Toast.makeText(FormPengajuan.this, "Silahkan lengkapi data", Toast.LENGTH_SHORT).show();
                } else {
                    buat_pengaduan(id_users, s_nama, s_kota, s_berangkat, s_kembali, s_pesawat, s_penginapan, s_taksi_bandara, s_taksi_daerah, s_uang_harian);
                }


            }
        });

        getNama(nama);
        getKota(kota);
    }

    private void getNama(final AutoCompleteTextView target){
        Call<ResponseBody> getNama = apiInterface.getNama();
        getNama.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    try {
                        JSONObject o = new JSONObject(response.body().string());
                        JSONArray a = o.getJSONArray("nama");
                        for(int i = 0; i<a.length();i++){


                            JSONObject ao = a.getJSONObject(i);


                            namaList.add(ao.getString("nama_lengkap"));
                            dataUserList.add(new User(ao.getString("id_user"), ao.getString("nama_lengkap")));
                        }
                        getSpinnerAPI(target,namaList);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void getKota(final AutoCompleteTextView target){
        Call<ResponseBody> getKota = apiInterface.getKota();
        getKota.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    try {
                        JSONObject o = new JSONObject(response.body().string());
                        JSONArray a = o.getJSONArray("kota");
                        for(int i = 0; i<a.length();i++){
                            JSONObject ao = a.getJSONObject(i);
                            kotaList.add(ao.getString("nama_kota"));
                        }
                        getSpinnerAPI(target,kotaList);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void setEdt(CurrencyEditText target) {
        target.setCurrency(CurrencySymbols.INDONESIA);
        target.setDecimals(true);
        target.setSeparator(".");
    }
    private void getSpinnerAPI(AutoCompleteTextView target, ArrayList<String> item) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_list_item, item);
        target.setAdapter(adapter);
    }

    private void showDateDialog(final EditText edt_target) {

        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {


                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                edt_target.setText(simpleDateFormat.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }


    public void buat_pengaduan(final String id_user, final String nl, final String kt, final String tb,
                               final String tk,
                               final String bp,
                               final String bp2,
                               final String bt,
                               final String bt2,
                               final String uh) {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.in_tanggalBerangkat:
                showDateDialog(berangkat);
                break;
            case R.id.in_tanggalKembali:
                showDateDialog(kembali);
                break;
        }
    }
}
