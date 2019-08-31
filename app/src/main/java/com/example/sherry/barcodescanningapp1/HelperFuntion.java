package com.example.sherry.barcodescanningapp1;

import com.example.sherry.barcodescanningapp1.objects.Item;

public class HelperFuntion {
    public static Boolean checkItemObject(Item item) {
        if (item.getId().isEmpty() || item.getName().isEmpty() || item.getProdAmount() < 0) {
            return false;
        }
        return true;
    }
}
