package com.example.sherry.barcodescanningapp1;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.CursorAdapter;

import com.example.sherry.barcodescanningapp1.objects.Item;

import java.util.List;
import com.database.DriverHelper;

public class ItemListActivity extends AppCompatActivity {
    TextView textView;
    ListView listView;
//    Item listItem[] = {};
    List<Item> listItem;
    LinearLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Context context = getApplicationContext();
        final DriverHelper db = new DriverHelper(context);

        listView=(ListView)findViewById(R.id.listview1);
        listItem = db.getItemsDetailsHelper();
//        mLayout=(LinearLayout)findViewById(R.id.linearlayout1);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), listItem);
//        for (Item item: listItem) {
//            lv = new ListView(context);
////            tv.setTypeface(Typeface.DEFAULT_BOLD);
//            tv.setPadding(30, 15, 30, 15);
//            tv.setTextSize(20);
//            tv.(item.getName());
//            mLayout.addView(tv);
//        }
        listView.setAdapter(customAdapter);
    }


}
