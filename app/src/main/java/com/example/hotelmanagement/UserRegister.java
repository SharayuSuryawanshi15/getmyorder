package com.example.hotelmanagement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserRegister extends AppCompatActivity {
    Button register;
    EditText mid,fname,add,mob,pwd,cpwd;
    SQLiteDatabase db;
    String fn,ln,mn ,mem_id,spwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);


        db = openOrCreateDatabase("Hotel", Context.MODE_PRIVATE,null);


        register = (Button)findViewById(R.id.register_btn);
        fname = (EditText)findViewById(R.id.reg_mid_name_field);
        add = (EditText)findViewById(R.id.reg_mid_address_field);
        mob = (EditText)findViewById(R.id.reg_mid_mobile);
        mid = (EditText)findViewById(R.id.reg_mid_field);
        pwd = (EditText)findViewById(R.id.password_field);
        cpwd = (EditText)findViewById(R.id.confirm_pwd_field);
        final int bal = 2000;

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    db.execSQL("create table if not exists users(id INTEGER PRIMARY KEY autoincrement, " +
                            "name varchar(50),address varchar(30),mobile int UNIQUE,pass int,acc_bal int)");
                    if (pwd.getText().toString().equals("")) {
                        pwd.setError("This Field Can Not Be Empty");
                    } else if (pwd.getText().toString().equals("")) {
                        cpwd.setError("This Field Can Not Be Empty");

                    } else if (!pwd.getText().toString().equals(cpwd.getText().toString())) {
                        cpwd.setError("Password Does not Match");
                    } else if (pwd.getText().toString().equals(cpwd.getText().toString())) {
                        db.execSQL("insert into users(id,name,address,mobile,pass,acc_bal)values('" + mid.getText() + "','" + fname.getText() + "','"+add.getText().toString()+"','" + mob.getText() + "','" + pwd.getText() + "','"+bal+"')");
                        Toast.makeText(UserRegister.this, "Registered "+fname.getText().toString()+" Successfully", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {
                    Toast.makeText(UserRegister.this, "Mobile Number Already Registered", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
