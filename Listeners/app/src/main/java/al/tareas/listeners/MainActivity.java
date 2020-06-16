package al.tareas.listeners;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                TextView textView = findViewById(R.id.textView);

                textView.setText("Button clicked");
            }
        });

        button.setOnLongClickListener(new Button.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                TextView textView = findViewById(R.id.textView);

                textView.setText("Button long clicked");

                return true;
            }
        });
    }
}
