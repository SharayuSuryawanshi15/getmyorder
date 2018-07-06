package com.example.hotelmanagement;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PlaceOrder extends AppCompatActivity {

    public static int res;
    Button cal,place_ordr;
    TextView nm,price,total;
    public  static String val;
    EditText qty;
    int acc_bal,managerAccount;
    public  static  String prname,prprice,status;
    public  static  int prqty,ordrtotal;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        db = openOrCreateDatabase("Hotel", Context.MODE_PRIVATE,null);

        nm = (TextView)findViewById(R.id.tv_name);
        price = (TextView)findViewById(R.id.tv_price);
        qty = (EditText)findViewById(R.id.edit_qty);
        cal = (Button)findViewById(R.id.btn_cal_amt);
        total = (TextView)findViewById(R.id.tv_total);
        nm.setText(ListItemsActivity.nm1);
        price.setText(ListItemsActivity.prc);
        place_ordr = (Button)findViewById(R.id.btn_confirm_order);

        place_ordr.setEnabled(false);


        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (qty.getText().toString().equals("") ){
                        qty.setError("Please Enter Quantity");
                    } else {
                        int val = Integer.parseInt(ListItemsActivity.prc);
                        int total_amt = Integer.parseInt(qty.getText().toString());
                        res = val * total_amt;
                        total.setText("" + res);
                        place_ordr.setEnabled(true);
                        prname = ListItemsActivity.nm1;
                        prprice = ListItemsActivity.prc;
                        prqty = Integer.parseInt(qty.getText().toString());
                        ordrtotal = res;
                        status = "recieved";

                    }
                }catch(Exception e)
                {
                    Toast.makeText(PlaceOrder.this, "Error : "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        place_ordr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent i = new Intent(PlaceOrder.this, ConfirmedActivity.class);
                    startActivity(i);
                }catch (Exception e)
                {
                    Toast.makeText(PlaceOrder.this, ""+e.toString(), Toast.LENGTH_SHORT).show();
                }











































          /*try {
                    Cursor  c = db.rawQuery("select * from users where mobile="+LoginActivity.mobileno, null);
                    int flag = 0;
                    while (c.moveToNext()) {
                         acc_bal = Integer.parseInt(c.getString(5));
                        try {
                            if (res > acc_bal) {
                                flag = 0;//Toast.makeText(LoginActivity.this, "Invalid password or id " + c.getString(1).toString(), Toast.LENGTH_SHORT).show();
                            } else {
                                flag = 1;
                                // Intent intent = new Intent(LoginActivity.this, ListItemsActivity.class);
                                //startActivity(intent);
                            }
                        } catch (Exception e) {
                            Toast.makeText(PlaceOrder.this, "Error : " + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    c.close();
                    if (flag == 0) {
                        Toast.makeText(PlaceOrder.this, "Don't Have Sufficient Balance In Your Account", Toast.LENGTH_SHORT).show();
                    } else {
                        int acc_up = acc_bal-res;
                        db.execSQL("update users set acc_bal='" + acc_up+ "'");
                        Toast.makeText(PlaceOrder.this, "Available Balance Is : "+acc_up, Toast.LENGTH_SHORT).show();
                        //db.execSQL("update manager_account set acc_bal='" + acc_up+ "'");
                        try {
                            Cursor  c1 = db.rawQuery("select * from manager_account",null);
                            while (c1.moveToNext()) {

                                    managerAccount = Integer.parseInt(c1.getString(0));

                                    int manres = managerAccount + res;

                                    db.execSQL("update manager_account set acc_bal='" + manres + "'");

                            }
                            c1.close();

                            //   Intent i = new Intent(PlaceOrder.this,ConfirmedActivity.class);
                            // startActivity(i);
                        }catch (Exception e)
                        {
                            Toast.makeText(PlaceOrder.this, ""+e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    //   Intent i = new Intent(PlaceOrder.this,ConfirmedActivity.class);
                    // startActivity(i);
                }catch (Exception e)
                {
                    Toast.makeText(PlaceOrder.this, ""+e.toString(), Toast.LENGTH_SHORT).show();
                }
*/
            }
        });
    }

}
