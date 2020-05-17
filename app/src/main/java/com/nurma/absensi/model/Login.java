package com.nurma.absensi.model;

import android.content.Intent;

import com.google.gson.annotations.SerializedName;

public class Login {
    @SerializedName("id_login")
    private String id_login;

    @SerializedName("unique_id")
    private String unique_id;

    @SerializedName("username")
    private String username;

    @SerializedName("jam_login")
    private String jam_login;

    @SerializedName("jam_logout")
    private String jam_logout;

    @SerializedName("tanggal")
    private String tanggal;

    @SerializedName("password")
    private  String password;

    @SerializedName("latitude")
    private  Double latitude;

    @SerializedName("longitude")
    private  Double longitude;

    @SerializedName("nim_siswa")
    private  int nim_siswa;

    public Integer getNim_siswa() {
        return nim_siswa;
    }

    public void setNim_siswa(int nim_siswa) {
        this.nim_siswa = nim_siswa;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getJam_login() {
        return jam_login;
    }

    public void setJam_login(String jam_login) {
        this.jam_login = jam_login;
    }

    public String getJam_logout() {
        return jam_logout;
    }

    public String getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(String unique_id) {
        this.unique_id = unique_id;
    }

    public void setJam_logout(String jam_logout) {
        this.jam_logout = jam_logout;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getId_login() {
        return id_login;
    }

    public void setId_login(String id_login) {
        this.id_login = id_login;
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
}
