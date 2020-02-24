package com.faislll.projectjson.ddd;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Product implements Parcelable {
    private int productId;

    @SerializedName("productName")
    private String productNama;

    private String productSlug;

    private int productQty;

    private String productImage;

    @SerializedName("merchant")
    private Merchant merchants;

    @SerializedName("category")
    private ProductCategory productCategories;

    public Product(int productId, String productNama, String productSlug, int productQty, String productImage, Merchant merchants, ProductCategory productCategories) {
        this.productId = productId;
        this.productNama = productNama;
        this.productSlug = productSlug;
        this.productQty = productQty;
        this.productImage = productImage;
        this.merchants = merchants;
        this.productCategories = productCategories;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductNama() {
        return productNama;
    }

    public String getProductSlug() {
        return productSlug;
    }

    public int getProductQty() {
        return productQty;
    }

    public String getProductImage() {
        return productImage;
    }

    public Merchant getMerchants() {
        return merchants;
    }

    public ProductCategory getProductCategories() {
        return productCategories;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.productId);
        dest.writeString(this.productNama);
        dest.writeString(this.productSlug);
        dest.writeInt(this.productQty);
        dest.writeString(this.productImage);
        dest.writeParcelable(this.merchants, flags);
        dest.writeParcelable(this.productCategories, flags);
    }

    protected Product(Parcel in) {
        this.productId = in.readInt();
        this.productNama = in.readString();
        this.productSlug = in.readString();
        this.productQty = in.readInt();
        this.productImage = in.readString();
        this.merchants = in.readParcelable(Merchant.class.getClassLoader());
        this.productCategories = in.readParcelable(ProductCategory.class.getClassLoader());
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
