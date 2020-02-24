package com.faislll.projectjson;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Hashtable;
import java.util.Map;

public class AddProductActivuty extends AppCompatActivity {

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_activuty);

        requestQueue = Volley.newRequestQueue(this);

        //kirim product

        String productName = "Samsung Galaxy s30+";
        String qty = "12";
        String catId = "1";
        String merchantId = "2";

        String url = "http://210.210.154:4444/api/products";

       final Map<String, String> data = new Hashtable<String, String>();
       data.put("productName",productName);
       data.put("productQty", qty);
       data.put("categoryId", catId);
       data.put("merchantId",merchantId);

        StringRequest addProductREq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new Hashtable<String, String>();
                param = data;
                return param;
            }

        };
    }
}
