package com.example.administrator.restaurant1.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.restaurant.R;

public class WaiterActivity extends Activity implements View.OnClickListener{

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(salayout.activity_waiter);

        findViewById(R.id.btn_History).setOnClickListener(this);
        findViewById(R.id.btn_Setting).setOnClickListener(this);
        findViewById(R.id.btn_NoPay).setOnClickListener(this);
        findViewByvedInstanceState);
        setContentView(R.Id(R.id.btn_Start).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        intent = new Intent();
        switch (v.getId()){
            case R.id.btn_Setting:
                enter(this,SettingActivity.class);
                break;
            case R.id.btn_History:
                enter(this,HistoryActivity.class);
                break;
            case R.id.btn_NoPay:
                enter(this,NoPayActivity.class);
                break;
            case R.id.btn_Start:
                enter(this,StartActivity.class);
                break;
            default:
                break;
        }
    }
    private void enter(Activity activity1, Class activity2) {
        intent.setClass(activity1,activity2);
        startActivity(intent);
        finish();
    }
}
