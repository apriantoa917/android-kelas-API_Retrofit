package com.aprianto.p13apiandroid.API_config;

import com.aprianto.p13apiandroid.API_config.Barang.GetDataBarang;
import com.aprianto.p13apiandroid.API_config.Barang.PostInsertBarang;
import com.aprianto.p13apiandroid.Barang.PostUpdateBarang;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

public interface ApiService {

    @Headers("Content-Type:application/json")
    @GET("read_barang.php")
    Call<GetDataBarang> getDataBarang();


    @FormUrlEncoded
    @POST("insert_barang.php")
    Call<PostInsertBarang> postInsertBarang(
            @FieldMap Map<String, String> text
    );

    @FormUrlEncoded
    @POST("update_barang.php")
    Call<PostUpdateBarang> postUpdateBarang(
            @FieldMap Map<String, String> text
    );

    @FormUrlEncoded
    @POST("delete_barang.php")
    Call<PostDeleteBarang> postDeleteBarang(
            @Field("kode") String kode
    );
}
