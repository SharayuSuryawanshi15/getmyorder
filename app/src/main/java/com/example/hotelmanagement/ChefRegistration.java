package com.example.hotelmanagement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChefRegistration extends AppCompatActivity {

    Button register;
    EditText mid,fname,lname,mob,pwd,cpwd;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_registration);

        db = openOrCreateDatabase("Hotel", Context.MODE_PRIVATE,null);
        register = (Button)findViewById(R.id.register_btn);
        fname = (EditText)findViewById(R.id.reg_mid_name_field);
        lname = (EditText)findViewById(R.id.reg_mid_lname_field);
        mob = (EditText)findViewById(R.id.reg_mid_mobile);
        mid = (EditText)findViewById(R.id.reg_mid_field);
        pwd = (EditText)findViewById(R.id.password_field);
        cpwd = (EditText)findViewById(R.id.confirm_pwd_field);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    db.execSQL("create table if not exists chef(id INTEGER PRIMARY KEY autoincrement, " +
                            "name varchar(50),lname varchar(30),mobile int UNIQUE,pass int)");
                    if (mid.getText().toString().equals("")) {
                        mid.setError("This Field Can Not Be Empty");
                    } else if (fname.getText().toString().equals("")) {
                        fname.setError("This Field Can Not Be Empty");
                    } else if (lname.getText().toString().equals("")) {
                        lname.setError("This Field Can Not Be Empty");
                    }else if (pwd.getText().toString().equals("")) {
                        pwd.setError("This Field Can Not Be Empty");
                    } else if (pwd.getText().toString().equals("")) {
                        cpwd.setError("This Field Can Not Be Empty");
                    } else if (!pwd.getText().toString().equals(cpwd.getText().toString())) {
                        cpwd.setError("Password Does not Match");
                    } else if (pwd.getText().toString().equals(cpwd.getText().toString())) {
                        try {
                            db.execSQL("insert into chef(id,name,lname,mobile,pass)values('"+mid.getText()+"','" + fname.getText() + "','" + lname.getText().toString() + "','" + mob.getText() + "','" + pwd.getText() + "')");
                            Toast.makeText(ChefRegistration.this, "Registered " + fname.getText().toString() + " Successfully", Toast.LENGTH_SHORT).show();
                        }catch (Exception e)
                        {
                            Toast.makeText(ChefRegistration.this, "Mobile Number Already Registered", Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (Exception e)
                {
                    Toast.makeText(ChefRegistration.this, ""+e.toString(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
