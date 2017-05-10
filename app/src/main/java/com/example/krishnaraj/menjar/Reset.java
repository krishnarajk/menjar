package com.example.krishnaraj.menjar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by krishnaraj on 10/5/17.
 */
public class Reset extends AppCompatActivity {
    EditText name,pass;
    Button b;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        b= (Button) findViewById(R.id.resetbutton);
        name = (EditText) findViewById(R.id.resetname);
        pass = (EditText) findViewById(R.id.resetpassword);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().equals(pass.getText().toString())){
                    Global.tableId=0;
                    Global.orderItemsGlobal.clear();
                    Global.amount=0;
                    startActivity(new Intent(Reset.this, TableLogin.class));
                }
            }
        });
    }
}
