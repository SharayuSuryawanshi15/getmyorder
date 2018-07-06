package com.example.hotelmanagement;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.zip.Inflater;

/**
 * Created by J.A.R.V.I.S on 7/8/2017.
 */

public class ListViewAdapter extends ArrayAdapter<String> {
    private final Activity context1;
    private final String  name[];
    private  final String price[];
    private final String id[];
    ListViewAdapter(Activity context1 ,String [] id, String [] name,String [] price)
    {
        super(context1,R.layout.list_layout,name);
        this.context1 =context1 ;
        this.name = name;
        this.price = price;
        this.id = id;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context1.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_layout, null, true);
        TextView tv = (TextView) rowView.findViewById(R.id.textView_name);
        tv.setText(name[position]);
        TextView tva = (TextView) rowView.findViewById(R.id.textView_price);
        tva.setText(price[position]);
        TextView tvid = (TextView) rowView.findViewById(R.id.textView_id);
        tvid.setText(id[position]);

        return rowView;
    }
}