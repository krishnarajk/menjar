package com.example.krishnaraj.menjar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.krishnaraj.menjar.Models.Order;
import com.example.krishnaraj.menjar.Utlis.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YourOrder extends AppCompatActivity {
    ListView yourOrderList;
    TextView yourOrderHeading,yourOrderStatus,yourOrderAmount;
    Order order = new Order();
    String status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(YourOrder.this, MainActivity.class));
                finish();
            }
        });


        yourOrderHeading = (TextView) findViewById(R.id.placedsuccesfull);
        yourOrderStatus = (TextView) findViewById(R.id.status);
        yourOrderAmount = (TextView) findViewById(R.id.yourorderamount);
        if (Global.orderItemsGlobal.size() == 0) {
            yourOrderHeading.setText("No item Ordered");
            yourOrderStatus.setText("");
            yourOrderAmount.setText("");
        } else {
            yourOrderAmount.setText("Amount:₹" + Global.amount);

            ApiClient.getClient().getOrder(Global.orderId).enqueue(callback);
            yourOrderList = (ListView) findViewById(R.id.yourorderlistview);
            YourOrderAdapter yourOrderAdapter = new YourOrderAdapter(Global.orderItemsGlobal, this);
            yourOrderList.setAdapter(yourOrderAdapter);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void refreshOrders() {
        if (this.isDestroyed())
            return;
        ApiClient.getClient().getOrder(Global.orderId).enqueue(callback);
    }
    Callback<Order> callback = new Callback<Order>() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        @Override
        public void onResponse(Call<Order> call, Response<Order> response) {
            if(response.isSuccessful()){
                order = response.body();
                status = order.status;
                yourOrderStatus.setText(status);
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    refreshOrders();
                }
            }, 4000);
        }

        @Override
        public void onFailure(Call<Order> call, Throwable t) {

        }
    };
}
