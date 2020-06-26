package com.example.skill_it_2021_09.ui.Customers;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.skill_it_2021_09.Data.Customers;
import com.example.skill_it_2021_09.PostToJson.postCustomers;
import com.example.skill_it_2021_09.R;

public class AddCustomers extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        assert container != null;
        container.removeAllViews();
        final EditText name, email, phone;
        final View view = inflater.inflate(R.layout.fragment_add_customers, container, false);
        Button save = view.findViewById(R.id.save);
        name = view.findViewById(R.id.name);
        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                if(!hasFocus)
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
        email = view.findViewById(R.id.email);
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                if(!hasFocus)
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
        phone = view.findViewById(R.id.phone);
        phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                if(!hasFocus)
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postCustomers postCustomers = new postCustomers(new Customers(name.getText().toString(), email.getText().toString(), phone.getText().toString()));
                postCustomers.execute();
                requireActivity().onBackPressed();
            }
        });

        return view;
    }
}
