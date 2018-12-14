package com.example.fadilayona.griyabusanawanita.Model;

import com.google.gson.annotations.SerializedName;

public class Baju {
    @SerializedName("id_baju")
    private String idBaju;
    @SerializedName("namabaju")
    private String namabaju;
    @SerializedName("kategori")
    private String kategori;
    @SerializedName("harga")
    private int harga;
    @SerializedName("photo_url")
    private String photoUrl;
    private String action;

    public Baju(String idBaju, String namabaju, String kategori, int harga, String photoUrl, String
            action) {
        this.idBaju = idBaju;
        this.namabaju = namabaju;
        this.kategori = kategori;
        this.harga = harga;
        this.photoUrl = photoUrl;
        this.action = action;
    }

    public String getIdBaju() {
        return idBaju;
    }

    public void setIdBaju(String idBaju) {
        this.idBaju = idBaju;
    }

    public String getNamabaju() {
        return namabaju;
    }

    public void setNamabaju(String namabaju) {
        this.namabaju = namabaju;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getAction() { return action; }

    public void setAction(String action) {
        this.action = action;
    }

}
