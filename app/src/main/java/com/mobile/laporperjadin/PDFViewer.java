package com.mobile.laporperjadin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class PDFViewer extends AppCompatActivity {
    PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_d_f_viewer);

        pdfView = findViewById(R.id.pdfViewer);
        String resultPDF = getIntent().getStringExtra("pdfIntentPdf");
        if(resultPDF.equals("user")){
            pdfView.fromAsset("daftaruser.pdf").load();
        }
        if (resultPDF.equals("pengajuan")){
            pdfView.fromAsset("daftarpengajuan.pdf").load();

        }

    }
}
