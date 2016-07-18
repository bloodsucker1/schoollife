package com.example.work.utils;

import com.example.work.R;
import com.example.work.activity.PersonInfoActiviy;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;

public class FragmentUtils extends Fragment {
	
	private Context context;
	public FragmentUtils(Context context) {
		this.context = context;
	}

	//跳转fragment
	public void startFrgment(int Id,Fragment fragment){
		
		FragmentManager fm = ((Activity) context).getFragmentManager(); 
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.replace(Id, fragment);
		transaction.commit();
	}
}
