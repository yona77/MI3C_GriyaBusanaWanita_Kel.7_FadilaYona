package com.example.fadilayona.griyabusanawanita.Model;

import com.google.gson.annotations.SerializedName;

public class Pembeli {
    @SerializedName("id_pembeli")
    private int idPembeli;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("nama")
    private String nama;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("telp")
    private String telp;

    private String action;

    public Pembeli(int idPembeli, String username, String password, String nama, String alamat, String telp, String action) {
        this.idPembeli = idPembeli;
        this.username   = username;
        this.password   = password;
        this.nama      = nama;
        this.alamat    = alamat;
        this.telp      = telp;
        this.action    = action;
    }

    public int getIdPembeli() {
        return idPembeli;
    }
    public void setIdPembeli(int idPembeli) {
        this.idPembeli = idPembeli;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
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

    public String getTelp() {
        return telp;
    }
    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }

    }


