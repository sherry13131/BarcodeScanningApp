package com.example.sherry.barcodescanningapp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sherry.barcodescanningapp1.objects.Item;

import static com.example.sherry.barcodescanningapp1.HelperFuntion.checkItemObject;

public class ItemContentActivity extends AppCompatActivity {

    TextView prod_name, prod_amount, prod_barcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_content);

        Bundle extras = getIntent().getExtras();
        Item itemContent = (Item) extras.getSerializable("content");

        if (checkItemObject(itemContent)) {
            prod_name = findViewById(R.id.prod_name2);
            prod_amount = findViewById(R.id.prod_amount2);
            prod_barcode = findViewById(R.id.prod_barcode2);
            prod_name.setText(itemContent.getName());
            prod_amount.setText(String.valueOf(itemContent.getProdAmount()));
            prod_barcode.setText(itemContent.getId());
        } else {
            Toast.makeText(getApplicationContext(),itemContent.getName(),Toast.LENGTH_SHORT).show();
        }

    }
}
