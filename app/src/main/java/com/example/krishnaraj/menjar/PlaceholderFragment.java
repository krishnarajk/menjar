package com.example.krishnaraj.menjar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.krishnaraj.menjar.Models.Catalog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krishnaraj on 6/5/17.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public PlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(List<Catalog>list) {
        ArrayList<Catalog>catalogs=new ArrayList<>();
        for (Catalog catalog : list) {
            catalogs.add(catalog);
        }
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putSerializable("list",catalogs);
        fragment.setArguments(args);
        for(int i=0;i<catalogs.size();i++){
            Log.i("name", String.valueOf(catalogs.get(i).getName()));
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu_categories, container, false);
        final ArrayList<Catalog> catalogs = (ArrayList<Catalog>)getArguments().getSerializable("list");
        ListView listView = (ListView) rootView.findViewById(R.id.menuListView);
        MenuAdapter menuAdapter = new MenuAdapter(catalogs,getContext());
        listView.setAdapter(menuAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),Description.class);
                intent.putExtra("catalog",catalogs.get(i));
                getActivity().startActivity(intent);
            }
        });
        return rootView;
    }
}