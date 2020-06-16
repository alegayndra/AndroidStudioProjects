package al.tareas.explicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ActB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_b);

        Bundle extras = getIntent().getExtras();

        if (extras == null) {
            return;
        }

        String qString = extras.getString("pregunta");

        final TextView textView = findViewById(R.id.textView2);
        textView.setText(qString);
    }

    public void onClick(View view) {
        Intent data = new Intent();
        EditText editText = findViewById(R.id.editText2);

        String returnString = editText.getText().toString();
        data.putExtra("returnData", returnString);

        setResult(RESULT_OK, data);
        finish();
    }
}
