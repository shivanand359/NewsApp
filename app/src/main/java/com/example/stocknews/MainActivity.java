package com.example.stocknews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RecyclerView newsrecyclerview;
    private List<NewsModel> newsModelList = new ArrayList<>();
    private NewsAdapter newsAdapter;
    String myurl = "https://gnews.io/api/v4/top-headlines?token=258a2fe2c624628f5f014b562cfc6549&lang=hi&country=in";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsrecyclerview = findViewById(R.id.recycle_latestnews);
        getNews();
        newsAdapter = new NewsAdapter(newsModelList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        newsrecyclerview.setLayoutManager(linearLayoutManager);
        newsrecyclerview.setAdapter(newsAdapter);
        newsAdapter.notifyDataSetChanged();

    }

    private void getNews() {

        StringRequest request = new StringRequest(Request.Method.GET, myurl, response ->
        {
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("articles");
                int length = jsonArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String title = jsonObject1.getString("title");
                    String img = jsonObject1.getString("image");


                    newsModelList.add(new NewsModel(img, title));

                }
                newsAdapter.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(MainActivity.this, "Data not found", Toast.LENGTH_LONG).show());

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);

    }
}