package com.example.krishnaraj.menjar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.krishnaraj.menjar.Models.Order;
import com.example.krishnaraj.menjar.Utlis.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cart extends AppCompatActivity {
    Button additem,confirm;
    ListView cartList;
    TextView subTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        setTitle("My Cart");
        additem = (Button) findViewById(R.id.additem);
        confirm = (Button) findViewById(R.id.confirm);
        cartList = (ListView) findViewById(R.id.cart);
        CartAdapter cartAdapter = new CartAdapter(Global.order.items,this);
        cartList.setAdapter(cartAdapter);
        subTotal = (TextView) findViewById(R.id.subtotal);
        subTotal.setText(Global.order.amount+"");
        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Cart.this,MenuCategories.class));
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiClient.getClient().order(Global.order).enqueue(new Callback<Order>() {
                    @Override
                    public void onResponse(Call<Order> call, Response<Order> response) {
                        if(response.isSuccessful()){
                            Log.i("success","success");
                            
                        }
                        else{
                            Log.i("Error","Error");
                        }

                    }
                    @Override
                    public void onFailure(Call<Order> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
                Toast.makeText(Cart.this,"Order placed successfully",Toast.LENGTH_LONG).show();
                Log.i("price", String.valueOf(Global.order.amount));
                Global.order.amount=0;
            }
        });
    }
}
