package al.tareas.overflowmenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.concurrent.Callable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.overflow_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ConstraintLayout mainLayout = findViewById(R.id.myLayout);

        switch (item.getItemId()) {
            case R.id.red:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    mainLayout.setBackgroundColor(Color.RED);
                return true;
            case R.id.green:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    mainLayout.setBackgroundColor(Color.BLUE);
                return true;
            case R.id.blue:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    mainLayout.setBackgroundColor(Color.GREEN);
                return true;
        }

        return true;
    }

}
