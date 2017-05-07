package com.example.krishnaraj.menjar.Utlis;

import com.example.krishnaraj.menjar.Global;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by krishnaraj on 28/3/17.
 */

public class ApiClient {
    static RestApiInterface apiClient;
    public static RestApiInterface getClient() {
        if (apiClient==null) {
            apiClient = new Retrofit.Builder()
                    .baseUrl(Global.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RestApiInterface.class);
        }
        return apiClient;
    }
}
