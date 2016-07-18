package com.example.work.interfaces;

import android.view.View;

/**
 * Created by Administrator on 2016/6/19.
 */
public interface UIoperator extends View.OnClickListener{
	/*
	  * 设置界面
	  */
	 public abstract int setLayoutId();
	 /*
	  * 初始化监听
	  */
	 public abstract void initListener();
	 /*
	  * 初始化数据
	  */
	 public abstract void initData();
	 /*
	  * 初始化界面
	  */
	 public abstract void initView();
	 /*
	  * 设置子类点击事件
	  */
	 public abstract void childonClick(View v);
}
