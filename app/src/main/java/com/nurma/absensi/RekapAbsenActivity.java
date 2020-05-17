package com.nurma.absensi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nurma.absensi.Service.ApiInterface;
import com.nurma.absensi.adapter.LoginAdapter;
import com.nurma.absensi.adapter.RekapAdapter;
import com.nurma.absensi.helper.ServiceGenerator;
import com.nurma.absensi.model.Login;
import com.nurma.absensi.model.Rekap;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RekapAbsenActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Rekap> rekapList;
    private RekapAdapter rekapAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekap_absen);
        recyclerView = findViewById(R.id.rvRekap);

        rekapList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        rekapAdapter = new RekapAdapter(rekapList,getApplicationContext());
        recyclerView.setAdapter(rekapAdapter);

        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<List<Rekap>> call = service.listRekap();
        call.enqueue(new Callback<List<Rekap>>() {
            @Override
            public void onResponse(Call<List<Rekap>> call, Response<List<Rekap>> response) {
                rekapList = response.body();
                rekapAdapter.setRekapList(rekapList);
            }
            @Override
            public void onFailure(Call<List<Rekap>> call, Throwable t) {
                Toast.makeText( RekapAbsenActivity.this,"Error",Toast.LENGTH_LONG).show();
            }
        });
    }
}

