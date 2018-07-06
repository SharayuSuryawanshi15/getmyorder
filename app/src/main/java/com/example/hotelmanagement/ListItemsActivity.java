package com.example.hotelmanagement;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ListItemsActivity extends AppCompatActivity {
    Button bn;
    SQLiteDatabase db;
    ListView lv;
    public String[] name = null;
    public String[] price = null;
    public String [] id  = null;
    public  String[] mobile = null;

    int idval;
    public  static String nm1,prc,uid,umobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);


        try {
            db = openOrCreateDatabase("Hotel", Context.MODE_PRIVATE,null);

        lv = (ListView) findViewById(R.id.list_View1);

        db.execSQL("create table if not exists menu(id int , name varchar(50),price float)");
            final Cursor c = db.rawQuery("select * from menu", null);
            name = new String[c.getCount()];
            price = new String[c.getCount()];
            id = new String[c.getCount()];
            int i = 0;
            while (c.moveToNext()) {
                try {
                    id[i] = c.getString(c.getColumnIndex("id"));
                    name[i] = c.getString(c.getColumnIndex("name"));
                    price[i] = c.getString(c.getColumnIndex("price"));
                    i++;
                    ListViewAdapter adapter = new ListViewAdapter(ListItemsActivity.this, id, name, price);//Constructor Of ListViewAdapter Class
                    lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    lv.setAdapter(adapter);
                }catch (Exception e)
                {
                    Toast.makeText(ListItemsActivity.this, "Error : "+e.toString(), Toast.LENGTH_SHORT).show();
                }
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        try {
                            Intent intent = new Intent(ListItemsActivity.this, PlaceOrder.class);
                            nm1 = name[i].toString();
                            prc = price[i].toString();
                            uid = id[i].toString();
                            startActivity(intent);
                        }catch (Exception e)
                        {
                            Toast.makeText(ListItemsActivity.this, ""+e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        }catch(Exception e)
        {
            Toast.makeText(ListItemsActivity.this, "Error : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }
}
