package com.example.sherry.barcodescanningapp1;

import android.graphics.Bitmap;

import com.example.sherry.barcodescanningapp1.objects.Item;

import java.io.ByteArrayOutputStream;

public class HelperFuntion {
    public static Boolean checkItemObject(Item item) {
        if (item.getId().isEmpty() || item.getName().isEmpty() || item.getProdAmount() < 0) {
            return false;
        }
        return true;
    }

    public static Boolean checkIsEmpty(String item) {
        if (item.isEmpty()) {
            return true;
        } else if (item.matches("")) {
            return true;
        }
        return false;
    }

    public static Boolean checkIsValidNum(int item) {
        if (item > 0) {
            return true;
        }
        return false;
    }

    public static Boolean isSameContent(String t1, String t2) {
        if (t1.matches(t2)) {
            return true;
        }
        return false;
    }

    public static Boolean isSameContent(int t1, int t2) {
        if (t1 == t2) {
            return true;
        }
        return false;
    }

    public static byte[] getBitmapArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }
}
