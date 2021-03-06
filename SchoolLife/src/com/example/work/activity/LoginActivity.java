package com.example.work.activity;

import com.example.work.R;
import com.example.work.bean.ProjectBean;
import com.example.work.utils.HttpUtil;
import com.example.work.utils.UIUtils;
import com.example.work.utils.URLData;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends BaseActivity {

	private EditText et_accout;
	private EditText et_password;
	private Button btn_login;
	private Button btn_reg;
	private Context context;

	
	@Override
	public int setLayoutId() {
		return R.layout.fragment_login;
	}

	@Override
	public void initListener() {
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
	}

	@Override
	public void initData() {
	}

	@Override
	public void initView() {
			
		context = this;
		
		et_accout = (EditText) findViewById(R.id.accout);
		et_password = (EditText)findViewById(R.id.password);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
	}

	@SuppressLint("HandlerLeak") @Override
	public void childonClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login:
			final String account = et_accout.getEditableText().toString();
			final String password = et_password.getEditableText().toString();
			
//			HttpUtil.Post(account, password,"","", URLData.LoginURL);
//			
//			HttpUtil.handler = new Handler(){
//				@Override
//				public void handleMessage(Message msg) {
//					super.handleMessage(msg);
//					if((Boolean) msg.obj){
//						ProjectBean.setUser(account);
//						enterMain();
//					}
//				}
//			};
			enterMain();
			break;
		case R.id.btn_reg:
			enterReg();
			break;
		default:
			break;
		}
		
	}
//进入主界面
	private void enterMain() {
		
		Intent intent = new Intent(context,HomeActivity.class);
		startActivity(intent);
		finish();
	}
// 进入注册界面
	private void enterReg() {
		Intent intent = new Intent(context,RegisterActivity.class);
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
