package com.example.krishnaraj.menjar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.krishnaraj.menjar.Models.Table;
import com.example.krishnaraj.menjar.Utlis.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TableLogin extends AppCompatActivity {
    EditText tableName;
    EditText tablepin;
    Button login;
    String name="";
    String pin="";
    Table table = new Table();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_login);
        tableName = (EditText) findViewById(R.id.tableName);
        tablepin = (EditText) findViewById(R.id.pin);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = tableName.getText().toString();
                pin = tablepin.getText().toString();
                table.name= name;
                table.pin=pin;
                ApiClient.getClient().login(table).enqueue(new Callback<Table>() {
                    @Override
                    public void onResponse(Call<Table> call, Response<Table> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(TableLogin.this,"Login Successful",Toast.LENGTH_LONG).show();
                            Global.tableId= Integer.parseInt(name);
                            Log.i("tid", String.valueOf(Global.tableId));
                            startActivity(new Intent(TableLogin.this,MainActivity.class));
                        }
                        else{
                            Toast.makeText(TableLogin.this,"Login failed",Toast.LENGTH_LONG).show();
                            Log.i("Error","Error");
                        }
                    }

                    @Override
                    public void onFailure(Call<Table> call, Throwable t) {

                    }
                });
            }
        });
    }
}
