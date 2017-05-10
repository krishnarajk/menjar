package com.example.krishnaraj.menjar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.krishnaraj.menjar.Models.OrderItem;

import java.util.ArrayList;

/**
 * Created by krishnaraj on 10/5/17.
 */

public class YourOrderAdapter extends BaseAdapter {
    ArrayList<OrderItem> orderItems;
    Context context;
    public YourOrderAdapter(ArrayList<OrderItem>orderItems,Context context){
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
            view = LayoutInflater.from(context).inflate(R.layout.list_your_order,viewGroup,false);
        }
        OrderItem orderItem = (OrderItem) getItem(i);
        TextView yoname = (TextView) view.findViewById(R.id.yourordername);
        TextView yoquantity = (TextView) view.findViewById(R.id.yourorderquantity);
        yoname.setText(orderItem.name);
        yoquantity.setText("Quantity :"+orderItem.quantity+"");
        return view;
    }
}
