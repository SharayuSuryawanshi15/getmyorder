package com.example.hotelmanagement;

/**
 * Created by J.A.R.V.I.S on 10/5/2017.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

/**
 * Created by J.A.R.V.I.S on 7/8/2017.
 */

public class OrderListAdapter extends ArrayAdapter<String> {
        public final Activity context;
        public final String prname[];
        public final String uid[];
        public final String prqty[];
        public final String umobile[];
        public final String prprice[];
        public final String totalamt[];
        public final String status[];
        View rowView;
        OrderListAdapter(Activity context, String[] uid,String[] umobile, String[] prname,
            String [] prqty, String [] prprice,
            String []totalamt, String []status) {

                super(context, R.layout.orders_list_layout, prname);
                this.context = context;
                this.uid = uid;
                this.umobile = umobile;
                this.prname = prname;
                this.prqty = prqty;
                this.prprice = prprice;
                this.totalamt = totalamt;
                this.status = status;

        }

    public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
             rowView = inflater.inflate(R.layout.orders_list_layout, null, true);

            try {
                    TextView tvpid = (TextView) rowView.findViewById(R.id.textView_mob);
                    tvpid.setText(uid[position]);

                    TextView tvumo = (TextView) rowView.findViewById(R.id.tv_mobi);
                    tvumo.setText(umobile[position]);


                    TextView tva = (TextView) rowView.findViewById(R.id.textView_pname);
                    tva.setText(prname[position]);

                    TextView tvqty = (TextView) rowView.findViewById(R.id.textView_qty);
                    tvqty.setText(prqty[position]);


                    TextView tvprice = (TextView) rowView.findViewById(R.id.textView_price);
                    tvprice.setText(prprice[position]);


                    TextView tvtotal = (TextView) rowView.findViewById(R.id.textView_total);
                    tvtotal.setText(totalamt[position]);

                    TextView tvstatus = (TextView) rowView.findViewById(R.id.textView_status);
                    tvstatus.setText(status[position]);

            }
            catch (Exception ex)
            {
                    Toast.makeText(context, "Error="+ex.toString(), Toast.LENGTH_SHORT).show();
            }
            return rowView;
    }


}
