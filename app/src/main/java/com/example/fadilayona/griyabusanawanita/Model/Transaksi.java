package com.example.fadilayona.griyabusanawanita.Model;

import com.google.gson.annotations.SerializedName;

public class Transaksi {
    @SerializedName("id_transaksi")
    private String idTransaksi;
    @SerializedName("id_Pembeli")
    private String idPembeli;
    @SerializedName("id_Baju")
    private String idBaju;
    @SerializedName("totalharga")
    private String totalharga;

    public Transaksi(String id_transaksi, String id_pembeli, String id_baju,  String totalharga) {
        this.idTransaksi = id_transaksi;
        this.idPembeli = id_pembeli;
        this.idBaju = id_baju;
        this.totalharga = totalharga;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getIdPembeli() {
        return idPembeli;
    }

    public void setIdPembeli(String idPembeli) {
        this.idPembeli = idPembeli;
    }

    public String getIdBaju() {
        return idBaju;
    }

    public void setIdBaju(String idBaju) {
        this.idBaju = idBaju;
    }

    public String getTotalharga() {
        return totalharga;
    }

    public void setTotalharga(String totalharga) {
        this.totalharga = totalharga;
    }
}

