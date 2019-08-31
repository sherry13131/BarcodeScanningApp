package com.database;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.sherry.barcodescanningapp1.objects.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedList;
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

    /*
    get an item details
     */
    public Cursor getItemDetails(String id) {
        Cursor item = super.getItemDetails(id);
        return item;
    }

    /*
    check if an item exist
    INPUT: string id
    OUTPUT: boolean
     */
    public Boolean checkItemExist(String id) {
        Cursor cursor = this.getItemDetails(id);

        if (cursor.moveToNext()) {
            return true;
        } else {
            return false;
        }
    }

    /*
    get name of an item
    INPUT: String id
    OUTPUT: String name of the item
     */
    public String getItemName(String id) {
        String name = null;
        Cursor cursor = this.getItemDetails(id);

        while (cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndex("NAME"));
        }
        return name;
    }

    /*
    get the amount of an item
    INPUT: String id
    OUTPUT: int amount of the item
     */
    public int getItemAmount(String id) {
        int amount = -1;
        Cursor cursor = this.getItemDetails(id);

        while (cursor.moveToNext()) {
            amount = cursor.getInt(cursor.getColumnIndex("AMOUNT"));
        }
        return amount;
    }

    public List<Item> getItemsDetailsHelper() {
        Cursor itemCursor = super.getItemsDetails();
        List<Item> items = new ArrayList<Item>();
        while (itemCursor.moveToNext()) {
            Item item = new Item(String.valueOf(itemCursor.getString(itemCursor.getColumnIndex("ID"))),
                    String.valueOf(itemCursor.getString(itemCursor.getColumnIndex("NAME"))),
                    itemCursor.getInt(itemCursor.getColumnIndex("AMOUNT")));
            items.add(item);

        }
        return items;
    }

    public Item[] getItemsDetailsArrayHelper() {
        Cursor itemCursor = super.getItemsDetails();
        Item items[] = {};
        int i = 0;
        while (itemCursor.moveToNext()) {
            Item item = new Item(String.valueOf(itemCursor.getString(itemCursor.getColumnIndex("ID"))),
                    String.valueOf(itemCursor.getString(itemCursor.getColumnIndex("NAME"))),
                    itemCursor.getInt(itemCursor.getColumnIndex("AMOUNT")));
            items[i] = item;
            i += 1;
        }
        return items;
    }

    /*
    update the amount of an item
    INPUT: String item id, int new amount
    OUTPUT: boolean updated
     */
    public boolean updateItemAmount(String id, int amount) {
        boolean updated = super.updateItemAmount(id, amount);
        return updated;
    }

}
