package com.example.work.fragment;

import java.util.ArrayList;

import com.example.work.utils.UIUtils;
import com.example.work.widget.LoadingPage;
import com.example.work.widget.LoadingPage.Result;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class LoadBaseFragment extends Fragment {
	
//	初始化fragment布局
	private LoadingPage mLoadingPage;
	
	@Override
		public View onCreateView(LayoutInflater inflater,
				@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mLoadingPage = new LoadingPage(UIUtils.getContext()){
			
			@Override
			public View onCreateSuccessView() {
				Log.e("LoadBaseFragment", "LoadBaseFragment");
				return LoadBaseFragment.this.onCreateSuccessView();
			}

			@Override
			public Result onLoad() {
				Log.e("LoadBaseFragment", "onLoad");
				return LoadBaseFragment.this.onLoad();
			}

			@Override
			public View onCreateEmptyView() {
				return LoadBaseFragment.this.onCreateEmptyView();
			}
			
		};
		return mLoadingPage;
		}
	
	/**
	 *加载数据
	 */
	public void loadData(){
		
		if(mLoadingPage!=null){
			Log.e("mLoadingPage2", ""+mLoadingPage);
				mLoadingPage.LoadData();
		}
	}
	//加载成功返回页面
	public abstract View onCreateSuccessView();
	//加载数据逻辑
	public abstract Result onLoad();
	
	public abstract View onCreateEmptyView();
	
	/**数据校验
	 * @param data1
	 * @return
	 */
	public Result check(ArrayList data1){
		if(data1!=null){
			if(data1 instanceof ArrayList){
				if(!data1.isEmpty()){
					return Result.LOAD_SUCCESS;
				}else{
					return Result.LOAD_EMPTY;
				}
			}
		}
		return Result.LOAD_ERROR;
	}
}
