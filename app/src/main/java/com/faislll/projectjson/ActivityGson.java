package com.faislll.projectjson;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.faislll.projectjson.ddd.ListProduct;
import com.faislll.projectjson.ddd.Mahasiswa;
import com.faislll.projectjson.ddd.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityGson extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson);

        Gson jsonConverter = new Gson();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        Mahasiswa mhs = new Mahasiswa("lautaro","Behavior",21,"Sastra");

        // serialisasi
        String json = jsonConverter.toJson(mhs);
        Toast.makeText(this, json, Toast.LENGTH_SHORT).show();

        // deserialisasi

        //String json = "{\"jurusan\":\"Sastra\",\"namaBelakang\":\"Behavior\",\"namaDepan\":\"Lautaro\",\"umur\":21}";
        //Maahasiswa mhs1 = jsonConverter.fromJson(json, Mahasiswa.class);

        //belajar volley
        RequestQueue obj = Volley.newRequestQueue(getApplicationContext());

        String url = "http://210.210.154:4444/api/products";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //handle respons

                ArrayList<Product> listProducts = new ArrayList<>();
                try {
                    Gson gson = new Gson();
                    ListProduct list = gson.fromJson(response.getJSONObject("data").toString(),ListProduct.class);
                    listProducts.addAll(list.getProducts());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }},
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ActivityGson.this, "Volley Error", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}