package com.example.sherry.barcodescanningapp1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.database.DriverHelper;
import com.example.sherry.barcodescanningapp1.objects.Item;

import static android.text.TextUtils.isEmpty;

public class ItemContentModification extends AppCompatActivity implements View.OnClickListener{

    Item itemContent;
    EditText amount_e, name_e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_content_modification);

        Bundle extras = getIntent().getExtras();
        itemContent = (Item) extras.getSerializable("content");

        // set text on fields
        amount_e = findViewById(R.id.prod_amount3);
        name_e = findViewById(R.id.prod_name3);
        amount_e.setText(String.valueOf(itemContent.getProdAmount()));
        name_e.setText(itemContent.getName());

        // set update btn function
        final Button update_btn = findViewById(R.id.button_update);
        update_btn.setOnClickListener(this);
//        update_btn.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//
//            }
//        });

        // do the upload picture as well?
    }

    @Override
    public void onClick(View view) {
        final Context context = getApplicationContext();
        final DriverHelper db = new DriverHelper(context);
        Toast toast;
        if(view.getId()==R.id.button_update){
            boolean updated = false;
            // check all field are all filled in
            if (HelperFuntion.checkIsEmpty(name_e.getText().toString())
                    || HelperFuntion.checkIsEmpty(amount_e.getText().toString())) {
                // do toast pop up
                String text = "All the required fields has to be filled in";
                toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
            } else if (!HelperFuntion.checkIsValidNum(Integer.parseInt(amount_e.getText().toString()))) {
                // do toast pop up
                String text = "Amount should not be negative number";
                toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
            } else {
                // update if different content
                int newAmount = Integer.parseInt(amount_e.getText().toString());
                String newName = name_e.getText().toString();
                try {
                    if (newAmount != itemContent.getProdAmount()) {
                        db.updateItemAmount(itemContent.getId(), newAmount);
                    }
                    if (newName != itemContent.getName()) {
                        db.updateItemName(itemContent.getId(), newName);
                    }
                    updated = true;
                    String text = "Update successfully";
                    itemContent.setName(newName);
                    itemContent.setProdAmount(newAmount);
                    toast = Toast.makeText(this, text, Toast.LENGTH_LONG);
                } catch (Exception e) {
                    String text = e.toString();
                    toast = Toast.makeText(this, text, Toast.LENGTH_LONG);
                }
            }
            toast.show();
            if (updated) {
                Intent intent = new Intent(this, ItemContentActivity.class);
                intent.putExtra("content", itemContent);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
                finish();
            }
        }
    }
}
