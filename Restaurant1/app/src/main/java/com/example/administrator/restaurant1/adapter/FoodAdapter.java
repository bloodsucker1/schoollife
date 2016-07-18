package com.example.administrator.restaurant1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.restaurant.R;
import com.example.administrator.restaurant.bean.Food;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/6.
 */
public class FoodAdapter extends BaseAdapter {

    public ArrayList<Food> foods= new ArrayList<Food>();

    private Context context;
    public FoodAdapter( ArrayList<Food> foods, Context context) {
        this.foods = foods;
        this.context = context;
    }

    @Override
    public int getCount() {
        return foods.size();
    }

    @Override
    public Object getItem(int position) {
        return foods.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = View.inflate(context, R.layout.item_food,null);
        }
        TextView tv_food_name = (TextView)convertView.findViewById(R.id.tv_food_name);
        TextView tv_food_price = (TextView)convertView.findViewById(R.id.tv_food_price);

        tv_food_name.setText(foods.get(position).getName());
        tv_food_price.setText(""+foods.get(position).getPrice());
        return convertView;
    }
}
