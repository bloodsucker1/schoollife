package com.example.work.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import com.example.work.activity.WatchDogActivity;
import com.example.work.db.dao.WatchDogDao;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Service;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
/**软件锁服务、看门狗服务
 * 
 * @author Administrator
 *
 */
@SuppressLint("NewApi") public class WatchDogService extends Service {
	private boolean flag=true; //软件锁服务开启或者关闭的状态
	private WatchDogDao watchDogDao;
	private String unlockPackagename="";//由WatchDogActivity回传的已经临时解锁的包名
	private int unlockTaskId;//临时解锁的任务栈的id
	
	private Context context;
	private BroadcastReceiver unLockReceiver =new BroadcastReceiver() {
		
		

		@Override
		public void onReceive(Context context, Intent intent) {
			unlockPackagename = intent.getStringExtra("packagename");
			unlockTaskId = intent.getIntExtra("taskId", 0);
			
		}
	};

	private ActivityManager am;
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	@Override
	public void onCreate() {
		super.onCreate();
		
		context = this;
		
		am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		watchDogDao=new WatchDogDao(this);
		
		//动作注册广播接收器，接受由 WatchDogActivity回传的包信息（该包已经实现了临时解锁）
		IntentFilter filter=new IntentFilter();
		filter.addAction("cn.itcast.phonesafe.unlock");
		registerReceiver(unLockReceiver, filter);
		new Thread(){
			public void run() {
				while(flag){
//					List<RunningTaskInfo> runningTasks = am.getRunningTasks(1);//最多取得多少个最近运行的任务栈
//					// Activity 存放在任务栈中 ，栈底  ，栈顶 
//					for(RunningTaskInfo temp:runningTasks){
////						temp.baseActivity;//处于栈底的Activity
////						temp.topActivity;//处于栈顶的Activity
//						//显示当前任务栈的包名
//						String packageName = temp.baseActivity.getPackageName();
//						Log.e("log","当前的包名"+packageName);
//						int taskId=temp.id;//当前任务栈的id
//						//比较包名：假如对该包已经加锁，则打开看门狗界面
//						boolean isLocked = watchDogDao.queryIsLocked(packageName);
//						if(isLocked){
//							if(!(packageName.equals(unlockPackagename)&&taskId==unlockTaskId)){
//								//跳转到看门狗界面  
//								Intent intent=new Intent();
//								intent.setClass(WatchDogService.this, WatchDogActivity.class);
//								//向 调用的Activity： WatchDogActivity传递 packagename 附加参数
//								intent.putExtra("packagename", packageName);
//								intent.putExtra("taskId", taskId);
//								// 假如当前的上下文不是Activity的上下文，而要跳转到一个Activity中，则设置FLAG_ACTIVITY_NEW_TASK的标志
//								intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//								startActivity(intent);
//							}
//						}
						
//					}
					
					try {
						Log.e("sad", getProcess()+"");
					} catch (Exception e) {
						e.printStackTrace();
					}
					//休眠一下 ,休眠必须在子线程实现 
					SystemClock.sleep(1000);
					}
				
			};
			
		}.start();
		
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		flag=false;//当服务停止，则软件锁监听功能解除
		//当服务停止，注销广播接收器
		unregisterReceiver(unLockReceiver);
		super.onDestroy();
	}

	private String getProcess() throws Exception {
	    if (Build.VERSION.SDK_INT >= 21) {
	        return getProcessNew();
	    } else {
	        return getProcessOld();
	    }
	}

	//API 21 and above
	private String getProcessNew() throws Exception {
		final int PROCESS_STATE_TOP = 2;
		ActivityManager.RunningAppProcessInfo currentInfo = null;
		Field field = null;
		try {
		    field = ActivityManager.RunningAppProcessInfo.class.getDeclaredField("processState");
		} catch (Exception ignored) {
		}
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningAppProcessInfo> appList = am.getRunningAppProcesses();
		for (ActivityManager.RunningAppProcessInfo app : appList) {
		    if (app.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND 
		            && app.importanceReasonCode == ActivityManager.RunningAppProcessInfo.REASON_UNKNOWN) {
		        Integer state = null;
		        try {
		            state = field.getInt(app);
		        } catch (Exception e) {
		        }
		        if (state != null && state == PROCESS_STATE_TOP) {
		            currentInfo = app;
		            break;
		        }
		    }
		}
		return currentInfo.processName;
	}

	//API below 21
	@SuppressWarnings("deprecation")
	private String getProcessOld() throws Exception {
	    String topPackageName = null;
	    ActivityManager activity = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
	    List<RunningTaskInfo> runningTask = activity.getRunningTasks(1);
	    if (runningTask != null) {
	        RunningTaskInfo taskTop = runningTask.get(0);
	        ComponentName componentTop = taskTop.topActivity;
	        topPackageName = componentTop.getPackageName();
	    }
	    return topPackageName;
	}
}
