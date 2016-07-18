package com.example.administrator.restaurant1.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/7/7.
 */
public class Food implements Parcelable {

    public String name;
    public float price;

    protected Food(Parcel in) {
        name = in.readString();
        price = in.readFloat();
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Food(String name, float price) {
        this.name = name;
        this.price = price;
    }
    public Food() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeFloat(price);
    }
    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            Food food = new Food();
            food.name = in.readString();
            food.price = in.readFloat();
            return food;
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };
}
