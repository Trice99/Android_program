package com.example.skill_it_2021_09.GetFromJson;

import android.os.AsyncTask;

import com.example.skill_it_2021_09.Adapter.ExpandableAdapterOrders;
import com.example.skill_it_2021_09.Data.Customers;
import com.example.skill_it_2021_09.Data.IntPair;
import com.example.skill_it_2021_09.Data.Orders;
import com.example.skill_it_2021_09.Data.Products;
import com.example.skill_it_2021_09.Data.StringPair;
import com.example.skill_it_2021_09.ui.Orders.OrdersFragment;

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
import java.util.HashMap;
import java.util.List;

public class getOrders extends AsyncTask<Void, Void, Void> {

    protected ArrayList<Orders> orders = new ArrayList<>();
    protected ArrayList<Products> products = new ArrayList<>();
    protected ArrayList<Customers> customers = new ArrayList<>();
    protected String data = "";

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL myordersURL = new URL("http://10.0.2.2:3000/orders");
            HttpURLConnection httpURLConnection = (HttpURLConnection) myordersURL.openConnection();
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
                JSONArray JArr = JO.getJSONArray("products");
                ArrayList<IntPair> pairs = new ArrayList<>();
                for(int j = 0; j < JArr.length(); j++){
                    JSONObject JObb = (JSONObject) JArr.get(j);
                    pairs.add(new IntPair(JObb.getInt("id"), JObb.getInt("cnt")));
                }
                orders.add(new Orders(JO.getInt("customerId"), pairs));
            }
            inputStream.close();
            bufferedReader.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            data = "";
        }
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
        }finally {
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
        List<String> listDataHeader = new ArrayList<>();;
        HashMap<String, List<StringPair>> listDataChild = new HashMap<>();
        for(int i = 0; i < orders.size(); i++){
            for(int j = 0; j < customers.size(); j++){
                if(orders.get(i).getCustomerId() == customers.get(j).getId())
                    listDataHeader.add(customers.get(j).getName());
            }
            List<StringPair> list = new ArrayList<>();
            for(int j = 0; j < orders.get(i).getProducts().size(); j++){

                for(int k = 0; k < products.size(); k++){
                    if(orders.get(i).getProducts().get(j).getId() == products.get(k).getId())
                        list.add(new StringPair(products.get(k).getName(), String.valueOf(orders.get(i).getProducts().get(j).getCnt())));
                }
            }
            listDataChild.put(listDataHeader.get(i), list);

        }
        ExpandableAdapterOrders listAdapter = new ExpandableAdapterOrders(OrdersFragment.context, listDataHeader, listDataChild);
        OrdersFragment.listView.setAdapter(listAdapter);
    }
}
