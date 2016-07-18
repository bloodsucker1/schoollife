package com.example.work.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

public class UIUtils {
	private static Context context= BaseApplication.getContext();

	// //////////BaseApplication相关方法///////////////////

	// 获取context
	public static Context getContext() {
		return BaseApplication.getContext();
	}

	// 获取handler
	public static Handler getHandler() {
		return BaseApplication.getHandler();
	}

	// 获取主线程id
	public static int getMainThreadId() {
		return BaseApplication.getMainThreadId();
	}

	// ///////////////加载资源文件///////////////////

	// 加载字符串
	public static String getString(int id) {
		return getContext().getResources().getString(id);
	}

	// 加载字符串数组
	public static String[] getStringArray(int id) {
		return getContext().getResources().getStringArray(id);
	}

	// 加载图片
	public static Drawable getDrawable(int id) {
		return getContext().getResources().getDrawable(id);
	}

	// 加载颜色
	public static int getColor(int id) {
		return getContext().getResources().getColor(id);
	}
	
	//加载颜色的状态选择器
	public static ColorStateList getColorStateList(int id) {
		return getContext().getResources().getColorStateList(id);
	}

	// 加载尺寸
	public static int getDimen(int id) {
		return getContext().getResources().getDimensionPixelSize(id);// 获取对应像素值
	}

	// ///////////////dip转px, px转dp///////////////////
	// dp = px / 设备密度
	public static int dip2px(float dip) {
		float density = getContext().getResources().getDisplayMetrics().density;// 设备密度
		int px = (int) (dip * density + 0.5f);// 3.1->3, 3.9+0.5->4.4->4
		return px;
	}

	public static float px2dip(int px) {
		float density = getContext().getResources().getDisplayMetrics().density;// 设备密度
		return px / density;
	}

	// ///////////////通过布局文件加载布局对象///////////////////
	public static View inflate(int layoutId) {
		return View.inflate(getContext(), layoutId, null);
	}

	// ///////////////判断当前是否在主线程运行///////////////////
	public static boolean isRunOnUiThread() {
		// 获取当前线程id, 如果当前线程id等于主线程id, 那就说明当前是在主线程
		return android.os.Process.myTid() == getMainThreadId();
	}

	// 运行在主线程
	public static void runOnUiThread(Runnable r) {
		// 判断当时是否是主线程,如果是,就直接运行
		if (isRunOnUiThread()) {
			// 当前就是主线程
			Log.e("runOnUiThread", "runOnUiThread");
			r.run();
		} else {
			// 不是主线程, 需要运行在主线程
			// handler处理发送Message之外,也可以发送一个Runnable对象,也是运行在主线程的
			Log.e("runOnUiThread", "getHandler");
			getHandler().post(r);
			
		}
	}
	//提示方法
	 public static void showtoast(Context context,String text){
	        Toast toast = Toast.makeText(context,text,Toast.LENGTH_SHORT);
	        toast.setGravity(Gravity.CENTER,0,0);
	        toast.show();
	 }
	//界面跳转
	public static void enter(Context activity,Class activity1) {
		Intent intent = new Intent(activity,activity1);
		context.startActivity(intent);
		((Activity) context).finish();
	}
	
	//更具图片资源获取id
	public static int getId(String str){
		Resources res=context.getResources();  
		int id=res.getIdentifier(str,"drawable", "com.example.work");
		Log.e("id", ""+id);
		return id;
	}
}
