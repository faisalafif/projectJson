package com.faislll.projectjson;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.faislll.projectjson.ddd.ListProduct;
import com.faislll.projectjson.ddd.Product;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    final AdapterRv adapterRv = new AdapterRv(this);
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inisialKomponen();
       inisialKomponen();
       VolleyLoad();
    }

    public void inisialKomponen(){
        recyclerView = findViewById(R.id.rv_home);
    }

    public void VolleyLoad() {
        requestQueue = Volley.newRequestQueue(this);
        String url = "http://210.210.154:4444/api/products";
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    ArrayList<Product> data = new ArrayList<>();
                    //JsonArray jsonArray = response.getJSONArray("data");
                    Gson gson = new Gson();
                    ListProduct listProduct = gson.fromJson(response.toString(), ListProduct.class);
                    data.addAll(listProduct.getProducts());

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapterRv);
                    adapterRv.addData(data);

                    Toast.makeText(MainActivity.this, String.valueOf(adapterRv.getItemCount()), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }},
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Volley Error", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(req);
    }

}