package al.tareas.tarea2_quizz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;

public class MainActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView questionText;
    private AdminDePreguntas adminPeguntas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        crearPreguntas();


        questionText = findViewById(R.id.question);

        mTrueButton = findViewById(R.id.true_button);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checarPregunta(true);
            }
        });

        mFalseButton = findViewById(R.id.false_button);

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checarPregunta(false);
            }
        });

        mNextButton = findViewById(R.id.next_button);

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasarDePregunta();
            }
        });

        pasarDePregunta();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        CharSequence userText = questionText.getText();
        outState.putCharSequence("savedText", userText);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        CharSequence userText = savedInstanceState.getCharSequence("savedText");
        questionText.setText(userText);
    }

    protected void crearPreguntas() {
        adminPeguntas = new AdminDePreguntas();

        // agregar las preguntas
        adminPeguntas.agregarPregunta("¿C++ es el mejor lenguaje de programación?");
        adminPeguntas.agregarPregunta("¿La pizza hawaiana es aceptable?");
        adminPeguntas.agregarPregunta("¿Él/Ella te quiere?");
        adminPeguntas.agregarPregunta("¿Los huevitos Kinder son amor y son vida?");
        adminPeguntas.agregarPregunta("¿Kirby es tu presidente?");

        // agregar las respuestas
        adminPeguntas.setRespuesta(0, true);
        adminPeguntas.setRespuesta(1, false);
        adminPeguntas.setRespuesta(2, false);
        adminPeguntas.setRespuesta(3, true);
        adminPeguntas.setRespuesta(4, true);
    }

    protected void pasarDePregunta() {
        int x;
        do {
            x = (int)((Math.random() * (4.5)));
        } while (x == adminPeguntas.getIdPregunta());
        adminPeguntas.setIdPregunta(x);
        questionText.setText(adminPeguntas.getPregunta(adminPeguntas.getIdPregunta()));
    }

    protected void checarPregunta(boolean respuesta) {

        Toast.makeText(MainActivity.this,
            (respuesta == adminPeguntas.getRespuesta(adminPeguntas.getIdPregunta())) ? R.string.correct_toast : R.string.false_toast,
            Toast.LENGTH_SHORT).show();

    }
}
