package com.example.sherry.barcodescanningapp1.objects;

import java.io.Serializable;

public class Item implements Serializable {
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

    public void setId(String id) {
        this.prod_id = id;
    }

    public String getName() {
        return prod_name;
    }

    public void setName(String name) {
        this.prod_name = name;
    }

    public int getProdAmount() {
        return prod_amount;
    }

    public void setProdAmount(int amount) {
        this.prod_amount = amount;
    }
}
