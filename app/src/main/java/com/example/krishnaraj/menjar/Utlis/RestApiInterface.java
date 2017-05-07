package com.example.krishnaraj.menjar.Utlis;

import com.example.krishnaraj.menjar.Models.Catalog;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by krishnaraj on 28/3/17.
 */

public interface RestApiInterface {
    @GET("/item")
    Call<List<Catalog>>getCatalogs();

}
