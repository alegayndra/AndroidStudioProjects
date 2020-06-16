package al.tareas.interfazconcodigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.EditText;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        configureLayout();
    }

    private int convertToPX(int value) {
        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, r.getDisplayMetrics());

        return px;
    }

    private void configureLayout() {
        Button myButton = new Button(this);
        myButton.setText(getString(R.string.press_me));
        myButton.setBackgroundColor(Color.YELLOW);
        myButton.setId(R.id.myButton);

        EditText editText = new EditText(this);
        editText.setId(R.id.myEditText);
        editText.setWidth(convertToPX(200));

        ConstraintLayout myLayout = new ConstraintLayout(this);
        // myLayout.setBackgroundColor(Color.BLUE);

        myLayout.addView(myButton);
        myLayout.addView(editText);

        setContentView(myLayout);

        ConstraintSet set = new ConstraintSet();
        set.constrainHeight(myButton.getId(),   ConstraintSet.WRAP_CONTENT);
        set.constrainWidth(myButton.getId(),    ConstraintSet.WRAP_CONTENT);

        set.constrainHeight(editText.getId(),   ConstraintSet.WRAP_CONTENT);
        set.constrainWidth(editText.getId(),    ConstraintSet.WRAP_CONTENT);

        set.connect(myButton.getId(), ConstraintSet.LEFT,   ConstraintSet.PARENT_ID, ConstraintSet.LEFT,    0);
        set.connect(myButton.getId(), ConstraintSet.RIGHT,  ConstraintSet.PARENT_ID, ConstraintSet.RIGHT,   0);
        set.connect(myButton.getId(), ConstraintSet.TOP,    ConstraintSet.PARENT_ID, ConstraintSet.TOP,     0);
        set.connect(myButton.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM,  0);

        set.connect(editText.getId(), ConstraintSet.LEFT,   ConstraintSet.PARENT_ID, ConstraintSet.LEFT,    0);
        set.connect(editText.getId(), ConstraintSet.RIGHT,  ConstraintSet.PARENT_ID, ConstraintSet.RIGHT,   0);
        set.connect(editText.getId(), ConstraintSet.TOP,    ConstraintSet.PARENT_ID, ConstraintSet.TOP,     0);
        set.connect(editText.getId(), ConstraintSet.BOTTOM, myButton.getId(),        ConstraintSet.BOTTOM,  0);

        set.applyTo(myLayout);
    }
}
