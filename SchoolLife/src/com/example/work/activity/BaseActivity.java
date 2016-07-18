package com.example.work.activity;

import com.example.work.interfaces.UIoperator;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public abstract class BaseActivity extends FragmentActivity implements UIoperator{
	
	  
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setrequestWindowFeature();
	        setContentView(setLayoutId());
	        initView();
	        initListener();
	        initData();
	    }
	
	public abstract void setrequestWindowFeature();

	@Override
	 public void onClick(View v) {
		childonClick(v);
	}
}
