package com.example.administrator.restaurant1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.restaurant.R;

/**
 * Created by Administrator on 2016/7/6.
 */
public class TypeAdapter extends BaseAdapter {

    private String[] data_type;
    private Context context;

    public TypeAdapter(String[] data_type,Context context) {
        this.data_type = data_type;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data_type.length;
    }

    @Override
    public Object getItem(int position) {
        return data_type[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = View.inflate(context,R.layout.item_type,null);
        }

        TextView tv_type = (TextView) convertView.findViewById(R.id.btn_type);
        tv_type.setText(data_type[position]);
        return convertView;
    }
}
