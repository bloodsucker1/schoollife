package com.example.work.activity;

import java.util.ArrayList;
import java.util.List;
import com.example.work.R;
import com.example.work.bean.appInfo;
import com.example.work.db.dao.WatchDogDao;
import com.example.work.manager.SoftManager;
import com.example.work.service.WatchDogService;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

public class GoodActivity extends Activity implements OnClickListener{

	private GridView lv_apps;
	private Context context;
	private CheckBox tv_app_check1;
	private List<appInfo> mData= new ArrayList<appInfo>();
	private List<appInfo> userTaskInfos;//用户程序列表
	private List<appInfo> systemTaskInfos;//系统程序列表
	private AppsAdapter adapter;
	private static final int TYPE_TITLE = 0;
	private static final int TYPE_ITEM = 1;
	private Dialog dialog;
	private TimePicker timePicker1;//设置时间
	private WatchDogDao Dao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_good);
		context = this;
		
		userTaskInfos=new ArrayList<appInfo>();
		systemTaskInfos=new ArrayList<appInfo>();
		lv_apps = (GridView) findViewById(R.id.lv_apps);
		
		Button btn_set_time = (Button) findViewById(R.id.btn_set_time);
		fillData();
		btn_set_time.setOnClickListener(this);
	}
	//填充数据
			private void fillData() {  
				new AsyncTask<String, Integer, String>() {
					
					protected void onPreExecute() {
						
					};

					@Override
					protected String doInBackground(String... params) {
						//获取所有应用的信息
						mData=SoftManager.getAllSoftInfos(context);
						
						userTaskInfos.clear();
						systemTaskInfos.clear();//清除集合中的数据
						//遍历应用程序列表
						for(appInfo info:mData){ 
							if(info.isUser()){ //判断是否为用户进程
								userTaskInfos.add(info);
							}else{
								systemTaskInfos.add(info);
							}
						}
						return null;
					}
					
					protected void onPostExecute(String result) {
						//ListView适配数据
						if(adapter==null){
							adapter=new AppsAdapter();
							lv_apps.setAdapter(adapter);
						}else{
							adapter.notifyDataSetChanged();//通知数据发生改变
						}
					};
				}.execute();
			}
	public class AppsAdapter extends BaseAdapter{

		private appInfo info;

		@Override
		public int getCount() {
			return mData.size();
		}

		@Override
		public Object getItem(int position) {
			return mData.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			if(position<userTaskInfos.size()){
				info = userTaskInfos.get(position);
			}else{
				info = systemTaskInfos.get(position-userTaskInfos.size());
			}
			if(convertView==null){
					convertView = View.inflate(context, R.layout.item_gride_lock,null);
			}
				ImageView iv_app_icon1 = (ImageView) convertView.findViewById(R.id.iv_app_icon1); 
				tv_app_check1 = (CheckBox) convertView.findViewById(R.id.cb_app_check1);
				TextView tv_app_name1 = (TextView) convertView.findViewById(R.id.tv_app_name1);
				
				iv_app_icon1.setImageDrawable(info.getIcon());
				tv_app_name1.setText(info.getName());
				tv_app_check1.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Dao = new WatchDogDao(context);
						if(tv_app_check1.isChecked()){
							Dao.addLockedApp(info.getPackageName());
						}else{
							Dao.removeLockedApp(info.getPackageName());
						}
					}
				});
				return convertView;
		}
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_set_time:
			
			dialog = new Dialog(context,R.style.customDialog);
			View view = View.inflate(context, R.layout.dialog_set_time, null);
			
			timePicker1 = (TimePicker) view.findViewById(R.id.timePicker1);
			Button btn_time_cancle = (Button) view.findViewById(R.id.btn_time_cancle);
			Button btn_time_sure = (Button) view.findViewById(R.id.btn_time_sure);
			btn_time_cancle.setOnClickListener(this);
			btn_time_sure.setOnClickListener(this);
			
			dialog.setContentView(view);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			break;
		case R.id.btn_time_cancle:
			dialog.dismiss();
			break;
		case R.id.btn_time_sure:
			Integer hour1 = timePicker1.getCurrentHour();
			Integer minute1 = timePicker1.getCurrentMinute();
			//获取系统时间
			Time t = new Time();
			t.setToNow();//取得当前系统时间
			int hour0 = t.hour;
			int minute0 = t.minute;
			//计算时间
			int hour = hour1-hour0;
			int minute = minute1 - minute0;
			
			//开启服务枷锁
			Log.e("time","hour:"+hour+"minute:"+minute);
			dialog.dismiss();
			Intent intent = new Intent(this,WatchDogService.class);
			startService(intent);
			break;	
		default:
			break;
		}
	}
}
