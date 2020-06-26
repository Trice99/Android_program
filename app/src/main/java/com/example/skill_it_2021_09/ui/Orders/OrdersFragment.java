package com.example.skill_it_2021_09.ui.Orders;

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
import android.widget.ExpandableListView;

import com.example.skill_it_2021_09.Data.Customers;
import com.example.skill_it_2021_09.Data.Orders;
import com.example.skill_it_2021_09.Data.Products;
import com.example.skill_it_2021_09.Data.StringPair;
import com.example.skill_it_2021_09.GetFromJson.getOrders;
import com.example.skill_it_2021_09.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressLint("StaticFieldLeak")
public class OrdersFragment extends Fragment {

    public static ExpandableListView listView;
    public static View view;
    public static Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentManager fm = requireActivity().getSupportFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
        view = inflater.inflate(R.layout.fragment_orders, container, false);
        context = this.getContext();
        listView = view.findViewById(R.id.orders);
        new getOrders().execute();
        return view;
    }
}
