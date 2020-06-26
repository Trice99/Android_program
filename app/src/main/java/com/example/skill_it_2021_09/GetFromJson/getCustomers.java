package com.example.skill_it_2021_09.GetFromJson;

import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;

import com.example.skill_it_2021_09.Adapter.AdapterCustomers;
import com.example.skill_it_2021_09.Data.Customers;
import com.example.skill_it_2021_09.ui.Customers.CustomersFragment;

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

public class getCustomers extends AsyncTask<Void, Void, Void> {

    protected String data = "";
    public ArrayList<Customers> customers = new ArrayList<>();

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
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        AdapterCustomers cust = new AdapterCustomers(CustomersFragment.context, customers) {
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
        CustomersFragment.customers.setAdapter(cust);
    }
}
