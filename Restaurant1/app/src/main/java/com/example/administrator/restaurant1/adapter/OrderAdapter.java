package com.example.administrator.restaurant1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.restaurant1.R;
import com.example.administrator.restaurant1.bean.Food;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/7.
 */
public class OrderAdapter extends BaseAdapter {

    private ArrayList<Food> order=new ArrayList<Food>();
    private Context context;

    public OrderAdapter(ArrayList<Food> order, Context context) {
        this.order = order;
        this.context = context;
    }

    @Override
    public int getCount() {
        return order.size();
    }

    @Override
    public Object getItem(int position) {
        return order.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = View.inflate(context, R.layout.item_food,null);
        }
        TextView tv_food_name = (TextView)convertView.findViewById(R.id.tv_food_name);
        TextView tv_food_price = (TextView)convertView.findViewById(R.id.tv_food_price);

        tv_food_name.setText(order.get(position).getName());
        tv_food_price.setText(""+order.get(position).getPrice());
        return convertView;
    }
}
