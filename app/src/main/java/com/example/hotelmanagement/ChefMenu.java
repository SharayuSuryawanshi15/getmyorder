package com.example.hotelmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ChefMenu extends AppCompatActivity {
    Button bn;
    public  static int val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_menu);
        bn = (Button)findViewById(R.id.btn_chefOrders);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                val =2;
                Intent i = new Intent(ChefMenu.this,OrdersList.class);
                startActivity(i);

            }
        });
    }
    public void onBackPressed() {
        super.onBackPressed();

        if(val == 1)
        {
            val=0;
        }

        Toast.makeText(this, "Exiting Chef Menu", Toast.LENGTH_SHORT).show();
    }

}

