package com.example.sherry.barcodescanningapp1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.database.DriverHelper;

import java.io.FileNotFoundException;
import java.util.List;

public class SetProductActivity extends AppCompatActivity implements View.OnClickListener {

    final int PICK_FROM_GALLERY = 0, PICK_FROM_CAMERA = 1;
    private String scanContent;
    private Button button_add, button_add_gallery, button_add_camera;
    private TextView contentTxt, prodName, temp, prod_image_name;
    private EditText prod_amount, prod_name;

    ImageView targetImage;

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
        prod_image_name = findViewById(R.id.prod_image_name);
        button_add = findViewById(R.id.button_add);
        button_add_gallery = findViewById(R.id.button_add_gallery);
        button_add_camera = findViewById(R.id.button_add_camera);
        button_add.setOnClickListener(this);
        button_add_gallery.setOnClickListener(this);
        button_add_camera.setOnClickListener(this);
        targetImage = findViewById(R.id.imageView);

        Bundle extras = getIntent().getExtras();
        scanContent = extras.getString("content");

        // create the table for setting the amount of product it has
        contentTxt.setText("CONTENT: " + scanContent);

        // search if such id exists
        if (db.checkItemExist(scanContent)) {
            prod_name.setEnabled(false);
            prod_name.setText(db.getItemName(scanContent));
        }
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
                    && Integer.parseInt(prod_amount.getText().toString()) > 0
                    && scanContent != null && prod_name != null) {
                int amount = Integer.valueOf(prod_amount.getText().toString());
                // check if item already exists
                if (db.checkItemExist(scanContent)) {
                    // update amount
                    int old_amount = db.getItemAmount(scanContent);
                    int new_amount = old_amount + amount;
                    if (db.updateItemAmount(scanContent, new_amount) == true) {
                        text = "Successfully updated item " + scanContent + " to " + new_amount;
                        inserted = true;
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
        } else if (view.getId() == R.id.button_add_gallery) {
            Intent intent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_FROM_GALLERY);
        } else if (view.getId() == R.id.button_add_camera) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, PICK_FROM_CAMERA);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
            Uri targetUri = data.getData();
            prod_image_name.setText(targetUri.toString());
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                targetImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (requestCode == PICK_FROM_CAMERA && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            targetImage.setImageBitmap(imageBitmap);
        }
    }
}
