package com.example.krishnaraj.menjar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.krishnaraj.menjar.Models.Catalog;
import com.example.krishnaraj.menjar.Models.OrderItem;

public class Description extends AppCompatActivity {
    public Catalog catalog;
    public int quantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        catalog = (Catalog) getIntent().getSerializableExtra("catalog");
        Log.i("abc",catalog.description);
        TextView description = (TextView) findViewById(R.id.description);
        description.setText(catalog.description);
        ImageView img = (ImageView) findViewById(R.id.foodimage);

        Button addtocart = (Button) findViewById(R.id.addtocart);
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText quant = (EditText) findViewById(R.id.quantity);
                quantity= Integer.parseInt(quant.getText().toString());
                Global.order.items.add(new OrderItem(catalog.id,quantity,catalog.name,catalog.price));
                Global.order.amount+=catalog.price*quantity;
                Global.order.amountTotal+=catalog.price*quantity;
                Global.order.tableId = Global.tableId;
                startActivity(new Intent(Description.this,Cart.class));
                Log.i("ab", String.valueOf(Global.order.amount));
                Log.i("abc",String.valueOf(Global.order.amountTotal));
                for(int i=0;i<Global.order.items.size();i++){
                    Log.i("abc", String.valueOf(Global.order.items.get(i)));
                }
            }
        });
    }
}
