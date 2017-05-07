package com.example.krishnaraj.menjar;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.krishnaraj.menjar.Models.Catalog;

import java.util.List;

/**
 * Created by krishnaraj on 6/5/17.
 */

public class MenuAdapter extends BaseAdapter {
    List<Catalog>items;
    Context context;
    public MenuAdapter(List<Catalog> items, Context context){
        this.items=items;
        this.context=context;

    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Catalog getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.list_item,viewGroup,false);
        }
        Catalog catalog = getItem(i);
        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(catalog.name);
        TextView price = (TextView) view.findViewById(R.id.price);
        price.setText(catalog.price+"");
        return view;
    }
}
