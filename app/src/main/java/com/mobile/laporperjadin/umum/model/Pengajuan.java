package com.mobile.laporperjadin.umum.model;

public class Pengajuan {
    private String id;
    private String nama;
    private String kotaTujuan;
    private String tglBerangkat;
    private String tglKembali;
    private String biayaPesawat;
    private String biayaPenginapan;
    private String biayaTaksiBandara;
    private String biayaTaksiDaerah;
    private String uangTunai;
    private String statusPengajuan;

    public Pengajuan(String id, String nama, String kotaTujuan, String tglBerangkat, String tglKembali, String biayaPesawat, String biayaPenginapan, String biayaTaksiBandara, String biayaTaksiDaerah, String uangTunai, String statusPengajuan, String no) {
        this.id = id;
        this.nama = nama;
        this.kotaTujuan = kotaTujuan;
        this.tglBerangkat = tglBerangkat;
        this.tglKembali = tglKembali;
        this.biayaPesawat = biayaPesawat;
        this.biayaPenginapan = biayaPenginapan;
        this.biayaTaksiBandara = biayaTaksiBandara;
        this.biayaTaksiDaerah = biayaTaksiDaerah;
        this.uangTunai = uangTunai;
        this.statusPengajuan = statusPengajuan;
        this.no = no;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    private String no;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKotaTujuan() {
        return kotaTujuan;
    }

    public void setKotaTujuan(String kotaTujuan) {
        this.kotaTujuan = kotaTujuan;
    }

    public String getTglBerangkat() {
        return tglBerangkat;
    }

    public void setTglBerangkat(String tglBerangkat) {
        this.tglBerangkat = tglBerangkat;
    }

    public String getTglKembali() {
        return tglKembali;
    }

    public void setTglKembali(String tglKembali) {
        this.tglKembali = tglKembali;
    }

    public String getBiayaPesawat() {
        return biayaPesawat;
    }

    public void setBiayaPesawat(String biayaPesawat) {
        this.biayaPesawat = biayaPesawat;
    }

    public String getBiayaPenginapan() {
        return biayaPenginapan;
    }

    public void setBiayaPenginapan(String biayaPenginapan) {
        this.biayaPenginapan = biayaPenginapan;
    }

    public String getBiayaTaksiBandara() {
        return biayaTaksiBandara;
    }

    public void setBiayaTaksiBandara(String biayaTaksiBandara) {
        this.biayaTaksiBandara = biayaTaksiBandara;
    }

    public String getBiayaTaksiDaerah() {
        return biayaTaksiDaerah;
    }

    public void setBiayaTaksiDaerah(String biayaTaksiDaerah) {
        this.biayaTaksiDaerah = biayaTaksiDaerah;
    }

    public String getUangTunai() {
        return uangTunai;
    }

    public void setUangTunai(String uangTunai) {
        this.uangTunai = uangTunai;
    }

    public String getStatusPengajuan() {
        return statusPengajuan;
    }

    public void setStatusPengajuan(String statusPengajuan) {
        this.statusPengajuan = statusPengajuan;
    }

}
