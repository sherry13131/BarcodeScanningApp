package com.example.sherry.barcodescanningapp1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.database.DriverHelper;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button scanBtn;
    private TextView formatTxt, contentTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        db.insertItem("222222222225", "cool", 10);
//        List<String> items = db.getItemList();
//        if (items.size() > 0 && items != null) {
//            Log.d("STATE", items.toString());
//            Log.d("STATE", "printinggggggggggggggggggggggggggggggggggggg");
//        } else {
//            Log.d("CREATION", "nothing here");
//        }

//        db.close();

        scanBtn = findViewById(R.id.scan_button);
        formatTxt = findViewById(R.id.scan_format);
        contentTxt = findViewById(R.id.scan_content);

        scanBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        System.out.println(view.getId());
        System.out.println(R.id.scan_button);
        if(view.getId()==R.id.scan_button){
            System.out.println(view.getId());
            //scan
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan(IntentIntegrator.PRODUCT_CODE_TYPES,-1);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            //we have a result
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
//            formatTxt.setText("FORMAT: " + scanFormat);
//            contentTxt.setText("CONTENT: " + scanContent);
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
