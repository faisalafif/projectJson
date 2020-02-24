package com.faislll.projectjson;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.faislll.projectjson.ddd.ListProduct;
import com.faislll.projectjson.ddd.Product;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class AsyncTaskData extends AsyncTask<String,Void,ArrayList<Product>> {
    ProgressDialog pd;
    WeakReference<AdapterRv> adapterRv;
    Context context;
    public static String json;

    public AsyncTaskData(Context context, AdapterRv adapterRv){
        this.context = context;
        this.adapterRv = new WeakReference<>(adapterRv);
    }

    @Override
    protected void onPreExecute() {
        pd = new ProgressDialog(context);
        pd.show();
    }

    @Override
    protected ArrayList<Product> doInBackground(String... url) {

        ArrayList<Product> products = null;
        json = loadJsonFromApi(url[0]);
        products = deserialisasiJSON(json);

        return products;
    }


    @Override
    protected void onPostExecute(ArrayList<Product> products) {
        pd.dismiss();
        AdapterRv adapternya = adapterRv.get();
        adapternya.addData(products);
        adapternya.notifyDataSetChanged();
    }

    public String loadJsonDataFromRaw(InputStream isParam){
        String json = null;
        try{
            InputStream is = isParam;
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);

            StringBuffer sb = new StringBuffer();
            String eachLine;
            while ((eachLine = reader.readLine()) != null){
                sb.append(eachLine);
            }

            json = sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

    public ArrayList<Product> deserialisasiJSON(String jsonParam){
        ArrayList<Product> data = new ArrayList<>();
        try {
            JSONObject jsonAwal = new JSONObject(jsonParam);
//            JSONArray jsonData = jsonAwal.getJSONArray("data");

            Gson gson = new Gson();
            ListProduct listProducts = gson.fromJson(jsonAwal.toString(), ListProduct.class);
            data.addAll(listProducts.getProducts());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }

    private String loadJsonFromApi(String urlParam){
        String json = null;

        //network calls
        try {
            HttpURLConnection connection = null;
            URL url = new URL(urlParam);
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);

                BufferedReader reader = new BufferedReader(isr);

                StringBuffer sb = new StringBuffer();
                String eachLine;
                while ((eachLine = reader.readLine()) != null){
                    sb.append(eachLine);
                }

                json = sb.toString();

            }catch (Exception ex){
                ex.printStackTrace();
            }finally {
                if (connection != null){
                    connection.disconnect();
                }
            }
        }catch (MalformedURLException ex){
            ex.printStackTrace();
            return ex.getMessage();
        }


        return json;
    }
}

