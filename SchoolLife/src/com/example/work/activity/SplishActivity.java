package com.example.work.activity;

import com.example.work.R;
import com.example.work.utils.IOUtils;
import com.example.work.utils.LogUtils;
import com.example.work.utils.UIUtils;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;

public class SplishActivity extends BaseActivity{

	private Handler handler;

	@Override
	public int setLayoutId() {
		return R.layout.activity_splash;
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initData() {
		String Path =getFilesDir().getAbsolutePath()+"/soft.json";
		String assetsPath = "soft.json";
		Log.e("as","CopyAssetsToFiles");
		IOUtils.CopyAssetsToFiles(Path, assetsPath, this);
	}

	@Override
	public void initView() {
		handler = new Handler();
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Intent intent = new Intent(SplishActivity.this,LoginActivity.class);
				startActivity(intent);
				finish();
			}
		}, 2000);
	}

	@Override
	public void childonClick(View v) {
		
	}

	@Override
	public void setrequestWindowFeature() {
		// TODO Auto-generated method stub
		
	}


}
