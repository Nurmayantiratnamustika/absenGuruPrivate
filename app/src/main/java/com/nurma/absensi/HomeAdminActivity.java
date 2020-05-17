package com.nurma.absensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeAdminActivity extends AppCompatActivity {
    private TextView tanggal;
    private Button tambahGuru,tambahSiswa,rekapLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        tanggal = findViewById(R.id.tvTanggal);
        tambahGuru = findViewById(R.id.buttontambahGuru);
        tambahSiswa = findViewById(R.id.buttontambahSiswa);
        rekapLogin = findViewById(R.id.buttonrekapLogin);
    }

    public void handleGuru(View view) {
        Intent i = new Intent(HomeAdminActivity.this,UserActivity.class);
        startActivity(i);
    }

    public void handleSiswa(View view) {
        Intent i = new Intent(HomeAdminActivity.this,SiswaActivity.class);
        startActivity(i);
    }

    public void handleRekap(View view) {
        Intent i = new Intent(HomeAdminActivity.this,RekapAbsenActivity.class);
        startActivity(i);
    }
}
