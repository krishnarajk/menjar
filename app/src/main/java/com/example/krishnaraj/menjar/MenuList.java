package com.example.krishnaraj.menjar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.krishnaraj.menjar.Models.Catalog;
import com.example.krishnaraj.menjar.Utlis.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);
        String category = getIntent().getStringExtra("category");
        ApiClient.getClient().getCatalogs().enqueue(new Callback< List< Catalog>>(){

            @Override
            public void onResponse(Call<List<Catalog>> call, Response<List<Catalog>> response) {
                if(response.isSuccessful()){
                    List<Catalog> catalogs =response.body();
                    for(int i=0;i<catalogs.size();i++){
                        Log.i("name", String.valueOf(catalogs.get(i).getName()));
                    }
                }
                else {
                    Log.i("Error","Error");
                }
            }

            @Override
            public void onFailure(Call<List<Catalog>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
