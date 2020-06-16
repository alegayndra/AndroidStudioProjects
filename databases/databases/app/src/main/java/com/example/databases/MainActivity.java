package com.example.databases;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

            TextView idView;
            EditText productBox;
            EditText quantityBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idView = findViewById(R.id.productID);
        productBox = findViewById(R.id.productName);
        quantityBox = findViewById(R.id.productQuantity);

    }


    public void newProduct(View view){

        MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);

        int quantity = Integer.parseInt(quantityBox.getText().toString());

        Product product = new Product(productBox.getText().toString(),quantity);

        dbHandler.addProduct(product);
        productBox.setText("");
        quantityBox.setText("");
        idView.setText("Product Toast");
    }


    public void lookupProduct (View view){
        MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);

        Product product = dbHandler.findProduct(productBox.getText().toString());

        if (product != null){

            idView.setText(String.valueOf(product.getID()));
            quantityBox.setText(String.valueOf(product.getQuantity()));

        }
        else {
            idView.setText("Not Found!");
        }


    }


    public void removeProduct (View view){

        MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);

        boolean result = dbHandler.deleteProduct(productBox.getText().toString());

        if (result){

            idView.setText("Record Deleted");
            productBox.setText("");
            quantityBox.setText("");
        }

        else {

            idView.setText("No Record found");
        }
    }


}
