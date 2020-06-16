package al.tareas.parcial1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChangeMyName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_my_name);

        Button changeNameBtn = findViewById(R.id.changeNameBtn);

        changeNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();

                EditText newNameText = findViewById(R.id.newNameText);

                String returnString = newNameText.getText().toString();
                data.putExtra("newName", returnString);

                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
