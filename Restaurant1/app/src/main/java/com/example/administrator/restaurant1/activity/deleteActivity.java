package com.example.administrator.restaurant1.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.administrator.restaurant.R;
import com.example.administrator.restaurant.adapter.OrderAdapter;
import com.example.administrator.restaurant.bean.Food;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/7.
 */
public class deleteActivity extends Activity {

    private ArrayList<Food> order =new ArrayList<Food>();
    private OrderAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        Bundle bundle = getIntent().getExtras();
        order = bundle.getParcelableArrayList("order");

        GridView gv_delete =  (GridView) findViewById(R.id.gv_delete);
        adapter = new OrderAdapter(order,this);
        gv_delete.setAdapter(adapter);

        gv_delete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                order.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(deleteActivity.this,StartActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("order",order);
        intent.putExtras(bundle);
        startActivity(intent);

        super.onBackPressed();
        System.out.println("按下了back键   onBackPressed()");
    }
}
