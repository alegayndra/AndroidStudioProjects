package al.tareas.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private AdapterClima mAdapterClima;
    private ArrayList<Clima> mClimaList;
    private RequestQueue mRequestQueue;

    private TextView txt_actual;
    private TextView txt_clima_descripcion;
    private TextView txt_nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_actual = findViewById(R.id.txt_clima_actual);
        txt_clima_descripcion = findViewById(R.id.txt_forecastDescripcion);
        txt_nombre = findViewById(R.id.txt_nombreUbicacion);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mClimaList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);

        parseClimaActual();
        parseClima5Dias();
    }

    public void parseClimaActual() {
        String url = "http://api.openweathermap.org/data/2.5/weather?q=Monterrey,Mx&appid=aea208a58f22c7fe32632fe1c466f147&units=metric&lang=es";

        JsonObjectRequest request =  new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject temp_actual = response.getJSONObject("main");
                    Integer actual = (int) Float.parseFloat(temp_actual.getString("temp"));
                    txt_actual.setText(actual + " C");
                    String descripcion = response.getJSONArray("weather").getJSONObject(0).getString("description");
                    txt_clima_descripcion.setText(descripcion);
                    txt_nombre.setText(response.getString("name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mRequestQueue.add(request);
    }

    public void parseClima5Dias() {
        String url = "http://api.openweathermap.org/data/2.5/weather?q=Monterrey,Mx&appid=aea208a58f22c7fe32632fe1c466f147&units=metric&lang=es";

        JsonObjectRequest request =  new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("list");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject clima = jsonArray.getJSONObject(i);

                        String imageUlr = Uri.parse("http://openweathermap.org/img/wn/" + clima.getJSONArray("weather").getJSONObject(0).getString("icon") + ".png").toString();
                        String fecha = clima.getString("dt_txt");
                        String descripcion = clima.getJSONArray("weather").getJSONObject(0).getString("main");
                        String tempMinMax = (int) Float.parseFloat(clima.getJSONObject("main").getString("temp_min")) + " C";

                        mClimaList.add(new Clima(imageUlr, fecha, descripcion, tempMinMax));
                    }

                    mAdapterClima = new AdapterClima(MainActivity.this, mClimaList);
                    mRecyclerView.setAdapter(mAdapterClima);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mRequestQueue.add(request);
    }
}
