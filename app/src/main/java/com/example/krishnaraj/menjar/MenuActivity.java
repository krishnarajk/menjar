package com.example.krishnaraj.menjar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Menu Categories");
        setContentView(R.layout.activity_menu);
        findViewById(R.id.snack).setOnClickListener(this);
        findViewById(R.id.veg).setOnClickListener(this);
        findViewById(R.id.nonveg).setOnClickListener(this);
        findViewById(R.id.burger).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String category = "";
        switch (view.getId()){
            case R.id.snack: category = "snack";
            case R.id.veg: category = "veg";
        }
        Intent intent = new Intent(this,MenuCategories.class);
        intent.putExtra("category",category);
        startActivity(intent);
    }
}
