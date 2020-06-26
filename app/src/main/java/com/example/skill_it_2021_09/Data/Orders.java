package com.example.skill_it_2021_09.Data;

import java.util.ArrayList;

public class Orders {

    private int customerId;
    private ArrayList<IntPair> products;

    public Orders(int customerId, ArrayList<IntPair> products) {
        this.customerId = customerId;
        this.products = products;
    }

    public int getCustomerId() {
        return customerId;
    }

    public ArrayList<IntPair> getProducts() {
        return products;
    }

}

