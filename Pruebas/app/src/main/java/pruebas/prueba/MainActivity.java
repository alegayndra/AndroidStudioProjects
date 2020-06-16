package pruebas.prueba;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button mConvertButton;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addKeyListener();

        mConvertButton = findViewById(R.id.convert_button);

        mConvertButton  .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView tv1 = (TextView)findViewById(R.id.degreesOutput);
                tv1.setText(Integer.toString((Integer.parseInt(mEditText.getText().toString()) - 32) * 5 / 9));
            }
        });
    }
    public void addKeyListener() {

        // get edittext component
        mEditText = (EditText) findViewById(R.id.degreesInput);

        // add a keylistener to monitor the keaybord avitvity...
        mEditText.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                // if the users pressed a button and that button was "0"
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_0)) {

                    // display the input text....
                    Toast.makeText(MainActivity.this, mEditText.getText(), Toast.LENGTH_LONG).show();
                    return true;

                    // if the users pressed a button and that button was "9"
                } else if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_9)) {

                    // display message
                    Toast.makeText(MainActivity.this, "Number 9 is pressed!", Toast.LENGTH_LONG).show();
                    return true;
                }

                return false;
            }
        });
    }

}
