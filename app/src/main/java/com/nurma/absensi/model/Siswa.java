package com.nurma.absensi.model;

public class Siswa {
    private int nim;
    private String nama_siswa;
    private String alamat_siswa;
    private String jenis_kelamin;
    private String tanggal_lahir;
    private String kelas;

    public Siswa(int nim, String nama_siswa, String alamat_siswa, String jenis_kelamin, String tanggal_lahir, String kelas) {
        this.nim = nim;
        this.nama_siswa = nama_siswa;
        this.alamat_siswa = alamat_siswa;
        this.jenis_kelamin = jenis_kelamin;
        this.tanggal_lahir = tanggal_lahir;
        this.kelas = kelas;
    }

    public  Siswa(int nim,String nama_siswa){
        this.nim = nim;
        this.nama_siswa = nama_siswa;
    }

    public int getNim() {
        return nim;
    }

    public void setNim(int nim) {
        this.nim = nim;
    }

    public String getNama_siswa() {
        return nama_siswa;
    }

    public void setNama_siswa(String nama_siswa) {
        this.nama_siswa = nama_siswa;
    }

    public String getAlamat_siswa() {
        return alamat_siswa;
    }

    public void setAlamat_siswa(String alamat_siswa) {
        this.alamat_siswa = alamat_siswa;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }
}
