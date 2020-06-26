package com.example.skill_it_2021_09.PostToJson;

import android.os.AsyncTask;
import android.util.Log;

import com.example.skill_it_2021_09.Data.IntPair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class postCart extends AsyncTask<Void, Void, Void> {

    protected int customerID;
    protected ArrayList<IntPair> products;

    public postCart(int customerID, ArrayList<IntPair> products) {
        this.customerID = customerID;
        this.products = products;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("http://10.0.2.2:3000/orders");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept","application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            JSONObject jsonParam = new JSONObject();
            JSONArray jArray = new JSONArray();
            for(int i = 0; i < products.size(); i++){
                JSONObject jGroup = new JSONObject();
                try {
                    jGroup.put("id", products.get(i).getId());
                    jGroup.put("cnt", products.get(i).getCnt());
                    if(products.get(i).getCnt() != 0)
                        jArray.put(jGroup);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            jsonParam.put("customerId", customerID);
            jsonParam.put("products", jArray);
            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            os.writeBytes(jsonParam.toString());
            os.flush();
            os.close();
            Log.i("STATUS", String.valueOf(conn.getResponseCode()));
            Log.i("MSG" , conn.getResponseMessage());
        } catch (Exception e) {
            e.printStackTrace(); }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
