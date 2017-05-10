package com.example.krishnaraj.menjar;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.krishnaraj.menjar.Models.Catalog;
import com.example.krishnaraj.menjar.Models.OrderItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by krishnaraj on 9/5/17.
 */

public class CartAdapter extends BaseAdapter {
    ArrayList<OrderItem>orderItems;
    Context context;
    ImageButton remove,plus,minus;
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
        ImageView img = (ImageView) view.findViewById(R.id.imagecart);
        Picasso.with(context).load(Global.BASE_URL+orderItem.image).fit().into(img);
        Log.i("img",orderItem.image);
        TextView name = (TextView) view.findViewById(R.id.itemName);
        name.setText(orderItem.name);
        plus = (ImageButton) view.findViewById(R.id.plus);
        minus = (ImageButton) view.findViewById(R.id.minus);
        remove = (ImageButton) view.findViewById(R.id.remove);
        quant = (TextView) view.findViewById(R.id.quantityCart);
        quant.setText(orderItem.quantity+"");
        TextView price = (TextView) view.findViewById(R.id.itemPrice);
        price.setText("₹"+orderItem.price*orderItem.quantity);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderItem.quantity+=1;
                notifyDataSetChanged();
                if(context instanceof CartUpdateListener){
                    ((CartUpdateListener)context).onCartUpdate();
                }
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(orderItem.quantity==1)
                    return;
                orderItem.quantity-=1;
                notifyDataSetChanged();
                if(context instanceof CartUpdateListener){
                    ((CartUpdateListener)context).onCartUpdate();
                }
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderItems.remove(orderItem);
                notifyDataSetChanged();
                if(context instanceof CartUpdateListener){
                    ((CartUpdateListener)context).onCartUpdate();
                }
            }
        });
        return view;
    }

    public interface CartUpdateListener{
        public void onCartUpdate();
    }
}
