package com.example.skill_it_2021_09.Data;

public class Products {

    private String name;
    private String description;
    private String plaintext;
    private int id;
    private String icon;
    private int price;
    private boolean purchasable;

    public Products(){}

    public Products(String name, String description, String plaintext, String icon, int price, boolean purchasable) {
        this.name = name;
        this.description = description;
        this.plaintext = plaintext;
        this.icon = icon;
        this.price = price;
        this.purchasable = purchasable;
    }

    public Products(String name, String description, String plaintext, int id, String icon, int price, boolean purchasable) {
        this.name = name;
        this.description = description;
        this.plaintext = plaintext;
        this.id = id;
        this.icon = icon;
        this.price = price;
        this.purchasable = purchasable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlaintext() {
        return plaintext;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isPurchasable() {
        return purchasable;
    }

    public void setPurchasable(boolean purchasable) {
        this.purchasable = purchasable;
    }
}

