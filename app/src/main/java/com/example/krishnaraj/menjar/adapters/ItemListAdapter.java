package com.example.krishnaraj.menjar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.krishnaraj.menjar.Models.Catalog;
import com.example.krishnaraj.menjar.R;

import java.util.List;

/**
 * Created by krishnaraj on 30/4/17.
 */

public class ItemListAdapter extends BaseAdapter {

    Context context;
    List<Catalog> itemList;

    public ItemListAdapter(Context c, List<Catalog> items){
        context = c;
        itemList = items;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int i) {
        return itemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view = LayoutInflater.from(context).inflate(R.layout.list_item,viewGroup,false);
        }
        Catalog catalog = (Catalog) getItem(i);
        TextView tvName = (TextView) view.findViewById(R.id.name);
        tvName.setText(catalog.getName());
        return view;
    }
}
