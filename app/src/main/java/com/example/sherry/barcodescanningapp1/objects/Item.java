package com.example.sherry.barcodescanningapp1.objects;

public class Item {
    String prod_id;
    String prod_name;
    int prod_amount;

    public Item (String id, String name, int amount) {
        prod_id = id;
        prod_name = name;
        prod_amount = amount;
    }

    public String getId() {
        return prod_id;
    }

    public String getName() {
        return prod_name;
    }

    public int getProdAmount() {
        return prod_amount;
    }
}
