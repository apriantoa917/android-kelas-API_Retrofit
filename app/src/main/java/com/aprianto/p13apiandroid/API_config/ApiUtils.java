package com.aprianto.p13apiandroid.API_config;

import retrofit2.Retrofit;

public class ApiUtils {

    private static String BASE_URL = "http://192.168.1.109/android/"; // edit your API URL here

    public ApiUtils() {
    }

    public static ApiService getApiService(){
        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);

    }
}
