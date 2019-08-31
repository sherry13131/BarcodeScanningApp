package com.example.sherry.barcodescanningapp1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.database.DriverHelper;

import java.util.List;

public class SetProductActivity extends AppCompatActivity implements View.OnClickListener {

    private String scanContent;
    private Button button_add;
    private TextView contentTxt, prodName, temp;
    private EditText prod_amount, prod_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_product);

        Context context = getApplicationContext();
        final DriverHelper db = new DriverHelper(context);

        contentTxt = findViewById(R.id.scan_content);
        prodName = findViewById(R.id.prodName);
        prod_amount = findViewById(R.id.prod_amount);
        prod_name = findViewById(R.id.prod_name);
//        temp = findViewById(R.id.temp);
        button_add = findViewById(R.id.button_add);
        button_add.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        scanContent = extras.getString("content");

        // create the table for setting the amount of product it has
        contentTxt.setText("CONTENT: " + scanContent);
//        prodName.setText("Product: ") // add the product name getting from db

        // search if such id exists
        if (db.checkItemExist(scanContent)) {
            prod_name.setEnabled(false);
            prod_name.setText(db.getItemName(scanContent));
        };

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.button_add){

            Context context = getApplicationContext();
            final DriverHelper db = new DriverHelper(context);
            CharSequence text;
            boolean inserted = false;
            Log.d("STATES", prod_amount.getText() + " " + scanContent
                    + " " + prod_name.getText());

            if (!prod_amount.getText().toString().matches("")
                    && Integer.parseInt(prod_amount.getText().toString()) < 0
                    && scanContent != null && prod_name != null) {
                int amount = Integer.valueOf(prod_amount.getText().toString());
                // check if item already exists
                if (db.checkItemExist(scanContent)) {
                    // update amount
                    int old_amount = db.getItemAmount(scanContent);
                    int new_amount = old_amount + amount;
                    if (db.updateItemAmount(scanContent, new_amount) == true) {
                        text = "Successfully updated item " + scanContent + " to " + new_amount;
                    } else {
                        text = "updated fail...";
                    }
                } else {
                    // insert new item
                    String name = prod_name.getText().toString();
                    db.insertItem(scanContent, name, amount);
                    inserted = true;
                    text = "Hello toast! you added sth " + scanContent + " with " + prod_amount.getText();
                }
            } else {
                text = "You must fill in all the fields correctly";
            }

            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            List<String> items = db.getItemList();
            Log.d("CREATION", items.toString());
            db.close();
            if (inserted) {
                finish();
            }
        }
    }
}
