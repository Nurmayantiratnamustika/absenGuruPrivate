package com.nurma.absensi.model;

public class Rekap {
    private String username;
    private String password;
    private String rekap;

    public Rekap(String username, String password, String rekap) {
        this.username = username;
        this.password = password;
        this.rekap = rekap;
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

    public String getRekap() {
        return rekap;
    }

    public void setRekap(String rekap) {
        this.rekap = rekap;
    }
}
