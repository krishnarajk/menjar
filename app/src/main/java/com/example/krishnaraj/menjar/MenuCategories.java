package com.example.krishnaraj.menjar;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.krishnaraj.menjar.Models.Catalog;
import com.example.krishnaraj.menjar.Utlis.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuCategories extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_categories);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Food Menu");
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabcart);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuCategories.this,Cart.class));
            }
        });

        ApiClient.getClient().getCatalogs().enqueue(new Callback< List< Catalog>>(){

            @Override
            public void onResponse(Call<List<Catalog>> call, Response<List<Catalog>> response) {
                if(response.isSuccessful()){
                    List<Catalog> catalogs =response.body();
                    mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),catalogs);
                    mViewPager.setAdapter(mSectionsPagerAdapter);
                }
                else {
                    Log.i("Error","Error");
                }
            }

            @Override
            public void onFailure(Call<List<Catalog>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
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

    /**
     * A placeholder fragment containing a simple view.
     */


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        List<Catalog> list ;

        public SectionsPagerAdapter(FragmentManager fm, List<Catalog> catalogs) {
            super(fm);
            list = catalogs;
        }
        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            ArrayList<Catalog>listNew = new ArrayList<>();
            String category="";
            switch (position){
                case 0:
                    category = "STARTERS";
                    break;
                case 1:
                    category="RICE";
                    break;
                case 2:
                    category= "BREADS";
                    break;
                case 3:
                    category="CHICKEN";
                    break;
                case 4:
                    category="VEG";
                    break;
                case 5:
                    category="CHILLER";
                    break;

            }
            for (Catalog catalog : list) {
                if(catalog.category.equals(category))
                    listNew.add(catalog);
            }
            return PlaceholderFragment.newInstance(listNew);
        }

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Starters";
                case 1:
                    return "Rice";
                case 2:
                    return "Breads";
                case 3:
                    return "Chicken";
                case 4:
                    return "Veg";
                case 5:
                    return "Chiller Zone";
            }
            return null;
        }
    }
}
