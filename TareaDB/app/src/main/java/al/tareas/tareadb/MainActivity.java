package al.tareas.tareadb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;


public class MainActivity extends AppCompatActivity {

    EditText notaText;
    TextView fechaValue;
    Button guardarBtn;
    Button eliminarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notaText = findViewById(R.id.nota);
        fechaValue = findViewById(R.id.ultima_actualizacion_valor);
        guardarBtn = findViewById(R.id.guardar_btn);
        eliminarBtn = findViewById(R.id.eliminar_btn);

        guardarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler = new DBHandler(v.getContext(), null, null, 1);

                boolean cont = true;
                int toastText = R.string.guardado_alert;

                String notaTexto = notaText.getText().toString();
                long fecha = (long) (new Date().getTime());

                Nota nota = new Nota(notaTexto, fecha);

                if (dbHandler.findNota() == null) {
                    dbHandler.addNota(nota);
                } else {
                    dbHandler.updateNota(nota);
                }

                if (cont) {
                    fechaValue.setText("" + new Date(nota.getFecha()));
                } else {
                    toastText = R.string.error;
                }

                Toast.makeText(MainActivity.this,
                        toastText,
                        Toast.LENGTH_SHORT).show();
            }
        });

        eliminarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler = new DBHandler(v.getContext(), null, null, 1);

                int toastText = R.string.eliminado_alert;

                if (dbHandler.deleteNota()) {
                    notaText.setText("");
                    fechaValue.setText("");
                } else {
                    toastText = R.string.error;
                }

                Toast.makeText(MainActivity.this,
                        toastText,
                        Toast.LENGTH_SHORT).show();
            }
        });

        buscarNota();
    }

    public void buscarNota() {
        DBHandler dbHandler = new DBHandler(this, null, null, 1);

        Nota nota = dbHandler.findNota();

        if (nota != null) {
            notaText.setText(nota.getNota());
            fechaValue.setText("" + new Date(nota.getFecha()));
        }
    }
}
