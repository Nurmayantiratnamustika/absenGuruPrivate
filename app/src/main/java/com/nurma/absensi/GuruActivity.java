package com.nurma.absensi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nurma.absensi.Service.ApiInterface;
import com.nurma.absensi.adapter.LoginAdapter;
import com.nurma.absensi.helper.ServiceGenerator;
import com.nurma.absensi.model.Guru;
import com.nurma.absensi.model.Login;
import com.nurma.absensi.model.LoginResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuruActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<LoginResponse> loginList;
    private LoginAdapter loginAdapter;
    Button logout;
    String username;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guru);
        recyclerView = findViewById(R.id.rvLogin);

        loginList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        loginAdapter = new LoginAdapter(loginList,getApplicationContext());
        recyclerView.setAdapter(loginAdapter);
        Bundle ekstra = getIntent().getExtras();
        username= ekstra.getString("username");
        String password = ekstra.getString("password");
        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<List<LoginResponse>> call = service.listLogin(username,password);
        call.enqueue(new Callback<List<LoginResponse>>() {
            @Override
            public void onResponse(Call<List<LoginResponse>> call, Response<List<LoginResponse>> response) {
                loginList = response.body();
                loginAdapter.setLoginList(loginList);

            }

            @Override
            public void onFailure(Call<List<LoginResponse>> call, Throwable t) {
                Toast.makeText( GuruActivity.this,"Error",Toast.LENGTH_LONG).show();
            }
        });
    }

}
