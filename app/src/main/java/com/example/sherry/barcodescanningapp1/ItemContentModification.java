package com.example.sherry.barcodescanningapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sherry.barcodescanningapp1.objects.Item;

public class ItemContentModification extends AppCompatActivity {

    Item itemContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_content_modification);

        Bundle extras = getIntent().getExtras();
        itemContent = (Item) extras.getSerializable("content");

        // set text on fields

        // do the upload picture as well?
    }
}
