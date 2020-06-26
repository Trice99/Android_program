package com.example.skill_it_2021_09.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.skill_it_2021_09.Data.Customers;
import com.example.skill_it_2021_09.R;

import java.util.ArrayList;

public class AdapterCustomers extends BaseAdapter {

    private Context context;
    private ArrayList<Customers> arrayList;

    public AdapterCustomers(Context context, ArrayList<Customers> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.customerrow, parent, false);
        TextView name = convertView.findViewById(R.id.name);
        TextView email = convertView.findViewById(R.id.email);
        TextView phone = convertView.findViewById(R.id.phone);
        name.setText(arrayList.get(position).getName());
        email.setText(arrayList.get(position).getEmail());
        phone.setText(arrayList.get(position).getPhone());
        return convertView;
    }
}
