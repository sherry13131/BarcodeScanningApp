package com.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.math.BigDecimal;

/**
 * Created by Joe on 2017-07-17.
 */

public class DatabaseDriver extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "scanning_app.db";
    private SQLiteDatabase mDefaultWritableDatabase = null;

    public DatabaseDriver(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        final SQLiteDatabase db;
        if(mDefaultWritableDatabase != null){
            db = mDefaultWritableDatabase;
        } else {
            db = super.getWritableDatabase();
        }
        return db;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE ITEMS "
                + "(ID TEXT PRIMARY KEY NOT NULL,"
                + "NAME TEXT NOT NULL,"
                + "AMOUNT INT NOT NULL,"
                + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                + "update_at NOT NULL DEFAULT CURRENT_TIMESTAMP)");

        this.mDefaultWritableDatabase = sqLiteDatabase;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ITEMS");

        this.mDefaultWritableDatabase = sqLiteDatabase;
//        onCreate(this.mDefaultWritableDatabase);
    }

    //INSERTS
    protected long insertItem(String id, String name, int amount) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", id);
        contentValues.put("NAME", name);
        contentValues.put("AMOUNT", amount);
        return sqLiteDatabase.insert("ITEMS", null, contentValues);
    }

    /*
    only for testing
     */
    public Cursor getItem() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT NAME FROM ITEMS", null);
    }

    /*
    get details of an item
    INPUT: item ID
    OUTPUT: Cursor of item
     */
    public Cursor getItemDetails(String id) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM ITEMS WHERE ID = ?",
                new String[]{String.valueOf(id)});
    }

    public Cursor getItemsDetails() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM ITEMS ORDER BY NAME", null);
    }

    protected boolean updateItemAmount(String id, int amount) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("AMOUNT", amount);
        return sqLiteDatabase.update("ITEMS", contentValues, "ID = ?", new String[]{String.valueOf(id)})
                > 0;
    }

}
