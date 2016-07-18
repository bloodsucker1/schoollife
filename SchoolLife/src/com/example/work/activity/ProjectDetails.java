package com.example.work.activity;

import com.example.work.R;
import com.example.work.bean.ProjectBean;
import com.example.work.fragment.DetailFragment;
import com.example.work.fragment.ProjectFragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ProjectDetails extends FragmentActivity {

	public FragmentManager manager;
	public static FragmentTransaction transition;
	private Dialog dialog;
	public ProjectBean pb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
		manager = getSupportFragmentManager();
		transition = manager.beginTransaction();
		
		pb = (ProjectBean) getIntent().getSerializableExtra("ProjectBean");
		Log.e("ProjectDetails_onCreate",""+pb.toString());
		//Fragment ProjectFragment = new PostMessageFragment();
		DetailFragment fragment = new DetailFragment();
		
		transition.replace(R.id.fg_project_details, fragment);
		transition.commit();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_add:
			dialog = new Dialog(this);
			View dialogView = View.inflate(this, R.layout.dialog, null);
			dialog.setContentView(dialogView);
			dialog.setCanceledOnTouchOutside(true);
			dialog.show();
			break;
		case R.id.btn_cancle:
			dialog.dismiss();
			break;
		case R.id.btn_sure:
			Intent intent = new Intent(this,PhoneActivity.class);
			intent.putExtra("number", pb.QQ);
			startActivity(intent);
			finish();
			break;
		default:
			break;
		}
	}

}
