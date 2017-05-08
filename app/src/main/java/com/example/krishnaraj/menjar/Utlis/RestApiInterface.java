package com.example.krishnaraj.menjar.Utlis;

import com.example.krishnaraj.menjar.Models.Catalog;
import com.example.krishnaraj.menjar.Models.Order;
import com.example.krishnaraj.menjar.Models.Table;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by krishnaraj on 28/3/17.
 */

public interface RestApiInterface {
    @GET("/item")
    Call<List<Catalog>>getCatalogs();

    @POST("/order")
    Call<Order>order(@Body Order order);

    @POST("/table/login")
    Call<Table>login(@Body Table table);

}
