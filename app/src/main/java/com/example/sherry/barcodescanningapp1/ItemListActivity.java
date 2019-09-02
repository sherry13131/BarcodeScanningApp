package com.example.sherry.barcodescanningapp1;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sherry.barcodescanningapp1.objects.Item;

import java.util.List;
import com.database.DriverHelper;

public class ItemListActivity extends AppCompatActivity {
    ListView listView;
    List<Item> listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        final Context context = getApplicationContext();
        final DriverHelper db = new DriverHelper(context);

        listView = findViewById(R.id.listview1);
        listItem = db.getItemsDetailsHelper();
        final CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), listItem);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Item itemobj = customAdapter.getItem(position);
//                Toast.makeText(getApplicationContext(),itemobj.getName(),Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, ItemContentActivity.class);
                i.putExtra("content", itemobj);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
