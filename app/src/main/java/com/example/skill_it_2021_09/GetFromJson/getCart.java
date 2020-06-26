package com.example.skill_it_2021_09.GetFromJson;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.skill_it_2021_09.Adapter.AdapterCart;
import com.example.skill_it_2021_09.Data.Customers;
import com.example.skill_it_2021_09.Data.Products;
import com.example.skill_it_2021_09.ui.Cart.CartFragment;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class getCart extends AsyncTask<Void, Void, Void> {

    public ArrayList<Customers> customers = new ArrayList<>();
    public ArrayList<Products> products = new ArrayList<>();
    protected String data = "";

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL mycustomersURL = new URL("http://10.0.2.2:3000/customers");
            HttpURLConnection httpURLConnection = (HttpURLConnection) mycustomersURL.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }
            JSONArray JA = new JSONArray(data);
            for (int i = 0; i < JA.length(); i++) {
                JSONObject JO = (JSONObject) JA.get(i);
                customers.add(new Customers(JO.getInt("id"), JO.getString("name"), JO.getString("email"), JO.getString("phone")));
            }
            inputStream.close();
            bufferedReader.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            data = "";
        }
        try {
            URL myproductsURL = new URL("http://10.0.2.2:3000/products");
            HttpURLConnection httpURLConnection = (HttpURLConnection) myproductsURL.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }
            JSONArray JA = new JSONArray(data);
            for (int i = 0; i < JA.length(); i++){
                JSONObject JO = (JSONObject) JA.get(i);
                products.add(new Products(JO.getString("name"),JO.getString("description"), JO.getString("plaintext"),
                        JO.getInt("id"), JO.getString("icon"), JO.getInt("price"), JO.getBoolean("purchasable")));
            }
            inputStream.close();
            bufferedReader.close();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        AdapterCart cart = new AdapterCart(CartFragment.context, products) {
            @NotNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                if (position % 2 == 1) {
                    view.setBackgroundColor(Color.LTGRAY);
                } else {
                    view.setBackgroundColor(Color.WHITE);
                }
                return view;
            }
        };
        CartFragment.order.setAdapter(cart);
        ArrayList<String> names = new ArrayList<>();
        for(int i = 0; i < this.customers.size(); i++){
            names.add(this.customers.get(i).getName());
        }
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(CartFragment.context, android.R.layout.simple_spinner_item, names);
        spinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        CartFragment.customer.setAdapter(spinnerArrayAdapter);
        CartFragment.customers = this.customers;
        CartFragment.products = this.products;
    }
}
