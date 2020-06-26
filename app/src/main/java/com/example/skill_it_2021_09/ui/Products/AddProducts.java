package com.example.skill_it_2021_09.ui.Products;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.skill_it_2021_09.Data.Products;
import com.example.skill_it_2021_09.PostToJson.postProducts;
import com.example.skill_it_2021_09.R;

public class AddProducts extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        assert container != null;
        container.removeAllViews();
        final View view = inflater.inflate(R.layout.fragment_add_products, container, false);

        final EditText name = view.findViewById(R.id.name);
        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                if(!hasFocus)
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
        final EditText description = view.findViewById(R.id.description);
        description.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                if(!hasFocus)
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
        final EditText plaintext = view.findViewById(R.id.plaintext);
        plaintext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                if(!hasFocus)
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
        final EditText icon = view.findViewById(R.id.icon);
        icon.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                if(!hasFocus)
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
        final EditText price = view.findViewById(R.id.price);
        price.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                if(!hasFocus)
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
        final CheckBox purchasable = view.findViewById(R.id.purchasable);
        purchasable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
        Button save = view.findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postProducts postProducts = new postProducts(new Products(name.getText().toString(), description.getText().toString(), plaintext.getText().toString(), icon.getText().toString(),Integer.parseInt(price.getText().toString()), purchasable.isChecked()));
                postProducts.execute();
                requireActivity().onBackPressed();
            }
        });
        return view;
    }
}
