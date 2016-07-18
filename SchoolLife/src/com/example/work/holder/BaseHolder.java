package com.example.work.holder;

import android.view.View;

public abstract class BaseHolder<T> {

	public View myRootView;
	private T data;
	
	// 获取数据
	public T getData() {
		return data;
	}

	/**
	 * 设置数据
	 */
	public void setData(T data) {
		this.data = data;
		//一旦有数据立刻刷新
		refreshView(data);
	}
	
	// 获取item的布局对象
	public View getMyRootView() {
		return myRootView;
	}
	
	public void setMyRootView(View myRootView) {
		this.myRootView = myRootView;
	}

	public BaseHolder() {
		myRootView = initView();
		// 3. 设置tag
		myRootView.setTag(this);
	}


	/**
	 * 1. 初始化item的布局  2. findViewById
	 */
	public abstract View initView();
	
	/**
	 * 4. 刷新控件数据
	 * 
	 * @param data
	 */
	public abstract void refreshView(T data);

}