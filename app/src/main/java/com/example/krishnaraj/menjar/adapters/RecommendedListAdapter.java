package com.example.krishnaraj.menjar.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.krishnaraj.menjar.Global;
import com.example.krishnaraj.menjar.Models.Catalog;
import com.example.krishnaraj.menjar.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jayadeep on 5/11/17.
 */

public class RecommendedListAdapter extends RecyclerView.Adapter<RecommendedListAdapter.ViewHolder> {

    Context context;
    List<Catalog> items;

    public RecommendedListAdapter(Context c, List<Catalog> itemList) {
        context = c;
        items = itemList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_recommendation, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Catalog catalog = items.get(position);
        holder.name.setText(catalog.name);
//        holder.price.setText("₹" + catalog.price);
        Picasso.with(context).load(Global.BASE_URL+catalog.image).fit().into(holder.image);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.recomendsimage);
//            price = (TextView) itemView.findViewById(R.id.price);
            name = (TextView) itemView.findViewById(R.id.itemName);
        }
    }

}
