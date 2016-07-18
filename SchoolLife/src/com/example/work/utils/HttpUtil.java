package com.example.work.utils;

import com.example.work.bean.ProjectBean;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class HttpUtil {
	
	private static Context context= BaseApplication.getContext();
	public static Handler handler;
	
	//提交注册数据
		public static void Post(String account,String password,String email,String phone,String uri){

			RequestParams params = new RequestParams();
			params.addBodyParameter("username", account);
			params.addBodyParameter("password", password);
			params.addBodyParameter("email", email);
			params.addBodyParameter("phone", phone);
			
			HttpUtils http = new HttpUtils();
			http.configCurrentHttpCacheExpiry(0);
			http.send(HttpRequest.HttpMethod.POST, uri, params,new RequestCallBack<String>() {

				@Override
				public void onStart() {
					Log.e("start", "conn..");
				}
				@Override
				public void onFailure(HttpException arg0, String arg1) {
					Log.e(arg0.getExceptionCode()+"",arg1);
				}

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
						Log.e("POt", arg0.result);
						//检查返回结果
						boolean isok = CheckResult(arg0.result);
						//发送消息
					 	Message msg = Message.obtain();
		               	msg.obj = isok;
		                handler.sendMessage(msg);
				}
			});
		}

		public static boolean CheckResult(String result) {
			
			if(result.equals("1")){
				Toast.makeText(context, "登录成功", 0).show();
				return true;
			}else if(result.equals("0")){
				Toast.makeText(context, "登录失败", 0).show();
				return false;
			}else if(result.equals("-1")){
				Toast.makeText(context, "密码错误", 0).show();
				return false;
			}else if(result.equals("2")){
				Toast.makeText(context, "未进行邮箱激活", 0).show();
				return false;
			}
			return false;
		}

		//提交发布数据
			public static void Post(ProjectBean project,String uri){

				RequestParams params = new RequestParams();
				params.addBodyParameter("ideatitle", project.message_title);
				params.addBodyParameter("ideadescribtion", project.message_body);
				params.addBodyParameter("qqid", project.QQ);
				params.addBodyParameter("userid", project.user);
				params.addBodyParameter("pdemand", project.message_request);
				
				HttpUtils http = new HttpUtils();
				http.send(HttpRequest.HttpMethod.POST, uri, params,new RequestCallBack<String>() {

					@Override
					public void onStart() {
						Log.e("start", "conn..");
					}
					@Override
					public void onFailure(HttpException arg0, String arg1) {
						Log.e(arg0.getExceptionCode()+"",arg1);
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						Log.e("POt", arg0.result);
						
						boolean isok = CheckResult(arg0.result);
						//发送消息
					 	Message msg = Message.obtain();
		               	msg.obj = isok;
		                handler.sendMessage(msg);
					}
				});
			}
			
				 /**
			     * 检查当前网络是否可用
			     * 
			     * @param context
			     * @return
			     */
			    
			    public static boolean isNetworkAvailable(Activity activity)
			    {
			        Context context = activity.getApplicationContext();
			        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
			        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			        
			        if (connectivityManager == null)
			        {
			            return false;
			        }
			        else
			        {
			            // 获取NetworkInfo对象
			            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
			            
			            if (networkInfo != null && networkInfo.length > 0)
			            {
			                for (int i = 0; i < networkInfo.length; i++)
			                {
			                    System.out.println(i + "===状态===" + networkInfo[i].getState());
			                    System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
			                    // 判断当前网络状态是否为连接状态
			                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)
			                    {
			                        return true;
			                    }
			                }
			            }
			        }
			        return false;
			   }
			    //获取数据
			    public static void get(String Url){
			    	
			    	HttpUtils http = new HttpUtils();
			    	http.configCurrentHttpCacheExpiry(0);
			    	http.send(HttpRequest.HttpMethod.GET, Url, new RequestCallBack<String>() {

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							
						}

						@Override
						public void onSuccess(ResponseInfo<String> arg0) {
							Log.e("get onSuccess", ""+arg0.toString());
							Message msg = Message.obtain();
			               	msg.obj = arg0.result;
			                handler.sendMessage(msg);
						}
					});
			    }
			    //获取数据
			    public static void get(String Url,String i_id){
			    	
			    	HttpUtils http = new HttpUtils();
			    	http.configCurrentHttpCacheExpiry(0);
			    	RequestParams params = new RequestParams();
					params.addQueryStringParameter("i_id", i_id);
			    	
			    	http.send(HttpRequest.HttpMethod.GET, Url,params, new RequestCallBack<String>() {

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							
						}

						@Override
						public void onSuccess(ResponseInfo<String> arg0) {
							Log.e("get onSuccess", ""+arg0.toString());
							Message msg = Message.obtain();
			               	msg.obj = arg0.result;
			                handler.sendMessage(msg);
						}
					});
			    }
}
