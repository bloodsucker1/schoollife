package com.example.work.protocol;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import com.example.work.bean.ProjectBean;
import com.example.work.utils.HttpUtil;
import com.example.work.utils.IOUtils;
import com.example.work.utils.StringUtils;
import com.example.work.utils.UIUtils;
import com.example.work.utils.URLData;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

/**基本的协议，提供数据访问
 * @author Administrator
 *获取数据--数据解析--设置缓存
 */
public abstract class BaseProtocol <T>{

	//解析后的数据集合
	private ArrayList<T> mdata = new ArrayList<T>();
	private String result = null;

	public ArrayList getData(int index){
		//先从缓存中获取数据
//		String result = getCache(index);
		if(StringUtils.isEmpty(result)){
			//从网络中获取数据
			result = getDataFromServer(index);
		}
		Log.e("BaseProtocol_result", ""+result);
		//判断返回数据是否为空
		if(result!=null){
			mdata = (ArrayList<T>) parsejson(result);
			//解析之后返回
			if(mdata!=null){
				Log.e("mdata",mdata.toString());
			}else{
				Log.e("mdata","mdata是空");
			}
			return mdata;
		}
		return null;
//		for(int i=0;i<=index;i++){
//			for(int j=0;j<10;j++){
//				ProjectBean bean = new ProjectBean("title"+i+j,"body"+i+j,new Random().nextInt());
//				mdata.add(bean);
//			}
//		}
//		return mdata;
	}

	/**从网络中获取数据
	 * @param index (index为服务器定义，依照服务器来写，指定访问第几页数据，实现分页加载)
	 * @return 
	 */
	private String getDataFromServer(final int index) {
		
		//链接=域名+接口字段+"?index=index"+参数
		Log.e("HttpHelper.URL+getkey()",""+URLData.GetURL+getkey()+"?index="+index+getParams());
		//HttpUtil.get(URLData.GetURL+getkey()+"?index="+index+getParams());
//		HttpUtil.get(setUrl());
		getDataFromUrl();
		
		Looper.prepare();
		HttpUtil.handler = new Handler(Looper.myLooper()){
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				result = (String) msg.obj;
				
				Log.e("getDataFromServer", ""+result);
				if(result!=null){//网络连接失败时会返回空，必须进行判断
					//缓存
					setCache(result, index);
					HttpUtil.handler.getLooper().quit();
				}
			}
		};
		Looper.loop();
		return result;
	}
	
	public abstract void getDataFromUrl();
	/**设置缓存
	 * @param index
	 */
	private void setCache(String json,int index){
		
		//获取系统缓存目录
		File FileDir = UIUtils.getContext().getCacheDir();
		//创建缓存文件
		File FileCache  = new File(FileDir, getkey()+"?index="+index+getParams());
		
		FileWriter writer = null;
		if(json!=null){//如果返回数据不为空
			try {
				writer = new FileWriter(FileCache);
				//设置缓存有效期为半个小时
				long deadTime = SystemClock.currentThreadTimeMillis()+30*60*1000;
				writer.write(deadTime+"\n");
				//写数据
				writer.write(json);
				writer.flush();//刷新数据
//			所谓flush是为了强制刷新。其实如果你写的东西多了，系统会自动刷新的。而且，close会自动把没有flush的东西写入的。
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				IOUtils.close(writer);
			}
		}
	}
	/**读缓存
	 * @param index
	 * @return
	 * @throws IOException 
	 */
	private String getCache(int index){
		//获取系统缓存目录
		File FileDir = UIUtils.getContext().getCacheDir();
		//创建缓存文件
		File FileCache  = new File(FileDir, getkey()+"?index="+index+getParams());
		
		if(FileCache.exists()){//判断是否存在
			//获取输出流
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(FileCache));
				
				String readLine = reader.readLine();
				long deadTime = Long.parseLong(readLine);
				//如果小于截止时间就返回
				if(SystemClock.currentThreadTimeMillis()<deadTime){
					
					StringBuffer buffer = new StringBuffer();
					String read = null;
					//判断是否督导文件末尾
					while((read=reader.readLine())!=null){
						buffer.append(read);
					}
					return buffer.toString();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				IOUtils.close(reader);//关闭流
			}
		}
		return null;
	}

	/**返回具体接口参数，必须由子类实现
	 * @return
	 */
	public abstract String getParams();

	/**返回具体接口字段，必须由子类实现
	 * @return
	 */
	public abstract String getkey();
	
	/**
	 * 解析json数据 ,每个页面要求的解析对象都不一样,必须由子类实现
	 * 
	 * @param result
	 */
	public abstract ArrayList<T> parsejson(String result);
}
