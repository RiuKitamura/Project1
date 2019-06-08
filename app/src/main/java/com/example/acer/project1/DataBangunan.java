package com.example.acer.project1;

public class DataBangunan {
    private int kode;
    private String nama_bangunan;
    private int jml_lantai;
    private int thn_dibuat;
    private String alamat_bangunan;
    private double latitude, longitude;
    private String nama;
    private String alamat;
    private int hp;

    public DataBangunan()
    {

    }

    public long getKode() {
        return kode;
    }

    public void setId(int kode) {
        this.kode = kode;
    }

    public String getNama_bangunan() {
        return nama_bangunan;
    }

    public void setNama_bangunan(String nama_bangunan) {
        this.nama_bangunan = nama_bangunan;
    }

    public int getJml_lantai() {
        return jml_lantai;
    }

    public void setJml_lantai(int jml_lantai) {
        this.jml_lantai = jml_lantai;
    }

    public int getThn_dibuat() {
        return thn_dibuat;
    }

    public void setThn_dibuat(int thn_dibuat) {
        this.thn_dibuat = thn_dibuat;
    }

    public String getAlamat_bangunan() {
        return alamat_bangunan;
    }

    public void setAlamat_bangunan(String alamat_bangunan) {
        this.alamat_bangunan = alamat_bangunan;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }



    @Override
    public String toString()
    {
        return "Kode "+ kode +" "+ nama_bangunan + " "+ alamat_bangunan;
    }
}
