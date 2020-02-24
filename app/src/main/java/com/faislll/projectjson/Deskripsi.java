package com.faislll.projectjson;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.faislll.projectjson.ddd.ListProduct;
import com.faislll.projectjson.ddd.Product;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Deskripsi extends AppCompatActivity {

    private TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_product);

        Bundle bundle = getIntent().getExtras();
        String json = bundle.getString("data");
        int position = bundle.getInt("posisi");

        ArrayList<Product> product = deserialisasiJSON(json);

//        Product product = getIntent().getParcelableExtra("data");
        inisialisasi();

        String baseUrl = "http://192.168.6.221:81/storage/";
        String url = baseUrl + product.get(position).getProductImage();

        Glide.with(this).load(url).into(img);

        tv1.setText("ID Produk      :  "+ String.valueOf(product.get(position).getProductId()));
        tv2.setText("Nama Produk    :  "+ product.get(position).getProductNama());
        tv3.setText("Slug Produk    :  "+ product.get(position).getProductNama());
        tv4.setText("Qty Produk     :  "+ String.valueOf(product.get(position).getProductId()));
        tv5.setText("Nama Merchant  :  "+ product.get(position).getMerchants().getMerchantName());
        tv6.setText("Slug Merchant  :  "+ product.get(position).getMerchants().getMerchantSLug());
        tv7.setText("Nama Kategori  :  "+ product.get(position).getProductCategories().getCategoryName());

    }

    public void inisialisasi(){
        img = findViewById(R.id.img_List);
        tv1 = findViewById(R.id.tv_des_id);
        tv2 = findViewById(R.id.tv_des_name);
        tv3 = findViewById(R.id.tv_des_slug);
        tv4 = findViewById(R.id.tv_des_qty);
        tv5 = findViewById(R.id.tv_mrc_name);
        tv6 = findViewById(R.id.tv_mrc_slug);
        tv7 = findViewById(R.id.tv_ctgry);

    }

    public ArrayList<Product> deserialisasiJSON(String jsonParam) {
        ArrayList<Product> data = new ArrayList<>();
        try {
            JSONObject jsonAwal = new JSONObject(jsonParam);

            Gson gson = new Gson();
            ListProduct listProducts = gson.fromJson(jsonAwal.toString(), ListProduct.class);
            data.addAll(listProducts.getProducts());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

}
