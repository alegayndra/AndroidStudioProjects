package com.example.clima;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private AdapterClima mAdapterClima;
    private ArrayList<Clima> mClimaLisT;
    private RequestQueue mRequestQueue;
    private SwipeRefreshLayout swipeRefreshLayout;

    private TextView txtactual;
    private TextView txt_clima_descripcion;
    private TextView txt_nombreUbicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtactual = findViewById(R.id.txt_clima_actual);
        txt_clima_descripcion = findViewById(R.id.txt_clima_descripcion);
        txt_nombreUbicacion = findViewById(R.id.txt_nombreUbicacion);
        swipeRefreshLayout = findViewById(R.id.swipe_viewt);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
       mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

       mClimaLisT = new ArrayList<>();
        VolleyLog.DEBUG = true;

        mRequestQueue = Volley.newRequestQueue(this);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mClimaLisT.clear();
                parseClimaActual();
                parseClima5Dias();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        mClimaLisT.clear();
        parseClimaActual();
        parseClima5Dias();

    }

    private void parseClimaActual() {

        String url ="http://api.openweathermap.org/data/2.5/weather?q=Monterrey,Mx&appid=aea208a58f22c7fe32632fe1c466f147&units=metric&lang=es";

        // Request a string response from the provided URL.
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    String  temp_actual = "";
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the first 500 characters of the response string.
                        try {
                            //Obtenemos el clima actual
                            JSONObject temp_actual = response.getJSONObject("main");
                            Integer actual = (int)Float.parseFloat(temp_actual.getString("temp"));
                            txtactual.setText(actual + "°C");
                            String descripcion = response.getJSONArray("weather").getJSONObject(0).getString("description");
                            txt_clima_descripcion.setText(descripcion);
                            txt_nombreUbicacion.setText(response.getString("name"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
             //   textView.setText("That didn't work!");
                VolleyLog.e("Error: ", error.getMessage());
            }
        });
        mRequestQueue.add(request);
    }


    private void parseClima5Dias() {

        String url ="http://api.openweathermap.org/data/2.5/forecast?q=Monterrey,Mx&appid=aea208a58f22c7fe32632fe1c466f147&units=metric&lang=es";


        // Request a string response from the provided URL.
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    String  temp_actual = "";
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the first 500 characters of the response string.
                        try {

                            JSONArray jsonArray = response.getJSONArray("list");

                            for (int i =0; i <jsonArray.length(); i++){

                                JSONObject clima = jsonArray.getJSONObject(i);
                                String imageUrl = Uri.parse("http://openweathermap.org/img/wn/" + clima.getJSONArray("weather").getJSONObject(0).getString("icon") + "@2x.png").toString();
                                String Fecha = clima.getString("dt_txt");
                                String Descripcion = clima.getJSONArray("weather").getJSONObject(0).getString("main");
                                String TempMinMax =  (int)Float.parseFloat(clima.getJSONObject("main").getString("temp_min"))  + "°";
                                mClimaLisT.add(new Clima(imageUrl,Fecha,Descripcion,TempMinMax));
                            }

                            mAdapterClima = new AdapterClima(MainActivity.this,mClimaLisT);
                            mRecyclerView.setAdapter(mAdapterClima);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //   textView.setText("That didn't work!");
                VolleyLog.e("Error: ", error.getMessage());
            }
        });
        mRequestQueue.add(request);
    }
}
