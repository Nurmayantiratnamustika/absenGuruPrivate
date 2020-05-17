package com.nurma.absensi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.nurma.absensi.Service.ApiInterface;
import com.nurma.absensi.adapter.GuruAdapter;
import com.nurma.absensi.adapter.SiswaAdapter;
import com.nurma.absensi.helper.ServiceGenerator;
import com.nurma.absensi.model.Guru;
import com.nurma.absensi.model.Siswa;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SiswaActivity extends AppCompatActivity {
    ArrayList<Siswa> siswa;
    SiswaAdapter adapter;
    private ConstraintLayout mSiswaLayout;
    Button tambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siswa);
        tambah = findViewById(R.id.buttontbhSiswa);
        siswa = new ArrayList<>();
        RecyclerView siswaView = findViewById(R.id.rvSiswa);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        siswaView.setLayoutManager(layoutManager);
        adapter = new SiswaAdapter(siswa);
        siswaView.setAdapter(adapter);
        mSiswaLayout = findViewById(R.id.siswaLayout);
        doLoad();

    }

    public void doRecipe() {
        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<List<Siswa>> call = service.listSiswa();
        call.enqueue(new Callback<List<Siswa>>() {
            @Override
            public void onResponse(Call<List<Siswa>> call, Response<List<Siswa>> response) {
                if (response.isSuccessful()) {
                    for (int i = 0; i <response.body().size(); i++) {
                        int id = response.body().get(i).getNim();
                        String nama = response.body().get(i).getNama_siswa();
                        String alamat = response.body().get(i).getAlamat_siswa();
                        String jenisKelamin = response.body().get(i).getJenis_kelamin();
                        String ttl = response.body().get(i).getTanggal_lahir();
                        String kelas = response.body().get(i).getKelas();

                        siswa.add(new Siswa(id, nama, alamat, jenisKelamin, ttl,kelas));
                    }
                    Snackbar snackbar = Snackbar.make(mSiswaLayout, "Memuat data, Mohon ditunggu", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }else {
                    Snackbar snackbar = Snackbar.make(mSiswaLayout, "Gagal mengambil data", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }

            @Override
            public void onFailure(Call<List<Siswa>> call, Throwable t) {
                Snackbar snackbar = Snackbar.make(mSiswaLayout, "Gagal koneksi", Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });
    }

    public void doLoad() {
        siswa.clear();
        adapter.notifyDataSetChanged();

        doRecipe();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        }, 3000);
    }

    public void handleUpload(View view) {
        Intent i = new Intent(SiswaActivity.this,AddSiswaActivity.class);
        startActivity(i);
    }
}

