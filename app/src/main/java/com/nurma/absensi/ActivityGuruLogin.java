package com.nurma.absensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.Marker;
import com.nurma.absensi.Service.ApiInterface;
import com.nurma.absensi.helper.ServiceGenerator;
import com.nurma.absensi.model.Login;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityGuruLogin extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView tvjam,tvtanggal,tvLongitude,tvLatitude;
    private Button map,logout,riwayat;
    String id,username,password,tglLogin,jamLogin;
    Double latitude,longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guru_login);
        tvjam= findViewById(R.id.tvJamLogin);
        tvtanggal = findViewById(R.id.tvTanggal);
        tvLatitude= findViewById(R.id.tvLatitude);
        tvLongitude=findViewById(R.id.tvLongitude);
        logout = findViewById(R.id.buttonlogout);
        riwayat = findViewById(R.id.buttonriwayat);
        map = findViewById(R.id.btnmap);

        Date dt=new Date();
        SimpleDateFormat jam = new SimpleDateFormat("kk:mm:ss");
        SimpleDateFormat tgl = new SimpleDateFormat(" yyyy-MM-dd");
        tglLogin=tgl.format(dt);
        jamLogin = jam.format(dt);

        Bundle ekstra = getIntent().getExtras();
        id = ekstra.getString("id");
        username = ekstra.getString("username");
        password = ekstra.getString("password");
        latitude = ekstra.getDouble("latitude");
        longitude = ekstra.getDouble("longitude");


        Double lat = latitude;
        Double longi = longitude;

        tvtanggal.setText(tglLogin);
        tvjam.setText(jamLogin);
        tvLatitude.setText(Double.toString(latitude));
        tvLongitude.setText(Double.toString(longitude));
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivityGuruLogin.this,MapsActivity.class);
                startActivity(i);
                i.putExtra("latitude",lat);
                i.putExtra("longitude",longi);
                startActivity(i);
            }
        });
    }



    public void handleLogout(View view) {

        Date dt=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("kk:mm:ss");

        String tgl_jam=sdf.format(dt);
        ApiInterface service =ServiceGenerator.createService(ApiInterface.class);
        Call<Login> call = service.updateLogin(jamLogin,tgl_jam);
        call.enqueue(new Callback<Login>() {

            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ActivityGuruLogin.this, "Berhasil", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ActivityGuruLogin.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(ActivityGuruLogin.this, "Gagal", Toast.LENGTH_SHORT).show();

            }
        });
    }


    public void handleRiwayat(View view) {
        String u = username;
        String p = password;
        Intent i = new Intent(ActivityGuruLogin.this,GuruActivity.class);
        i.putExtra("username",u);
        i.putExtra("password",p);
        startActivity(i);

    }

    public void handleSiswa(View view) {
        Intent i = new Intent(ActivityGuruLogin.this,PilihSiswaActivity.class);
        i.putExtra("username",username);
        i.putExtra("password",password);
        i.putExtra("latitude",latitude);
        i.putExtra("longitude",longitude);
        i.putExtra("jamlogin",jamLogin);
        i.putExtra("tgllogin",tglLogin);
        startActivity(i);
    }
}


