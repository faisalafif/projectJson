package com.faislll.projectjson;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.faislll.projectjson.ddd.Product;
import com.google.gson.Gson;

import java.util.ArrayList;

public class AdapterRv extends RecyclerView.Adapter<AdapterRv.MyViewHolder> {
    private Context context;
    private ArrayList<Product> products = new ArrayList<>();

//    public AdapterRv(Context context, ArrayList<Product> products) {
//        this.context = context;
//        this.products = products;
//    }

    public AdapterRv(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.detail_product, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.tvNama.setText(products.get(position).getProductNama());
//        holder.img.setImageResource(products.get(position).getProductImage());
        holder.tvMerchant.setText(products.get(position).getMerchants().getMerchantName());

        String baseUrl = "http://210.210.154:4444/api/products";
        String url = baseUrl+products.get(position).getProductImage();

        Glide.with(context).load(url).into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Deskripsi.class);
//                intent.putExtra("data", products.get(position));

                String json = new Gson().toJson(products.get(position));
                intent.putExtra("data", json);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tvNama, tvMerchant;
        private ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNama = itemView.findViewById(R.id.tv_list);
            img = itemView.findViewById(R.id.img_List);
            tvMerchant = itemView.findViewById(R.id.tv2_list);

        }
    }

    public void addData(ArrayList<Product> products){
        this.products = products;
    }
}
