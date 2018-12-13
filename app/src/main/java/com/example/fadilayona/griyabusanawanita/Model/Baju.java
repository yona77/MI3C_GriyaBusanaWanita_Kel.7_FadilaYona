package com.example.fadilayona.griyabusanawanita.Model;

import com.google.gson.annotations.SerializedName;

public class Baju {
    @SerializedName("idBaju")

    private String idBaju;

    @SerializedName("namaBaju")

    private String namaBaju;

    @SerializedName("kategori")

    private String kategori;

    @SerializedName("harga")

    private String harga;

    @SerializedName("gambar")

    private String gambar;

    private String action;

    public Baju(String idBaju, String namaBaju, String kategori, String harga, String gambar, String action) {
        this.idBaju = idBaju;
        this.namaBaju = namaBaju;
        this.kategori = kategori;
        this.harga = harga;
        this.gambar = gambar;
        this.action = action;
    }

    public String getIdBaju() {
        return idBaju;
    }

    public void setIdBaju(String idBaju) {
        this.idBaju = idBaju;
    }

    public String getNamaBaju() {
        return namaBaju;
    }

    public void setNamaBaju(String namaBaju) {
        this.namaBaju = namaBaju;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
