package com.nurma.absensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.nurma.absensi.Service.ApiInterface;
import com.nurma.absensi.helper.ServiceGenerator;
import com.nurma.absensi.model.Siswa;

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSiswaActivity extends AppCompatActivity {
    private String nim, nama, alamat, jk,ttl,kelas;
    private EditText nimTxt,namaTxt, alamatTxt, ttlTxt,kelasTxt;
    private RadioGroup radioGroup;
    private RadioButton selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_siswa);
        nimTxt = findViewById(R.id.edtNimSiswa);
        namaTxt = findViewById(R.id.edtNama);
        alamatTxt = findViewById(R.id.edtalamat);
        ttlTxt = findViewById(R.id.edtttl);
        kelasTxt= findViewById(R.id.edtKelas);
        radioGroup = findViewById(R.id.group_jk);
    }
    public void doUpload(){
        nim = nimTxt.getText().toString();
        nama = namaTxt.getText().toString();
        alamat = alamatTxt.getText().toString();
        selected = findViewById(radioGroup.getCheckedRadioButtonId());
        jk= "";
        if (selected != null) {
            jk = selected.getText().toString();
        }
        ttl = ttlTxt.getText().toString();
        kelas =kelasTxt.getText().toString();

        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<Siswa> call = service.uploadSiswa(nim,nama,alamat,jk,ttl,kelas);
        call.enqueue(new Callback<Siswa>() {
            @Override
            public void onResponse(Call<Siswa> call, Response<Siswa> response) {
                if (response.isSuccessful()){
                    Toast.makeText(AddSiswaActivity.this, "Upload sukses", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(AddSiswaActivity.this,SiswaActivity.class);
                    startActivity(i);
                }
                else{
                    if(nim == null || nim.trim().length() == 0){
                        Toast.makeText(AddSiswaActivity.this, "nim is required", Toast.LENGTH_SHORT).show();
                    }
                    else if(nama == null || nama.trim().length() == 0){
                        Toast.makeText(AddSiswaActivity.this, "nama is required", Toast.LENGTH_SHORT).show();
                    }
                    else if(alamat == null || alamat.trim().length() == 0){
                        Toast.makeText(AddSiswaActivity.this, "alamat is required", Toast.LENGTH_SHORT).show();
                    }
                    else if (ttl == null || ttl.trim().length() == 0){
                        Toast.makeText(AddSiswaActivity.this, "tanggal lahir is required", Toast.LENGTH_SHORT).show();
                    }
                    else if (kelas == null || kelas.trim().length() == 0){
                        Toast.makeText(AddSiswaActivity.this, "username is required", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<Siswa> call, Throwable t) {
                Toast.makeText(AddSiswaActivity.this, "Koneksi gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void handleUploadBaru(View view) {
            doUpload();
    }

}
