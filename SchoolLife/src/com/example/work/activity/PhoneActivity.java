package com.example.work.activity;

import com.example.work.R;

import android.view.View;
import android.widget.TextView;

public class PhoneActivity extends BaseActivity {

	private TextView tv_number;
	private String number;

	@Override
	public int setLayoutId() {
		return R.layout.activity_number;
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initData() {
		tv_number.setText(number);
	}

	@Override
	public void initView() {
		tv_number = (TextView) findViewById(R.id.tv_number);
		number = getIntent().getStringExtra("number");
	}

	@Override
	public void childonClick(View v) {
	}

	@Override
	public void setrequestWindowFeature() {
		// TODO Auto-generated method stub
		
	}

}
