package com.example.fadilayona.griyabusanawanita.Model;
import com.google.gson.annotations.SerializedName;

public class Transaksi {
    @SerializedName ( "id_transaksi" )
    private int id_transaksi;

    @SerializedName ( "id_pembeli" )
    private int id_pembeli;

    @SerializedName ( "total_harga" )
    private int total_harga;

    @SerializedName ( "id_baju" )
    private int id_baju;

    public Transaksi(int id_transaksi, int id_pembeli, int total_harga, int id_baju) {
        this.id_transaksi = id_transaksi;
        this.id_pembeli = id_pembeli;
        this.total_harga = total_harga;
        this.id_baju = id_baju;
    }

    public int getId_transaksi() {
        return id_transaksi;
    }
    public void setId_transaksi(int id_transaksi){
        this.id_transaksi = id_transaksi;
    }

    public int getId_pembeli() {
        return id_pembeli;
    }
    public void setId_pembeli(int id_pembeli) {
        this.id_pembeli = id_pembeli;
    }

    public int getTotal_harga() {
        return total_harga;
    }
    public void setTotal_harga(int total_harga) {
        this.total_harga = total_harga;
    }

    public int getId_baju() {
        return id_baju;
    }
    public void setId_baju(int id_baju) {
        this.id_baju = id_baju;
    }
}

