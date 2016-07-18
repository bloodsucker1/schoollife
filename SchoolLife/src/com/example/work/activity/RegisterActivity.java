package com.example.work.activity;

import com.example.work.R;
import com.example.work.bean.ProjectBean;
import com.example.work.utils.HttpUtil;
import com.example.work.utils.UIUtils;
import com.example.work.utils.URLData;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


@SuppressLint("HandlerLeak") public class RegisterActivity extends BaseActivity {

	private Context context;
	private EditText et_accout;
	private EditText et_password;
	private EditText et_email;
	private EditText et_phone;
	private Button btn_reg;
	private Button btn_cancle;
	private String result;

	@Override
	public int setLayoutId() {
		return R.layout.fragment_register;
	}

	@Override
	public void initListener() {
		btn_reg.setOnClickListener(this);
		btn_cancle.setOnClickListener(this);
	}

	@Override
	public void initData() {
	}

	@Override
	public void initView() {
		context = this;
		
		et_accout = (EditText) findViewById(R.id.accout);
		et_password = (EditText)findViewById(R.id.password);
		et_email = (EditText) findViewById(R.id.email);
		et_phone = (EditText) findViewById(R.id.phone);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_cancle = (Button) findViewById(R.id.btn_cancle);
	}

	@Override
	public void childonClick(View v) {
		switch (v.getId()) {
		case R.id.btn_reg:
			final String account = et_accout.getEditableText().toString();
			final String password = et_password.getEditableText().toString();
			final String email = et_email.getEditableText().toString();
			final String phone = et_phone.getEditableText().toString();
			
			HttpUtil.Post(account, password,email,phone,URLData.RegURL);
			
			HttpUtil.handler = new Handler(){
				@Override
				public void handleMessage(Message msg) {
					super.handleMessage(msg);
					if((Boolean) msg.obj){
						enterLogin();
					}
				}
			};
			
			break;
		case R.id.btn_cancle:
			enterLogin();
			break;
		default:
			break;
		}
	}
	//进入登录
	private void enterLogin() {
		Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
		startActivity(intent);
		finish();
	}
	//账号判空操作
	public static boolean CheckAccWithAcc(String account) {
		if(!account.isEmpty()){
			return true;
		}
		Toast.makeText(UIUtils.getContext(), "账号输入不能为空",Toast.LENGTH_SHORT).show();
		return false;
	}
	//密码判空操作
	public static boolean CheckAccWithPas(String password) {
		if(!password.isEmpty()){
			return true;
		}
		Toast.makeText(UIUtils.getContext(), "密码输入不能为空",Toast.LENGTH_SHORT).show();
		return false;
	}

	@Override
	public void setrequestWindowFeature() {
		// TODO Auto-generated method stub
		
	}
}
