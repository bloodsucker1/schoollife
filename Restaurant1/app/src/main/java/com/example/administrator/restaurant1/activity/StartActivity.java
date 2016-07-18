package com.example.administrator.restaurant1.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.restaurant.R;
import com.example.administrator.restaurant.adapter.FoodAdapter;
import com.example.administrator.restaurant.adapter.TypeAdapter;
import com.example.administrator.restaurant.bean.Food;

import java.util.ArrayList;

public class StartActivity extends Activity implements View.OnClickListener{


    private String[] data_type = {"类型一", "类型二", "类型三", "类型四", "类型五", "类型六"};
    private String[] food_menu = {"油泼面", "西红柿鸡蛋", "剁椒面", "漉面", "烩面", "炒面", "刀削面", "凉面", "手擀面",
            "米饭", "快餐", "盖浇饭", "木桶饭", "炸鸡饭", "青椒肉丝", "麻辣鸡丝", "香辣土豆", "红烧肉", "凉拌米线", "凉拌黄瓜", "凉粉", "凉皮", "肉夹馍", "菜加馍", "鸡蛋加膜", "梅菜加馍", "面皮加馍", "孜然加馍", "火腿加馍"};
    private float[] food_price = {15, 10, 25, 15, 10, 15,20, 10, 25,
            2, 3, 4, 5, 3, 4, 5, 8, 4, 5, 4, 6, 1, 2, 3, 2, 15, 2, 3, 4};
    private static ArrayList<Food> order =new ArrayList<Food>();
    public ArrayList<Food> foods= new ArrayList<Food>();

    private float total = 0;
    private int Count = 0;

    private Context context;
    private Button btn_order;
    private Button btn_total;
    private Button btn_delete;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        context = this;

        for(int i= 0;i<food_menu.length;i++){
            Food food = new Food();
            food.setName(food_menu[i]);
            food.setPrice(food_price[i]);
            foods.add(food);
        }

        ListView lv_type = (ListView) findViewById(R.id.lv_type);
        lv_type.setAdapter(new TypeAdapter(data_type, this));

        final GridView gv_food = (GridView) findViewById(R.id.gv_menu);
        gv_food.setAdapter(new FoodAdapter(foods, this));

        btn_order = (Button) findViewById(R.id.btn_order);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_total = (Button)findViewById(R.id.btn_total);
        btn_delete.setOnClickListener(this);

        //设置返回数据
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            order = bundle.getParcelableArrayList("order");
            if(order==null){
                btn_total.setText("");
                btn_order.setText("");
                btn_order.invalidate();
                btn_total.invalidate();
            }else{
                for(int i=0;i<order.size();i++){
                    total+=order.get(i).getPrice();
                }
                btn_total.setText(""+total);
                btn_order.setText(""+order.size());
                btn_order.invalidate();
                btn_total.invalidate();
            }
        }

        lv_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        gv_food.setSelection(0);
                        break;
                    case 1:
                        gv_food.setSelection(8);
                        break;
                    case 2:
                        gv_food.setSelection(15);
                        break;
                }
            }
        });
        gv_food.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                total +=food_price[position];
                order.add(foods.get(position));

                Count++;
                btn_total.setText(""+total);
                btn_order.setText(""+Count);

                btn_order.invalidate();
                btn_total.invalidate();
            }
        });
    }

    @Override
    public void onClick(View v) {
        intent = new Intent();
        switch(v.getId()){
            case R.id.btn_delete:
                intent.setClass(StartActivity.this, deleteActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("order",order);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            break;
            case R.id.btn_BeSure:
                Toast.makeText(context,"提交订单成功",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
