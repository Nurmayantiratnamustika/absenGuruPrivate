package com.nurma.absensi.model;

import com.google.gson.annotations.SerializedName;

public class RekapDetail {

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


        @SerializedName("nama_siswa")
        private  String nama_siswa;

        public String getNama_siswa() {
        return nama_siswa;
          }

          public void setNama_siswa(String nama_siswa) {
            this.nama_siswa = nama_siswa;
            }

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


