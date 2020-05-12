package com.nurma.absensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nurma.absensi.Service.ApiInterface;
import com.nurma.absensi.helper.ServiceGenerator;
import com.nurma.absensi.model.Login;
import com.nurma.absensi.model.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button loginButton;
    EditText edtUsername, edtPassword;
    String username, password;
    Login login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = findViewById(R.id.btnLogin);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = edtUsername.getText().toString();
                password = edtPassword.getText().toString();
                if (edtUsername.getText().toString().equals("admin") && edtPassword.getText().toString().equals("admin")) {
                    Toast.makeText(MainActivity.this, "Halo admin", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, UserActivity.class);
                    startActivity(i);
                }
                else if (validateLogin(username, password)) {
                    //do login
                    doLogin();
                }
            }
        });
    }

    private boolean validateLogin(String username, String password) {
        if(username == null || username.trim().length() == 0){
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password == null || password.trim().length() == 0){
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void doLogin() {
        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<LoginResponse> call = service.doLogin(username,password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    if(response.body() == null){
                        Toast.makeText(MainActivity.this, "Username doesn't exist", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, response.body().getUnique_id(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this,GuruActivity.class);
                        String id = response.body().getUnique_id();
                        i.putExtra("nama",username);
                        i.putExtra("password",password);
                        i.putExtra("id",id);
                        startActivity(i);
                    }


                    }
                }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}


