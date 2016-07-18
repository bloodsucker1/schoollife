package com.example.work.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class WatchDogOpenHelper extends SQLiteOpenHelper {
	private static final  String  DATABASE_NAME="watchdog.db";//数据库名 
	private static final  int  VERSION=1;//版本

	public WatchDogOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
	}
   // 初始化数据库的时候回调该方法
	@Override
	public void onCreate(SQLiteDatabase db) {
		//创建表
		db.execSQL("create table t_info(id integer primary key autoincrement,packagename text)");

	}
   //版本更新
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		System.out.println("onUpgrade");

	}

}
