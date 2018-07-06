package com.example.hotelmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UsersMenu extends AppCompatActivity {

    Button or,ch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_menu);
        or = (Button)findViewById(R.id.btn_order);
        ch = (Button)findViewById(R.id.btn_status);
        or.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UsersMenu.this,ListItemsActivity.class);
                startActivity(i);
            }
        });
        ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UsersMenu.this,OrderStatusActivity.class);
                startActivity(i);
            }
        });
    }
}
