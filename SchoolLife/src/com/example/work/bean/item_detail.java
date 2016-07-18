package com.example.work.bean;

import android.widget.ImageView;
import android.widget.TextView;

public class item_detail {

	private String tv_name;
	private int iv_pic;
	public String getTv_name() {
		return tv_name;
	}
	public void setTv_name(String tv_name) {
		this.tv_name = tv_name;
	}
	public int getIv_pic() {
		return iv_pic;
	}
	public void setIv_pic(int iv_pic) {
		this.iv_pic = iv_pic;
	}
	public item_detail(String tv_name, int iv_pic) {
		super();
		this.tv_name = tv_name;
		this.iv_pic = iv_pic;
	}
	@Override
	public String toString() {
		return "item_detail [tv_name=" + tv_name + ", iv_pic=" + iv_pic + "]";
	}
	
}
