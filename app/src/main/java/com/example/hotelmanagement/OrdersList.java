package com.example.hotelmanagement;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class OrdersList extends AppCompatActivity {

    SQLiteDatabase db;
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
    OrderListAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_list);

        temp2 = ChefMenu.val;
        temp3 = ManagerMenu.val1;

        ListViewdata();

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(temp3 == 1)
        {
            temp3=0;
        }
        else if(temp2==2)
        {
            temp2 = 0;
        }
        Toast.makeText(this, "Back Button Pressed", Toast.LENGTH_SHORT).show();
    }
    public  void ListViewdata()
    {


        try {



            db = openOrCreateDatabase("Hotel", Context.MODE_PRIVATE, null);

            lv = (ListView) findViewById(R.id.listOrders);
            db.execSQL("create table if not exists orders(id INTEGER PRIMARY KEY autoincrement,umobile int,name varchar(50),qty int,price int,totalamt int,status varchar(10))");

            final Cursor c = db.rawQuery("select * from orders", null);
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
                    adapter = new OrderListAdapter(OrdersList.this , uid,umobile, prname, prqty, prprice, totalamt, status);//Constructor Of ListViewAdapter Class
                    lv.setAdapter(adapter);
                } catch (Exception e) {
                   // Toast.makeText(OrdersList.this, "Error : " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }


            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    umob = uid[i].toString();
                    Amount = Integer.parseInt(totalamt[i]);
                    mobno = umobile[i].toString();

                    currStatus = status[i].toString();
                  //  Toast.makeText(OrdersList.this, "temp 2"+ temp2, Toast.LENGTH_SHORT).show();
                  //  Toast.makeText(OrdersList.this, "temp 3"+temp3, Toast.LENGTH_SHORT).show();

                    if (temp3 == 1) {
                        if (currStatus.equals("recieved")) {
                            sts = "Confirmed";

                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrdersList.this);

                            alertDialog.setTitle("Confirm Order ?");

                            alertDialog.setMessage("Are you Sure ?");
                            // Setting Positive "Yes" Button
                            alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    try {

                                        db.execSQL("update orders set status='" + sts + "' where id='" + umob + "'");
                                            ListViewdata();
                                        // Write your code here to invoke YES event
                                        //Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
                                    } catch (Exception e) {
                                        Toast.makeText(OrdersList.this, "" + e.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                            // Setting Negative "NO" Button
                            alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    try {
                                        // Write your code here to invoke NO event

                                        db.execSQL("delete from orders where id ='" + umob + "'");
                                        ListViewdata();
                                        dialog.cancel();
                                    } catch (Exception e) {
                                        Toast.makeText(OrdersList.this, "" + e.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                            // Showing Alert Message
                            alertDialog.show();
                        }
                        else
                        if (currStatus.equals("recieved")) {
                            sts = "Confirmed";

                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrdersList.this);

                            alertDialog.setTitle("Confirm Order ?");

                            alertDialog.setMessage("Are you Sure ?");
                            // Setting Positive "Yes" Button
                            alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    try {

                                        db.execSQL("update orders set status='" + sts + "' where id='" + umob + "'");
                                        ListViewdata();
                                        // Write your code here to invoke YES event
                                        //Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
                                    } catch (Exception e) {
                                        Toast.makeText(OrdersList.this, "" + e.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        else
                        if (currStatus.equals("Ready"))
                        {
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrdersList.this);

                            alertDialog.setTitle("Dispatch Order ");

                            alertDialog.setMessage("Order Is Ready Are You Sure TO Deliver");

                            alertDialog.setPositiveButton("Deliver Order", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // User pressed No button. Write Logic Here
                                    //sts = "Ready";
                                    //db.execSQL("update orders set status='" + sts + "' where id='" + umob + "'");
                                    try {
                                        Cursor c = db.rawQuery("select * from users where mobile=" +mobno, null);
                                        int flag = 0;
                                        while (c.moveToNext()) {
                                            acc_bal = Integer.parseInt(c.getString(5));
                                            try {
                                                if (Amount > acc_bal) {
                                                    flag = 0;//Toast.makeText(LoginActivity.this, "Invalid password or id " + c.getString(1).toString(), Toast.LENGTH_SHORT).show();
                                                } else {
                                                    flag = 1;
                                                    // Intent intent = new Intent(LoginActivity.this, ListItemsActivity.class);
                                                    //startActivity(intent);
                                                }
                                            } catch (Exception e) {
                                                Toast.makeText(OrdersList.this, "Error : " + e.toString(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        c.close();
                                        if (flag == 0)
                                        {
                                            sts="Not Paid";
                                            db.execSQL("update orders set status='" + sts + "' where id='" + umob + "'");
                                            ListViewdata();
                                            Toast.makeText(OrdersList.this, "Don't Have Sufficient Balance In Users Account", Toast.LENGTH_SHORT).show();
                                        }
                                        else if(flag ==1) {
                                            int acc_up = acc_bal - Amount;
                                            db.execSQL("update users set acc_bal='" + acc_up + "'");
                                            userbal = acc_up;
                                            //Toast.makeText(OrdersList.this, "Available Balance Is : " + acc_up, Toast.LENGTH_SHORT).show();
                                            //db.execSQL("update manager_account set acc_bal='" + acc_up+ "'");
                                            try {
                                                Cursor c1 = db.rawQuery("select * from manager_account", null);
                                                while (c1.moveToNext()) {

                                                    managerAccount = Integer.parseInt(c1.getString(0));

                                                    manres = managerAccount + Amount;

                                                    db.execSQL("update manager_account set acc_bal='" + manres + "'");
                                                    flag = 2;

                                                }
                                                c1.close();

                                                //   Intent i = new Intent(OrdersList.this,ConfirmedActivity.class);
                                                // startActivity(i);
                                            } catch (Exception e) {
                                                Toast.makeText(OrdersList.this, "" + e.toString(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        if (flag==2)
                                        {
                                            Toast.makeText(OrdersList.this,""+Amount+" Rs Amount Credited In Your Account.! Current Balance Is : "+manres+" Rs", Toast.LENGTH_SHORT).show();
                                            db.execSQL("delete from orders where id ='" + umob + "'");
                                            ListViewdata();                                        }
                                    }catch (Exception e)
                                    {

                                    }

                                }

                            });

                            alertDialog.show();
                        }
                        else if(currStatus=="Not Paid")
                        {
                            Toast.makeText(OrdersList.this, "Don't Have Enough Balance In Users Account", Toast.LENGTH_SHORT).show();
                        }
                    }else if(temp2 == 2){
                        if (currStatus.equals("Confirmed")) {
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrdersList.this);

                            alertDialog.setTitle("Order Status For " + umob);

                            alertDialog.setMessage("Are You Preparing Order Or Not Available ?");
                            alertDialog.setPositiveButton("Preparing", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // User pressed YES button. Write Logic Here
                                    sts = "Preparing";
                                    db.execSQL("update orders set status='" + sts + "' where id='" + umob + "'");
                                    ListViewdata();
                                }
                            });
                            alertDialog.setNeutralButton("Not Available", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // User pressed Cancel button. Write Logic Here
                                    sts = "Not Available";
                                    db.execSQL("update orders set status='" + sts + "' where id='" + umob + "'");
                                    ListViewdata();
                                }
                            });
                            // Showing Alert Message
                            alertDialog.show();
                        }
                        else
                        if (currStatus.equals("Preparing")) {
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrdersList.this);

                            alertDialog.setTitle("Order Status");

                            alertDialog.setMessage("Is Order Ready ?");
                            alertDialog.setPositiveButton("Ready", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // User pressed YES button. Write Logic Here
                                    sts = "Ready";
                                    db.execSQL("update orders set status='" + sts + "' where id='" + umob + "'");
                                    ListViewdata();
                                }
                            });
                            alertDialog.show();
                        }
                    }
                }
            });
        } catch (Exception e) {
           // Toast.makeText(this, ""+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

}
