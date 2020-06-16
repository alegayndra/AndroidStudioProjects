package al.tareas.databasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView productIDValue;
    EditText productNameValue;
    EditText productQuantityValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productIDValue = findViewById(R.id.ProductIDValue);
        productNameValue = findViewById(R.id.ProductNameValue);
        productQuantityValue = findViewById(R.id.ProductQuantityValue);


    }

    public void newProduct(View view) {
        DBHandler dbHandler = new DBHandler(this, null, null, 1);

        int quantity = Integer.parseInt(productQuantityValue.getText().toString());

        Product product = new Product(productNameValue.getText().toString(), quantity);

        dbHandler.addProduct(product);
        productNameValue.setText("");
        productQuantityValue.setText("");
        productIDValue.setText("Product created");
    }

    public void lookUpProduct(View view) {
        DBHandler dbHandler = new DBHandler(this, null, null, 1);

        Product product = dbHandler.findProduct(productNameValue.getText().toString());

        if (product != null) {
            productIDValue.setText(String.valueOf(product.getID()));
            productQuantityValue.setText(String.valueOf(product.getQuantity()));
        } else {
            productIDValue.setText("Product not found");
        }
    }

    public void removeProduct(View view) {
        DBHandler dbHandler = new DBHandler(this, null, null, 1);

        boolean result = dbHandler.deleteProduct(productQuantityValue.getText().toString());

        if (result) {
            productIDValue.setText("Record deleted");
            productQuantityValue.setText("");
            productNameValue.setText("");
        } else {
            productIDValue.setText("Record not found");
        }
    }
}
