package com.nurma.absensi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.nurma.absensi.Service.ApiInterface;
import com.nurma.absensi.helper.ServiceGenerator;
import com.nurma.absensi.model.Rekap;
import com.nurma.absensi.model.Siswa;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPilihActivity extends AppCompatActivity {
    TextView tvAlamat,tvJK,tvTtl,tvkelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pilih);
        tvAlamat = findViewById(R.id.detPilAlamat);
        tvJK = findViewById(R.id.detPilJk);
        tvTtl = findViewById(R.id.detPilTtl);
        tvkelas = findViewById(R.id.detPilKelas);

        Bundle ekstra = getIntent().getExtras();
        int nim= ekstra.getInt("id");

        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<Siswa> call = service.getSiswaId(nim);
        call.enqueue(new Callback <Siswa>() {
            @Override
            public void onResponse(Call<Siswa> call, Response<Siswa> response) {
                tvAlamat.setText(response.body().getAlamat_siswa());
                tvJK.setText(response.body().getJenis_kelamin());
                tvTtl.setText(response.body().getTanggal_lahir());
                tvkelas.setText(response.body().getKelas());
            }

            @Override
            public void onFailure(Call<Siswa> call, Throwable t) {
                Toast.makeText( DetailPilihActivity.this,"Error",Toast.LENGTH_LONG).show();
            }
        });
    }
}
