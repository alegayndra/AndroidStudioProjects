package al.tareas.parcial1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "StateChanged";
    private static final int request_code_calculator = 5;
    private static final int request_code_currency   = 6;
    private static final int request_code_changeName = 7;

    private int stateChanges = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        CharSequence userText = savedInstanceState.getCharSequence("stateChanges");
//
//        if (userText != null) {
//            stateChanges = Integer.parseInt(userText.toString());
//        }

        Log.i(TAG, "onCreate " + stateChanges);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView calculatorImage = findViewById(R.id.calculatorImage);
        ImageView conversionImage = findViewById(R.id.conversionImage);
        TextView  changeNameText  = findViewById(R.id.changeNameText);

        calculatorImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Calculadora.class);
                startActivity(i);
            }
        });

        conversionImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Currency.class);
                startActivity(i);
            }
        });

        changeNameText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ChangeMyName.class);
                startActivityForResult(i, request_code_changeName);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart" + ++stateChanges);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume " + ++stateChanges);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause " + ++stateChanges);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop " + ++stateChanges);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart " + ++stateChanges);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy " + ++stateChanges);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.i(TAG, "onSaveInstanceState " + ++stateChanges);

        CharSequence userText = Integer.toString(stateChanges);
        outState.putCharSequence("stateChanges", userText);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState );

        CharSequence userText = savedInstanceState.getCharSequence("stateChanges");
        stateChanges = Integer.parseInt(userText.toString());
        Log.i(TAG, "onRestoreInstanceState " + ++stateChanges);
    }

    @Override
    protected void onActivityResult(int request_code, int result_code, Intent data) {
        super.onActivityResult(request_code_changeName, result_code, data);
        //super.onActivityResult(request_code, result_code, data);
//        if ((request_code == RESULT_OK)) {
            TextView nameToChange = findViewById(R.id.changeNameText);

            String returnString = data.getExtras().getString("newName");
            nameToChange.setText(returnString);
//        }
    }
}
