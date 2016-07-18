package com.example.work.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import com.example.work.R;
import com.example.work.fragment.LoadBaseFragment;
import com.example.work.fragment.PostMessageFragment;
import com.example.work.fragment.ProjectFragment;

public class ProjectActivity extends FragmentActivity {
	
	public static FragmentTransaction transition;
	public static String username;
	public FragmentManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project);
		
		//Fragment ProjectFragment = new PostMessageFragment();
		LoadBaseFragment fragment = new ProjectFragment();
		
		
		manager = getSupportFragmentManager();
		transition = manager.beginTransaction();
		transition.replace(R.id.fg_project, fragment);
		transition.commit();
		
	}
	public void onClick(View v){
		switch (v.getId()) {
		case R.id.PostMessage:
			Log.e("sdfs", "sgfs");
			Fragment fragment = new PostMessageFragment();
			transition = manager.beginTransaction();
			transition.replace(R.id.fg_project, fragment);
			transition.addToBackStack(null);
			transition.commit();
			findViewById(R.id.PostMessage).setVisibility(View.GONE);
			break;
		default:
			break;
		}
	}
}
