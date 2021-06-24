package com.aprianto.p13apiandroid.API_config;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiService {

    @Headers("Content-Type:application/json")
    @GET("read_barang.php")
    Call<GetBarang> barangGet();
}
