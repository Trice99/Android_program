package com.example.skill_it_2021_09.GetFromJson;

import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;

import com.example.skill_it_2021_09.Adapter.AdapterProducts;
import com.example.skill_it_2021_09.Data.Products;
import com.example.skill_it_2021_09.ui.Products.ProductsFragment;

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

public class getProducts extends AsyncTask<Void, Void, Void> {

    public ArrayList<Products> products = new ArrayList<>();
    protected String data = "";

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL myproductsURL = new URL("http://10.0.2.2:3000/products");
            HttpURLConnection httpURLConnection = (HttpURLConnection) myproductsURL.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data += line;
            }
            JSONArray JA = new JSONArray(data);
            for (int i = 0; i < JA.length(); i++){
                JSONObject JO = (JSONObject) JA.get(i);
                products.add(new Products(JO.getString("name"),JO.getString("description"), JO.getString("plaintext"),
                        JO.getInt("id"), JO.getString("icon"), JO.getInt("price"), JO.getBoolean("purchasable")));
            }
            inputStream.close();
            bufferedReader.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        AdapterProducts prod = new AdapterProducts(ProductsFragment.context, products){
            @NotNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                if(position%2 == 1){
                    view.setBackgroundColor(Color.LTGRAY);
                }else{
                    view.setBackgroundColor(Color.WHITE);
                }
                return view;
            }
        };
        ProductsFragment.products.setAdapter(prod);
    }
}
