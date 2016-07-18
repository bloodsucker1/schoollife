package com.example.work.db.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.work.db.WatchDogOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;

/**黑名单访问数据库的工具类
 * 
 * @author Administrator
 *
 */
public class WatchDogDao {
	public static final String TABLE = "t_info";
	public static final String PACKAGENAME = "packagename";//包名

	private WatchDogOpenHelper helper;
	public WatchDogDao(Context context){
		helper=new WatchDogOpenHelper(context);
	}
	/**
	 * 添加 一个加锁的应用
	 * */

	public void addLockedApp(String packagename){
		SQLiteDatabase db=helper.getWritableDatabase();// 加了锁 ，比getReadableDatabase 要慢一些
		ContentValues values=new ContentValues();
		values.put(PACKAGENAME, packagename);
		long id = db.insert(TABLE, null, values);
		db.close();
	}
	
	//删除加锁的应用
	public void removeLockedApp(String packagename){
		SQLiteDatabase db=helper.getWritableDatabase();// 加了锁 ，比getReadableDatabase 要慢一些
		db.delete(TABLE, PACKAGENAME+"=?", new String[]{packagename});
		db.close();
	}
	
	
	//查询当前包是否加锁
	public boolean queryIsLocked(String packagename){
		boolean flag=false;
		SQLiteDatabase db=helper.getReadableDatabase();// getReadableDatabase没有加锁，速度比getWritableDatabase快一些
		Cursor c = db.query(TABLE,null, PACKAGENAME+"=?", new String[]{packagename}, null, null, null);
		if(c.moveToNext()){
			flag=true;//表示当前的包或者应用 已经加锁
		}
		c.close();
		db.close();
		return flag;
	}
	
	//查询所有的加锁的应用  ，返回的是包的列表
	
	public List<String> getAllLockedApp(){
		List<String> data=null;
		SQLiteDatabase db=helper.getReadableDatabase();
		Cursor c = db.query(TABLE, new String[]{PACKAGENAME}, null, null, null, null,null);
		if(c.getCount()>0){
//			c.moveToPosition(position); //把游标指定到目标位置
			data=new ArrayList<String>();
		}
		while(c.moveToNext()){
			String packagename=c.getString(0);
			data.add(packagename);
		}
		c.close();
		db.close();
		return data;
	}

}
