package com.aprianto.p13apiandroid.API_config;

import retrofit2.Retrofit;

public class ApiUtils {

    private static String IP = "192.168.1.109";
    private static String BASE_URL = "http://"+IP+"/android";

    public ApiUtils() {
    }

    public static ApiService getApiService(){
        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);

    }
}
