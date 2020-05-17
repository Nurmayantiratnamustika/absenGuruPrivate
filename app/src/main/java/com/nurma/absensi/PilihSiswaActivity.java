package com.nurma.absensi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.nurma.absensi.Service.ApiInterface;
import com.nurma.absensi.adapter.PilihAdapter;
import com.nurma.absensi.adapter.SiswaAdapter;
import com.nurma.absensi.helper.ServiceGenerator;
import com.nurma.absensi.model.Login;
import com.nurma.absensi.model.Siswa;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PilihSiswaActivity extends AppCompatActivity  implements PilihAdapter.ListItemListener{
    ArrayList<Siswa> siswaList;
    private ConstraintLayout mSiswaLayout;
    PilihAdapter pilihAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_siswa);
        siswaList = new ArrayList<>();
        RecyclerView siswaView = findViewById(R.id.rvSiswaPilih);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        siswaView.setLayoutManager(layoutManager);
        pilihAdapter = new PilihAdapter(siswaList,this);
        siswaView.setAdapter(pilihAdapter);
        mSiswaLayout = findViewById(R.id.siswapilihLayout);
        doLoad();
    }

    public void doRecipe() {
        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<List<Siswa>> call = service.listSiswa();
        call.enqueue(new Callback<List<Siswa>>() {
            @Override
            public void onResponse(Call<List<Siswa>> call, Response<List<Siswa>> response) {
                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().size(); i++) {
                        int id = response.body().get(i).getNim();
                        String nama = response.body().get(i).getNama_siswa();
                        siswaList.add(new Siswa(id, nama));
                    }
                    Snackbar snackbar = Snackbar.make(mSiswaLayout, "Memuat data, Mohon ditunggu", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                } else {
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
        siswaList.clear();
        pilihAdapter.notifyDataSetChanged();

        doRecipe();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pilihAdapter.notifyDataSetChanged();
            }
        }, 3000);
    }


    @Override
    public void onListClik(int position) {
        Bundle ekstra = getIntent().getExtras();
        String username= ekstra.getString("username");
        String password= ekstra.getString("password");
        Double latitude = ekstra.getDouble("latitude");
        Double longitude = ekstra.getDouble("longitude");
        String tgllogin= ekstra.getString("tgllogin");
        String jamlogin= ekstra.getString("jamlogin");

        String latitude1 =Double.toString(latitude);
        String longitude1 =Double.toString(longitude);

        int id = siswaList.get(position).getNim();

        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<Login> call = service.doInsert(username,password,latitude1,longitude1,jamlogin,tgllogin,id);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(PilihSiswaActivity.this, "Siswa Dipilih", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(PilihSiswaActivity.this, DetailPilihActivity.class);
                    i.putExtra("id", id);
                    startActivity(i);
                }
            }
            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText( PilihSiswaActivity.this,"Error",Toast.LENGTH_LONG).show();
            }
        });
    }
}

