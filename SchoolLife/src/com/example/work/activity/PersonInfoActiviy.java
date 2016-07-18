package com.example.work.activity;

import com.example.work.R;
import com.example.work.fragment.PersonLoginFragment;
import com.example.work.fragment.PersonRegistFragment;
import com.example.work.fragment.UserLoginFragment;
import com.example.work.interfaces.CallBackInterface;
import com.example.work.utils.FragmentUtils;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class PersonInfoActiviy extends Activity{

	private static FragmentManager fm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_regeist);
		
		new FragmentUtils(PersonInfoActiviy.this).startFrgment(R.id.fg_login_regist,new PersonLoginFragment());
	}
}
