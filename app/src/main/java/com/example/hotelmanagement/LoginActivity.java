package com.example.hotelmanagement;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    SQLiteDatabase db;
    EditText mobile,pass;
    Button log,reg;
    public static String mobileno;
    public  static int accbal,name,address;
    public static int temp1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        log = (Button)findViewById(R.id.login_btn);
        reg = (Button)findViewById(R.id.register_btn);
        db = openOrCreateDatabase("Hotel", Context.MODE_PRIVATE,null);

        mobile = (EditText)findViewById(R.id.mid_field);
        pass = (EditText)findViewById(R.id.password_field);

        temp1 = MainActivity.temp;

       /* if(temp1 == 1){
            reg.setVisibility(View.GONE);
        }
        else
        {
            reg.setVisibility(View.VISIBLE);
        }*/
        //Toast.makeText(this, ""+temp1.toString(), Toast.LENGTH_SHORT).show();
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //Manager Activity Begins
                    if (temp1 == 1) {

                        int flag=0;
                        final Cursor c = db.rawQuery("select * from manager", null);
                        while (c.moveToNext()) {
                            try {
                                if (mobile.getText().toString().equals("")) {
                                    mobile.setError("Please Enter Mobile Number");
                                }
                                else if ((!c.getString(3).equals(mobile.getText().toString())) || (!c.getString(4).equals(pass.getText().toString()))) {
                                    // Toast.makeText(LoginActivity.this, "Invalid password or id " , Toast.LENGTH_SHORT).show();
                                }else
                                {
                                    flag=1;
                                    ////Intent intent = new Intent(LoginActivity.this, ManagerActivity.class);
                                    // startActivity(intent);
                                }
                            } catch (Exception e) {
                            }
                        }
                        if(flag==0){
                            Toast.makeText(LoginActivity.this, "Invalid password or Mobile No " , Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Intent intent = new Intent(LoginActivity.this, ManagerMenu.class);
                            startActivity(intent);
                        }

                        c.close();
                    }
                    //Users Activity Begins
                    else if(temp1 == 0)
                    {

                        int flag = 0;
                        final Cursor c = db.rawQuery("select * from users", null);

                        while (c.moveToNext()) {
                            try {
                                if (mobile.getText().toString().equals("")) {
                                    mobile.setError("Please Enter Mobile Number");
                                }
                                else if ((!c.getString(3).equals(mobile.getText().toString())) || (!c.getString(4).equals(pass.getText().toString()))) {
                                    //Toast.makeText(LoginActivity.this, "Invalid password or id " + c.getString(1).toString(), Toast.LENGTH_SHORT).show();
                                }else
                                {
                                    flag = 1;
                                    // Intent intent = new Intent(LoginActivity.this, ListItemsActivity.class);
                                    //startActivity(intent);
                                }
                            } catch (Exception e) {
                                Toast.makeText(LoginActivity.this, "You Need To Register", Toast.LENGTH_SHORT).show();
                            }
                        }
                        if(flag==0){
                            Toast.makeText(LoginActivity.this, "Invalid password or id " , Toast.LENGTH_SHORT).show();
                        }
                        else{
                            mobileno = mobile.getText().toString();
                            Intent intent = new Intent(LoginActivity.this, UsersMenu.class);
                            startActivity(intent);
                        }
                        c.close();
                    }
                    //Chef Activity Begins
                    else if(temp1==2)
                    {

                        int flag = 0;
                        final Cursor c = db.rawQuery("select * from chef", null);

                        while (c.moveToNext()) {
                            try {
                                if (mobile.getText().toString().equals("")) {
                                    mobile.setError("Please Enter Mobile Number");
                                }
                                else if ((!c.getString(3).equals(mobile.getText().toString())) || (!c.getString(4).equals(pass.getText().toString()))) {
                                    //Toast.makeText(LoginActivity.this, "Invalid password or id " + c.getString(1).toString(), Toast.LENGTH_SHORT).show();
                                }else
                                {
                                    flag = 1;
                                    // Intent intent = new Intent(LoginActivity.this, ListItemsActivity.class);
                                    //startActivity(intent);
                                }
                            } catch (Exception e) {
                                Toast.makeText(LoginActivity.this, "You Need To Register", Toast.LENGTH_SHORT).show();
                            }
                        }
                        if(flag==0){
                            Toast.makeText(LoginActivity.this, "Invalid password or id " , Toast.LENGTH_SHORT).show();
                        }
                        else{
                            mobileno = mobile.getText().toString();
                            Intent intent = new Intent(LoginActivity.this, ChefMenu.class);
                            startActivity(intent);
                        }
                        c.close();
                    }
                }catch (Exception e)
                {
                    Toast.makeText(LoginActivity.this, "You Need To Register", Toast.LENGTH_SHORT).show();
                }
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(temp1 == 1) {
                    Intent i  = new Intent(LoginActivity.this,ManagerRegister.class);
                    startActivity(i);
                }
                else if(temp1==0)
                {
                    Intent i  = new Intent(LoginActivity.this,UserRegister.class);
                    startActivity(i);
                }else if(temp1==2)
                {
                    Intent intent = new Intent(LoginActivity.this,ChefRegistration.class);
                    startActivity(intent);
                }
            }
        });















    }

    @Override
    protected void onStart() {
        super.onStart();
        for (int i=0;i<10;i++)
        {
            Log.w("Login",""+i);

        }
         }
}
