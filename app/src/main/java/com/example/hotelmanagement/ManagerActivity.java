package com.example.hotelmanagement;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ManagerActivity extends AppCompatActivity {
    Button in ,up,del,sh,abal;
    TextView abaltv;
    EditText nm,prc,idm;
    SQLiteDatabase db;
    ListView list;
    public String[] name = null;
    public String[] price = null;
    public String [] id ;
    int idval;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        db = openOrCreateDatabase("Hotel", Context.MODE_PRIVATE,null);
        in = (Button)findViewById(R.id.btn_insert);
        abal = (Button)findViewById(R.id.btn_acc_bal);
        up = (Button)findViewById(R.id.btn_update);
        del = (Button)findViewById(R.id.btn_delete);
        nm = (EditText) findViewById(R.id.edit_name);
        prc = (EditText) findViewById(R.id.edit_price);
        idm = (EditText) findViewById(R.id.edit_id);
        sh = (Button)findViewById(R.id.btn_show);
        list = (ListView) findViewById(R.id.list_View);
        abaltv = (TextView)findViewById(R.id.tv_bal);


        db.execSQL("create table if not exists menu(id int primary key UNIQUE , name varchar(50),price float)");

        in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    db.execSQL("insert into menu(id,name,price)values('"+idm.getText()+"','" + nm.getText() + "','" + prc.getText() + "')");
                    Toast.makeText(ManagerActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                    idm.setText("");
                    nm.setText("");
                    prc.setText("");
                }catch (Exception e)
                {

                        Toast.makeText(ManagerActivity.this, "Id Already Exist", Toast.LENGTH_SHORT).show();

                }
            }
        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    db.execSQL("update menu set name='" + nm.getText() + "',price='" + prc.getText() + "' where id='" + idm.getText() + "'");

                    Toast.makeText(ManagerActivity.this, "Updated", Toast.LENGTH_SHORT).show();

                }catch (Exception e)
                {
                    Toast.makeText(ManagerActivity.this, "Error : "+e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        sh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    final Cursor c = db.rawQuery("select * from menu", null);
                    name = new String[c.getCount()];
                    price = new String[c.getCount()];
                    id = new String[c.getCount()];
                    int i = 0;
                    while (c.moveToNext()) {
                        try {
                            id[i] = c.getString(0);
                            name[i] = c.getString(1);
                            price[i] = c.getString(2);
                            i++;

                            ListViewAdapter adapter = new ListViewAdapter(ManagerActivity.this, id, name, price);//Constructor Of ListViewAdapter Class

                            list.setAdapter(adapter);

                        }catch (Exception e)
                        {
                            Toast.makeText(ManagerActivity.this, "Error : "+e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                }catch(Exception e)
                {
                    Toast.makeText(ManagerActivity.this, "Error : "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    db.execSQL("delete from menu where id ='"+ idm.getText() +"'");
                    Toast.makeText(ManagerActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(ManagerActivity.this, "Error : " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        abal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int managerAccount;
                Cursor  c1 = db.rawQuery("select * from manager_account",null);
                while (c1.moveToNext()) {

                    managerAccount = Integer.parseInt(c1.getString(0));
                    abaltv.setText("Balance In Your Account is : "+managerAccount);
                }
                c1.close();
            }
        });
    }
}