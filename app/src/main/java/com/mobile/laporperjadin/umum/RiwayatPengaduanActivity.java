package com.mobile.laporperjadin.umum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.mobile.laporperjadin.R;
import com.mobile.laporperjadin.umum.adapter.TableAdapter;
import com.mobile.laporperjadin.umum.model.Pengajuan;

import java.util.ArrayList;
import java.util.List;

public class RiwayatPengaduanActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TableAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_pengaduan);

        recyclerView = findViewById(R.id.rvRiwayatPengajuanUmum);
        adapter = new TableAdapter(getRiwayat());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private List<Pengajuan> getRiwayat() {
        List<Pengajuan> pengajuans = new ArrayList<>();
        pengajuans.add(new Pengajuan("JD001","Arifin","Bandung","20 Mei","22 Mei","1000000","500000","100000","40000","1000000","Disetujui","1"));
        pengajuans.add(new Pengajuan("JD002","Dedek","Bandung","20 Mei","22 Mei","1000000","500000","100000","40000","1000000","Disetujui","2"));
        return pengajuans;
    }
}
