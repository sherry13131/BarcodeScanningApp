package com.example.sherry.barcodescanningapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button scanBtn, item_list_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanBtn = findViewById(R.id.scan_button);
        item_list_btn = findViewById(R.id.item_list_btn);

        scanBtn.setOnClickListener(this);
        item_list_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        System.out.println(view.getId());
        System.out.println(R.id.scan_button);
        System.out.println(R.id.item_list_btn);
        if(view.getId()==R.id.scan_button){
            System.out.println(view.getId());
            //scan
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan(IntentIntegrator.PRODUCT_CODE_TYPES,-1);
        } else if(view.getId()==R.id.item_list_btn){
            System.out.println(view.getId());
            // switch page to item list pages
            startActivity(new Intent(this, ItemListActivity.class));
        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            //we have a result
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            // take the scanContent to the next page for storing
            // if this product never exist in the database, goto AddProductActivity
            // if it is already exist in the database, goto SetProductActivity
            Intent i = new Intent(this, SetProductActivity.class);
            i.putExtra("content", scanContent);
            startActivity(i);

        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
