package com.example.krishnaraj.menjar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.krishnaraj.menjar.Models.OrderItem;

import java.util.ArrayList;

/**
 * Created by krishnaraj on 9/5/17.
 */

public class CartAdapter extends BaseAdapter {
    ArrayList<OrderItem>orderItems;
    Context context;
    Button remove,plus,minus;
    TextView quant;
    public CartAdapter(ArrayList<OrderItem>orderItems,Context context){
        this.orderItems=orderItems;
        this.context=context;
    }

    @Override
    public int getCount() {
        return orderItems.size();
    }

    @Override
    public Object getItem(int i) {
        return orderItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view = LayoutInflater.from(context).inflate(R.layout.list_cart,viewGroup,false);
        }
        final OrderItem orderItem = (OrderItem) getItem(i);
        TextView name = (TextView) view.findViewById(R.id.itemName);
        name.setText(orderItem.name);
        TextView price = (TextView) view.findViewById(R.id.itemPrice);
        price.setText(orderItem.price+"");
        plus = (Button) view.findViewById(R.id.plus);
        minus = (Button) view.findViewById(R.id.minus);
        remove = (Button) view.findViewById(R.id.remove);
        quant = (TextView) view.findViewById(R.id.quantityCart);
        quant.setText(orderItem.quantity+"");
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderItem.quantity+=1;
                notifyDataSetChanged();
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderItem.quantity-=1;
                notifyDataSetChanged();


            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderItems.remove(orderItem);
                notifyDataSetChanged();
            }
        });
        return view;
    }
}
