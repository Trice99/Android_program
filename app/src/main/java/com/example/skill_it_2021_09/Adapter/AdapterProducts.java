package com.example.skill_it_2021_09.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.skill_it_2021_09.Data.Products;
import com.example.skill_it_2021_09.R;
import com.example.skill_it_2021_09.ui.Products.ProductsFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterProducts extends BaseAdapter {

    private Context context;
    private ArrayList<Products> arrayList;

    public AdapterProducts(Context context, ArrayList<Products> arrayList) {
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

    @SuppressLint({"ViewHolder", "DefaultLocale"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.productsrow, parent, false);
        ImageView icon = convertView.findViewById(R.id.icon);
        TextView name = convertView.findViewById(R.id.name);
        TextView plaintext = convertView.findViewById(R.id.plaintext);
        TextView description = convertView.findViewById(R.id.description);
        TextView price = convertView.findViewById(R.id.price);
        CheckBox purchasable = convertView.findViewById(R.id.purchasable);
        Picasso.get().load(arrayList.get(position).getIcon()).into(icon);
        name.setText(arrayList.get(position).getName());
        plaintext.setText(arrayList.get(position).getPlaintext());
        description.setText(arrayList.get(position).getDescription());
        price.setText(String.format("%d", arrayList.get(position).getPrice()));
        purchasable.setChecked(arrayList.get(position).isPurchasable());
        purchasable.setEnabled(false);
        return convertView;
    }
}
