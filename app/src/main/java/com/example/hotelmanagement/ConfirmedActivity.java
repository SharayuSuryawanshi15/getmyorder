package com.example.hotelmanagement;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmedActivity extends AppCompatActivity {

    SQLiteDatabase db;
    RadioGroup radioGroup;
    RadioButton cash,debit,online;
    TextView tvbill;
    LinearLayout debitc,onlinet;
    Button pay,confirm;
    EditText card,onlinetr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmed);
        db = openOrCreateDatabase("Hotel", Context.MODE_PRIVATE,null);
        card = (EditText) findViewById(R.id.ed_debit);
        onlinetr = (EditText) findViewById(R.id.ed_online);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup) ;
        cash=(RadioButton)findViewById(R.id.rd_cash);
        debit=(RadioButton)findViewById(R.id.rd_debit);
        debitc = (LinearLayout)this.findViewById(R.id.layoutdebit);
        onlinet = (LinearLayout)this.findViewById(R.id.layoutOnline);
        pay = (Button)findViewById(R.id.submit);
        confirm = (Button)findViewById(R.id.btn_confirm);
        online=(RadioButton)findViewById(R.id.rd_online);
        tvbill = (TextView)findViewById(R.id.tv_bill);
        tvbill.setText(""+tvbill.getText()+""+PlaceOrder.res+"Rs");
        debitc.setVisibility(View.GONE);
        onlinet.setVisibility(View.GONE);
        db.execSQL("create table if not exists orders(id INTEGER PRIMARY KEY autoincrement,umobile int,name varchar(50),qty int,price int,totalamt int,status varchar(10))");
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cash.isChecked()&&!debit.isChecked()&&!online.isChecked())
                {

                    Toast.makeText(ConfirmedActivity.this, "Please Select Payment Method", Toast.LENGTH_SHORT).show();                }
                else if(cash.isChecked())
                {
                    debitc.setVisibility(View.GONE);
                    onlinet.setVisibility(View.GONE);
                    Toast.makeText(ConfirmedActivity.this, "CASH", Toast.LENGTH_SHORT).show();
                }
               else if (debit.isChecked())
                {
                    onlinet.setVisibility(View.GONE);
                    debitc.setVisibility(View.VISIBLE);
                }
                else if(online.isChecked())
                {
                    debitc.setVisibility(View.GONE);
                    onlinet.setVisibility(View.VISIBLE);

                }
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(card.getText().toString().equals(""))
                {
                    card.setError("Please Enter Debit Card Number");
                }else if(onlinetr.getText().toString().equals(""))
                {
                    onlinetr.setError("Please Enter Account Number To Proceed");
                }
                final Cursor c = db.rawQuery("select * from Orders", null);
                if(c.getCount()>=6)
                {
                    Toast.makeText(ConfirmedActivity.this, "Sorry For Inconvenience All Tables Are Already Taken please Wait Sometime...", Toast.LENGTH_SHORT).show();
                }
                else
                {
                card.setText("");
                onlinetr.setText("");
                db.execSQL("insert into orders" +
                        "(umobile,name,qty,price,totalamt,status)values" +
                        "('"+LoginActivity.mobileno+"','" + PlaceOrder.prname + "','"
                        + PlaceOrder.prqty + "','" + PlaceOrder.prprice +
                       "','" + PlaceOrder.ordrtotal + "','" + PlaceOrder.status + "')");
                }
            }
        });

    }
}
