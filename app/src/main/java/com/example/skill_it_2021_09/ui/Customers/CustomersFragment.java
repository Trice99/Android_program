package com.example.skill_it_2021_09.ui.Customers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.skill_it_2021_09.GetFromJson.getCustomers;
import com.example.skill_it_2021_09.R;

@SuppressLint("StaticFieldLeak")
public class CustomersFragment extends Fragment {

    public static ListView customers;
    public static Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentManager fm = requireActivity().getSupportFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
        View view = inflater.inflate(R.layout.fragment_customers, container, false);
        customers = view.findViewById(R.id.customers);
        context = this.getContext();
        Button add = view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in,
                        android.R.animator.fade_out).addToBackStack(null).replace(((ViewGroup) requireView().getParent()).getId(),new AddCustomers()).commit();
            }
        });
        new getCustomers().execute();
        return view;
    }
}
