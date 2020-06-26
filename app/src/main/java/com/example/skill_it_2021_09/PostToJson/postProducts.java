package com.example.skill_it_2021_09.PostToJson;

import android.os.AsyncTask;
import android.util.Log;

import com.example.skill_it_2021_09.Data.Products;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class postProducts extends AsyncTask<Void, Void, Void> {

    Products products;

    public postProducts(Products products) {
        this.products = products;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("http://10.0.2.2:3000/products");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept","application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("name", products.getName());
            jsonParam.put("description", products.getDescription());
            jsonParam.put("plaintext", products.getPlaintext());
            jsonParam.put("icon", products.getIcon());
            jsonParam.put("price", products.getPrice());
            jsonParam.put("purchasable", products.isPurchasable());
            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            os.writeBytes(jsonParam.toString());
            os.flush();
            os.close();
            Log.i("STATUS", String.valueOf(conn.getResponseCode()));
            Log.i("MSG" , conn.getResponseMessage());
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace(); }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
