package com.example.sherry.barcodescanningapp1;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sherry.barcodescanningapp1.objects.Item;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    Context context;
    List<Item> itemList;
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Item getItem(int i) {
        return this.itemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return Long.parseLong(this.itemList.get(i).getId());
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.activity_listview, null);
        TextView itemName = (TextView) view.findViewById(R.id.textView1);
//        TextView itemId = (TextView) view.findViewById(R.id.icon);
        itemName.setText(itemList.get(i).getName());
        return view;
    }

}