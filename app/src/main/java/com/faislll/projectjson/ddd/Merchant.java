package com.faislll.projectjson.ddd;

import android.os.Parcel;
import android.os.Parcelable;

public class Merchant implements Parcelable {
    private int merchantId;
    private String merchantName;
    private String merchantSLug;

    public Merchant(int merchantId, String merchantName, String merchantSLug) {
        this.merchantId = merchantId;
        this.merchantName = merchantName;
        this.merchantSLug = merchantSLug;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getMerchantSLug() {
        return merchantSLug;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.merchantId);
        dest.writeString(this.merchantName);
        dest.writeString(this.merchantSLug);
    }

    protected Merchant(Parcel in) {
        this.merchantId = in.readInt();
        this.merchantName = in.readString();
        this.merchantSLug = in.readString();
    }

    public static final Parcelable.Creator<Merchant> CREATOR = new Parcelable.Creator<Merchant>() {
        @Override
        public Merchant createFromParcel(Parcel source) {
            return new Merchant(source);
        }

        @Override
        public Merchant[] newArray(int size) {
            return new Merchant[size];
        }
    };
}
