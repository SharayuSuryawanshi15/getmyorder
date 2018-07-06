package com.example.hotelmanagement;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class OrderStatusActivity extends AppCompatActivity {
    SQLiteDatabase db;
    Button bn;
    int acc_bal,managerAccount,Amount;
    public  String mobno;
    public static int manres;
    public  static int userbal;


    ListView lv;
    public static String prname[];
    public static String uid[];
    public static String prqty[];
    public static String umobile[];
    public static String prprice[];
    public static String totalamt[];
    public static String status[];
    public static  String umob;
    public  String sts,currStatus;
    public  int temp3 ,temp2 ;
    OrderStatusListAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        bn = (Button)findViewById(R.id.moreOrderbtn);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OrderStatusActivity.this,ListItemsActivity.class);
                startActivity(i);
            }
        });
        ListViewdata();
    }
    public  void ListViewdata() {


        try {


            db = openOrCreateDatabase("Hotel", Context.MODE_PRIVATE, null);

            lv = (ListView) findViewById(R.id.list_order_status);
            db.execSQL("create table if not exists orders(id INTEGER PRIMARY KEY autoincrement,umobile int,name varchar(50),qty int,price int,totalamt int,status varchar(10))");

            final Cursor c = db.rawQuery("select * from orders where umobile = "+LoginActivity.mobileno, null);
            prname = new String[c.getCount()];
            uid = new String[c.getCount()];
            umobile = new String[c.getCount()];
            prqty = new String[c.getCount()];
            prprice = new String[c.getCount()];
            totalamt = new String[c.getCount()];
            status = new String[c.getCount()];
            int i = 0;
            while (c.moveToNext()) {
                try {

                    uid[i] = c.getString(0);
                    umobile[i] = c.getString(1);
                    prname[i] = c.getString(2);
                    prqty[i] = c.getString(3);
                    prprice[i] = c.getString(4);
                    totalamt[i] = c.getString(5);
                    status[i] = c.getString(6);

                    i++;
                    adapter = new OrderStatusListAdapter(OrderStatusActivity.this, uid, umobile, prname, prqty, prprice, totalamt, status);//Constructor Of ListViewAdapter Class
                    lv.setAdapter(adapter);
                    //swipeRefreshLayout.setRefreshing(false);
                } catch (Exception e) {
                    // Toast.makeText(OrdersList.this, "Error : " + e.toString(), Toast.LENGTH_SHORT).show();
                }

                // adapter.setNotifyOnChange(true);
            }
        }catch (Exception e)
        {
            Toast.makeText(this, ""+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
