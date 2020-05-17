package com.nurma.absensi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.nurma.absensi.Service.ApiInterface;
import com.nurma.absensi.adapter.DetailRekapAdapter;
import com.nurma.absensi.adapter.LoginAdapter;
import com.nurma.absensi.helper.ServiceGenerator;
import com.nurma.absensi.model.Login;
import com.nurma.absensi.model.LoginResponse;
import com.nurma.absensi.model.RekapDetail;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailRekapActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<RekapDetail> detailList;
    private DetailRekapAdapter detailRekapAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_rekap);
        recyclerView = findViewById(R.id.rvDetail);

        detailList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        detailRekapAdapter = new DetailRekapAdapter(detailList,getApplicationContext());
        recyclerView.setAdapter(detailRekapAdapter);
        Intent mIntent = getIntent();
        String username = mIntent.getStringExtra("username");
        String password = mIntent.getStringExtra("password");
        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<List<RekapDetail>> call = service.listRekapDetail(username,password);
        call.enqueue(new Callback<List<RekapDetail>>() {
            @Override
            public void onResponse(Call<List<RekapDetail>> call, Response<List<RekapDetail>> response) {
                detailList = response.body();
                detailRekapAdapter.setDetailList(detailList);
            }

            @Override
            public void onFailure(Call<List<RekapDetail>> call, Throwable t) {
                Toast.makeText( DetailRekapActivity.this,"Error",Toast.LENGTH_LONG).show();
            }
        });
    }
}


