package com.database;

import android.content.Context;
import android.database.Cursor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;

public class DriverHelper extends DatabaseDriver {

    public DriverHelper(Context context) {
        super(context);
    }

    public long insertItem(String id, String name, int amount) {
        long rid = super.insertItem(id, name, amount);
        return rid;
    }

    public Cursor getItems() {
        Cursor items = super.getItem();
        return items;
    }

    /**
     * Returns a list of the roles in the database.
     *
     * @return a list of the role ids in the database
     */
    public List<String> getItemList() {
        List<String> items = new ArrayList<String>();
        Cursor cursor = this.getItems();

        while (cursor.moveToNext()) {
            String item = cursor.getString(cursor.getColumnIndex("NAME"));
            items.add(item);
        }

        return items;
    }

}
