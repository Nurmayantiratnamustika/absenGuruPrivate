package com.nurma.absensi.Service;


import com.nurma.absensi.model.Guru;
import com.nurma.absensi.model.Login;
import com.nurma.absensi.model.LoginResponse;
import com.nurma.absensi.model.Rekap;
import com.nurma.absensi.model.RekapDetail;
import com.nurma.absensi.model.Siswa;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface ApiInterface {


    @FormUrlEncoded
    @POST("login.php")
    Call<Login> doLogin(@Field("username") String username, @Field("password") String password);

    @GET("readGuru.php")
    Call<List<Guru>> listGuru();

    @GET("readSiswa.php")
    Call<List<Siswa>> listSiswa();

    @GET("rekapLogin.php")
    Call<List<Rekap>> listRekap();

    @Multipart
    @POST("insertGuru.php")
    Call<ResponseBody> doUpload(@Part MultipartBody.Part photo, @PartMap Map<String, RequestBody> text);

    @GET("readLogin.php")
    Call<List<LoginResponse>> listLogin(@Query("username") String username,@Query("password") String password);

    @GET("rekapDetail.php")
    Call<List<RekapDetail>> listRekapDetail(@Query("username") String username, @Query("password") String password);

    @GET("readLoginUid.php")
    Call<Login> dataLogin(@Query("unique_id") String unique_id);

    @GET("readSiswaId.php")
    Call<Siswa> getSiswaId(@Query("nim") int nim);

    @FormUrlEncoded
    @POST("updateGuru.php")
    Call<Login> updateLogin(@Field("jam_login") String jlogin,
                           @Field("jam_logout") String jam);

    @FormUrlEncoded
    @POST("insertSiswa.php")
    Call<Siswa> uploadSiswa(@Field("nim") String nim, @Field("nama_siswa") String nama,
                            @Field("alamat_siswa") String alamat, @Field("jenis_kelamin") String jeniskelamin,
                            @Field("tanggal_lahir") String ttl,@Field("kelas") String kelas);

    @FormUrlEncoded
    @POST("insertLogin.php")
    Call<Login> doInsert(@Field("username") String username, @Field("password") String password,@Field("latitude") String latitude,
                         @Field("longitude") String longitude,@Field("jam_login") String jam_login,
                         @Field("tanggal") String tanggal,@Field("nim_siswa") int nim_siswa);


}
