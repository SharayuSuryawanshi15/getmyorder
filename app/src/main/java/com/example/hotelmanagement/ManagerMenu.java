package com.example.hotelmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ManagerMenu extends AppCompatActivity {

    public  static int val1;
    Button ordrs,chMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_menu);
        val1=1;

        ordrs = (Button)findViewById(R.id.btn_showorders);
        chMenu = (Button)findViewById(R.id.btn_changemenu);

        ordrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ManagerMenu.this,OrdersList.class);
                startActivity(i);
            }
        });
        chMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ManagerMenu.this,ManagerActivity.class);
                startActivity(i);

            }
        });
    }
    public void onBackPressed() {
        super.onBackPressed();

        if(val1 == 1)
        {
            val1=0;
        }

        Toast.makeText(this, "Exiting Manager Menu", Toast.LENGTH_SHORT).show();
    }

}
