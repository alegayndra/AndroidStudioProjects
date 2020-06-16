package al.tareas.tarearecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
import java.util.Date;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }

    private RecyclerView mRecyclerView;
    private AdapterSearchResult mAdapterSearchResults;
    private ArrayList<SearchResult> mSearchResultsList;
    private RequestQueue mRequestQueue;

    private TextView searchResultsQuant;
    private EditText searchParam;
    private Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchResultsQuant = findViewById(R.id.searchResultsQuant);
        searchParam = findViewById(R.id.seachParam);
        searchBtn = findViewById(R.id.searchBtn);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mSearchResultsList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseSearchResults();
            }
        });
    }

    public void parseSearchResults() {
        String sParam = searchParam.getText().toString();

        sParam.trim();

        char[] sParamChar = sParam.toCharArray();
        String sParamExtra = "";

        for (int i = 0; i < sParam.length(); i++) {
            if (sParamChar[i] == ' ') {
                sParamExtra += "%20";
            } else {
                sParamExtra += sParamChar[i];
            }
        }

        String url = "https://newsapi.org/v2/everything?q=" + sParamExtra + "&apiKey=78adee78cfe344268d476a3eb9981538";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    searchResultsQuant.setText(response.getInt("totalResults") + "");
                    JSONArray jsonArray = response.getJSONArray("articles");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject result = jsonArray.getJSONObject(i);

                        String title =  result.getString("title");
                        String desc =   result.getString("description");
                        String url =    result.getString("url");
                        String urlImg = result.getString("urlToImage");
                        String date =   result.getString("publishedAt");

                        mSearchResultsList.add(new SearchResult(title, desc, url, urlImg, date));
                    }

                    mAdapterSearchResults = new AdapterSearchResult(MainActivity.this, mSearchResultsList);

                    mRecyclerView.setAdapter(mAdapterSearchResults);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mRequestQueue.add(request);
    }
}
