package com.example.skill_it_2021_09.ui.Cart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.skill_it_2021_09.Data.Customers;
import com.example.skill_it_2021_09.Data.IntPair;
import com.example.skill_it_2021_09.Data.Products;
import com.example.skill_it_2021_09.GetFromJson.getCart;
import com.example.skill_it_2021_09.PostToJson.postCart;
import com.example.skill_it_2021_09.NumberPicker.NumberPicker;
import com.example.skill_it_2021_09.R;

import java.util.ArrayList;

@SuppressLint("StaticFieldLeak")
public class CartFragment extends Fragment {

    public static Context context;
    public static ListView order;
    public static Spinner customer;
    public static ArrayList<Customers> customers = new ArrayList<>();
    public static ArrayList<Products> products = new ArrayList<>();
    public static ArrayList<IntPair> intpairs = new ArrayList<>();

    @Nullable
    @Override
    @SuppressLint("CutPasteId")
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentManager fm = requireActivity().getSupportFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        context = this.getContext();
        order = view.findViewById(R.id.order);
        customer = view.findViewById(R.id.customer);
        Button add = view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int customerID = 0;
                for(int i = 0; i < customers.size(); i++){
                    if(customers.get(i).getName().equals(customer.getSelectedItem().toString()))
                        customerID = customers.get(i).getId();
                }
                for(int i = 0; i < products.size(); i++){
                    if(!products.get(i).isPurchasable())
                        products.remove(i);
                }
                for(int i = 0; i < products.size(); i++){
                    NumberPicker numberPicker = order.getChildAt(i).findViewById(R.id.count);
                    intpairs.add(new IntPair(products.get(i).getId(), numberPicker.getValue()));
                }
                postCart postCart = new postCart(customerID, intpairs);
                postCart.execute();
                requireActivity().onBackPressed();
            }
        });
        new getCart().execute();
        return view;
    }
}
