package com.faislll.projectjson.ddd;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListProduct {

        @SerializedName("data")
        private ArrayList<Product> products;

        public ListProduct(ArrayList<Product> products) {
            this.products = products;
        }

        public ArrayList<Product> getProducts() {
            return products;
        }


}
