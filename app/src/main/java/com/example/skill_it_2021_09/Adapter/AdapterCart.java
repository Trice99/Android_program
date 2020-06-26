package com.example.skill_it_2021_09.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.skill_it_2021_09.Data.Products;
import com.example.skill_it_2021_09.NumberPicker.NumberPicker;
import com.example.skill_it_2021_09.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AdapterCart extends BaseAdapter {

    private Context context;
    private ArrayList<Products> arrayList;
    public AdapterCart(Context context, @NotNull ArrayList<Products> arrayList) {
        this.context = context;
        this.arrayList = new ArrayList<>();
        for(int i = 0; i < arrayList.size(); i++){
            if(arrayList.get(i).isPurchasable())
                this.arrayList.add(arrayList.get(i));
        }
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint({"ViewHolder", "DefaultLocale", "SetTextI18n"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.cartrow, parent, false);
        TextView name = convertView.findViewById(R.id.name);
        ImageView icon = convertView.findViewById(R.id.icon);
        TextView price = convertView.findViewById(R.id.subtotal);
        name.setText(arrayList.get(position).getName());
        Picasso.get().load(arrayList.get(position).getIcon()).into(icon);
        price.setText(Integer.toString(arrayList.get(position).getPrice()));
        return convertView;
    }
}
