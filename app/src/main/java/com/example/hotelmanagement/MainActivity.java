package com.example.hotelmanagement;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    Button mngr,cust,chef;
    public static int temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mngr = (Button)findViewById(R.id.btn_hotel_mngr);
        chef = (Button)findViewById(R.id.btn_chef);
        cust = (Button)findViewById(R.id.btn_customer);
         int bal = 1000;
        try {

            db = openOrCreateDatabase("Hotel", Context.MODE_PRIVATE, null);
            db.execSQL("create table if not exists manager_account(acc_bal int)");
            db.execSQL("insert into manager_account(acc_bal)values('" + bal + "')");
        }catch (Exception e)
        {
            Toast.makeText(this, ""+e.toString(), Toast.LENGTH_SHORT).show();
        }
        mngr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp = 1;
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
        cust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp = 0 ;
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
        chef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp = 2 ;
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
