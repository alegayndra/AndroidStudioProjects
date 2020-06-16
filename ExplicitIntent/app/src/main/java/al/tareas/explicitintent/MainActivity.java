package al.tareas.explicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final int request_code = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Intent i = new Intent(this, ActB.class);

        final EditText editText = findViewById(R.id.editText);
        String pregunta = editText.getText().toString();

        i.putExtra("pregunta", pregunta);

        // startActivity(i);
        startActivityForResult(i, request_code);
    }

    protected void onActivityResult(int request_code, int result_code, Intent data) {
        //super.onActivityResult(request_code, result_code, data);
        //super.onActivityResult(request_code, result_code, data);
        if ((request_code == MainActivity.request_code) && (request_code == RESULT_OK)) {
            TextView textView = findViewById(R.id.textView);

            String returnString = data.getExtras().getString("returnData");
            textView.setText(returnString);
        }
    }
}
