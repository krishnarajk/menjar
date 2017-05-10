package com.example.krishnaraj.menjar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.krishnaraj.menjar.Models.Catalog;
import com.example.krishnaraj.menjar.Models.Order;
import com.example.krishnaraj.menjar.Models.OrderItem;
import com.example.krishnaraj.menjar.Utlis.ApiClient;
import com.example.krishnaraj.menjar.adapters.RecommendedListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cart extends AppCompatActivity implements CartAdapter.CartUpdateListener {
    Button additem, confirm;
    ListView cartList;
    TextView subTotal;
    RecyclerView rvRecommends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("My Cart");
        additem = (Button) findViewById(R.id.additem);
        confirm = (Button) findViewById(R.id.confirm);
        cartList = (ListView) findViewById(R.id.cart);
        CartAdapter cartAdapter = new CartAdapter(Global.order.items, this);
        cartList.setAdapter(cartAdapter);
        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Cart.this, MenuCategories.class));
                finish();
            }
        });
        final EditText comment = (EditText) findViewById(R.id.comments);
        subTotal = (TextView) findViewById(R.id.subtotal);
        subTotal.setText("₹" + Global.order.amount);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Global.order.comments = comment.getText().toString();
                ApiClient.getClient().order(Global.order).enqueue(new Callback<Order>() {
                    @Override
                    public void onResponse(Call<Order> call, Response<Order> response) {
                        if (response.isSuccessful()) {
                            Global.orderId = response.body().id;
                            Log.i("success", "success");
                            for (OrderItem orderItem : Global.order.items) {
                                Global.orderItemsGlobal.add(orderItem);
                                Log.i("x", orderItem.name);
                            }
                            Global.order.items.clear();
                            Toast.makeText(Cart.this, "Order placed successfully", Toast.LENGTH_LONG).show();
                            Log.i("price", String.valueOf(Global.order.amount));
                            Global.order.amount = 0;
                            startActivity(new Intent(Cart.this, YourOrder.class));
                            finish();
                        } else {
                            Log.i("Error", "Error");
                        }

                    }

                    @Override
                    public void onFailure(Call<Order> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

            }
        });
        rvRecommends = (RecyclerView) findViewById(R.id.recomends);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvRecommends.setLayoutManager(layoutManager);
        ApiClient.getClient().getRecommendedItems(/*Global.order*/).enqueue(new Callback<List<Catalog>>() {
            @Override
            public void onResponse(Call<List<Catalog>> call, Response<List<Catalog>> response) {
                if (response.isSuccessful()) {
                    List<Catalog> items = response.body();
                    Log.i("recommended",items.toString());
                    RecommendedListAdapter adapter = new RecommendedListAdapter(Cart.this, items);
                    rvRecommends.setAdapter(adapter);
                } else {
                    Toast.makeText(Cart.this, "Unable to load recommendations", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Catalog>> call, Throwable t) {
                Toast.makeText(Cart.this, "Unable to load recommendations", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCartUpdate() {
        Global.order.amount = 0;
        for (OrderItem item : Global.order.items) {
            Global.order.amount += item.price * item.quantity;
        }
        subTotal.setText("₹" + Global.order.amount);
    }
}
