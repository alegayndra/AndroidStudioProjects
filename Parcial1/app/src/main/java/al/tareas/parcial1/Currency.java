package al.tareas.parcial1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Currency extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        Button calcular = findViewById(R.id.calcularBtn);

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText dolar      = findViewById(R.id.dolarValue);
                EditText pesos      = findViewById(R.id.pesosValue);
                TextView conversion = findViewById(R.id.conversionValue);

                conversion.setText(Integer.toString(Integer.parseInt(pesos.getText().toString()) / Integer.parseInt(dolar.getText().toString())) + " USD");

            }
        });
    }
}
