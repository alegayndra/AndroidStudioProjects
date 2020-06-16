package al.tareas.tarea1_conversiondegrados;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button mConvertButton;
    private EditText mTextBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextBox = (EditText) findViewById(R.id.degreesInput);

        mConvertButton = findViewById(R.id.convert_button);

        mConvertButton  .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView degreesText = (TextView)findViewById(R.id.degreesOutput);
                degreesText.setText(Integer.toString((Integer.parseInt(mTextBox.getText().toString()) - 32) * 5 / 9) + " °C");
            }
        });
    }
}
