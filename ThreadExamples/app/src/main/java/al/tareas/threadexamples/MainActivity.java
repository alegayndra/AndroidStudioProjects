package al.tareas.threadexamples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private class MyTask extends AsyncTask<String, Integer, String> {

        int core = Runtime.getRuntime().availableProcessors();

        @Override
        protected String doInBackground(String... strings) {
            int i = 0;

            while (i <= 10) {
                try {
                    Thread.sleep(1000);
                    publishProgress(i);
                    i++;
                }
                catch (Exception e){

                }
            }
            return "Button pressed";
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            textView.setText("Counter = " + values[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            textView.setText(result);
        }


    }

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
    }

    public void buttonClick(View v) {
        AsyncTask task = new MyTask().execute();
    }

}
