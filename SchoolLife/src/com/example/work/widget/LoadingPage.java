package com.example.work.widget;

import com.example.work.R;
import com.example.work.manager.ThreadPoolManager;
import com.example.work.utils.UIUtils;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

/*根据当前界面状态来加载布局
 * @author Administrator
 */
public abstract class LoadingPage extends FrameLayout{

	private static final int STATE_LOAD_UNDO = 0;//未加载
	private static final int STATE_LOAD_LOADING = 1;//正在加载
	private static final int STATE_LOAD_EMPTY = 2;//加载为空
	private static final int STATE_LOAD_Error = 3;//加载为空
	private static final int STATE_LOAD_SUCCESS = 4;//加载成功
	
	private int myCurrentState = STATE_LOAD_UNDO;
	private static int i=0;
	
	private View MyLoadingView;
	private View MyEmptyView;
	private View MyErrorView;
	private View MySuccessView;
	
	public LoadingPage(Context context) {
		super(context);
		initView();
	}
	/**初始化布局
	 */
	private void initView(){
		//加载中的布局
		if(MyLoadingView==null){
			MyLoadingView = onCreateLoadingView();
			//将加载的布局添加到当前的帧布局中
			addView(MyLoadingView);
		}
		//数据为空的布局
		if(MyEmptyView==null){
			MyEmptyView = onCreateEmptyView();
			//将加载的布局添加到当前的帧布局中
			addView(MyEmptyView);
		}
		//数据加载失败的布局
		if(MyErrorView==null){
			MyErrorView = onCreateErrorView();
			//将加载的布局添加到当前的帧布局中
			addView(MyErrorView);
		}
		showRightPage();
	}
	/**
	 * 显示界面
	 */
	private void showRightPage(){
		if(MyLoadingView!=null){
			MyLoadingView.setVisibility((myCurrentState==STATE_LOAD_UNDO||myCurrentState==STATE_LOAD_LOADING)?View.VISIBLE:View.GONE);
		}
		if(MyEmptyView!=null){
			MyEmptyView.setVisibility((myCurrentState==STATE_LOAD_EMPTY)?View.VISIBLE:View.GONE);
		}
		if(MyErrorView!=null){
			MyErrorView.setVisibility((myCurrentState==STATE_LOAD_Error)?View.VISIBLE:View.GONE);
		}
		if(MySuccessView==null&&myCurrentState==STATE_LOAD_SUCCESS){
			MySuccessView=onCreateSuccessView();
			//防止返回位为空
			if(MySuccessView!=null){
				addView(MySuccessView);
			}
		}
		if(MySuccessView!=null){
			MySuccessView.setVisibility((myCurrentState==STATE_LOAD_SUCCESS)?View.VISIBLE:View.GONE);
		}
	}
	/**初始化加载中的布局
	 * @return
	 */
	public View onCreateLoadingView(){
		return UIUtils.inflate(R.layout.layout_loading);
	}
	public View onCreateEmptyView(){
		return UIUtils.inflate(R.layout.layout_empty);
	}
	private View onCreateErrorView() {
		View view = UIUtils.inflate(R.layout.layout_error);
		Button btn_Retry = (Button) view.findViewById(R.id.btn_retry);
		btn_Retry.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				LoadData();
			}
		});
		return view;
	}
	public abstract View onCreateSuccessView();
	
	
	public void LoadData(){
		//首先要状态清零
		if(myCurrentState==STATE_LOAD_EMPTY||myCurrentState== STATE_LOAD_Error||myCurrentState==STATE_LOAD_SUCCESS){
			myCurrentState =STATE_LOAD_UNDO;
		}
		if(myCurrentState == STATE_LOAD_UNDO){
			//利用线程池加载数据
			ThreadPoolManager.getThreadPool().execute(new Runnable() {
				
				@Override
				public void run() {
					final Result result=onLoad();
					Log.e("myCurrentState", ""+result);
					UIUtils.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							if(result!=null){
								myCurrentState = result.getSate();
								showRightPage();
							}
						}
					});
				}
			});
		}
	}
	
	/**
	 * 加载网络数据必须由子类实现
	 */
	public abstract Result onLoad();
	
	public enum Result{
		LOAD_SUCCESS(STATE_LOAD_SUCCESS),LOAD_ERROR(STATE_LOAD_Error),LOAD_EMPTY(STATE_LOAD_EMPTY);
		
		private int state;
		private Result(int state){
			this.state=state;
		}
		public int getSate(){
			return state;
		}
	}
}
