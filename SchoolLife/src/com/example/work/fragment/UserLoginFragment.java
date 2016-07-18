package com.example.work.fragment;

import com.example.work.R;
import com.example.work.activity.PersonInfoActiviy;
import com.example.work.utils.FragmentUtils;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class UserLoginFragment extends Fragment implements OnClickListener{
	
	private RelativeLayout phone_quick_regest;
	private EditText et_phone_num;
	private EditText et_phone_co_password;
	private EditText et_phone_password;
	private TextView tv_phone;
	private TextView right_image_btn;
	private Button btn_phone;
	private TextView froget_pass;
	private ImageView left_image_btn;
	private TextView zhuce;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.user_login_regeist, null);
		

		right_image_btn = (TextView) view.findViewById(R.id.right_image_btn);
		left_image_btn = (ImageView) view.findViewById(R.id.left_image_btn);
		
		phone_quick_regest = (RelativeLayout) view.findViewById(R.id.phone_quick_regest);
		tv_phone = (TextView) view.findViewById(R.id.tv_phone);
		froget_pass = (TextView) view.findViewById(R.id.froget_pass);
		et_phone_num = (EditText) view.findViewById(R.id.et_phone_num);
		et_phone_co_password = (EditText) view.findViewById(R.id.et_phone_co_password);
		et_phone_password = (EditText) view.findViewById(R.id.et_phone_password);

		btn_phone = (Button)view.findViewById(R.id.btn_phone);
		zhuce = (TextView) view.findViewById(R.id.zhuce);
		
		left_image_btn.setOnClickListener(this);
		btn_phone.setOnClickListener(this);
		zhuce.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new FragmentUtils(getActivity()).startFrgment(R.id.fg_login_regist,new UserRegistFragment());
			}
		});
		right_image_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new FragmentUtils(getActivity()).startFrgment(R.id.fg_login_regist,new PersonLoginFragment());
			}
		});
		
		return view;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.zhuce:
//			((PersonInfoActiviy) getActivity()).startFrgment(new UserRegistFragment());
			break;
		case R.id.right_image_btn:
//			((PersonInfoActiviy) getActivity()).startFrgment(new PersonLoginFragment());
			break;
		case R.id.btn_phone:
			String phone = et_phone_num.getText().toString();
			
			String p_pass = et_phone_password.getText().toString();
			
			if(et_phone_co_password.getVisibility()==View.VISIBLE){
				String co_password = et_phone_co_password.getText().toString();
				//验证
				boolean isOkey = ChickPassword(co_password,p_pass);
				
				if(isOkey){
					//注册并跳转
				}
			}
			Log.e("btn_phone", "phone:"+phone+"p_pass:"+p_pass);
			break;
		case R.id.left_image_btn:
			break;
		default:
			break;
		}
	}

	//对密码进行验证
	private boolean ChickPassword(String co_password, String p_pass) {
		String text = "";
		if(co_password.isEmpty()){
			text = "验证密码不能为空";
		}else if(!co_password.equals(p_pass)){
			text = "两次密码不一致";
		}else if(co_password.equals(p_pass)){
			Log.e("ds", "注册成功");
			return true;
		}
		Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
		return false;
	}
}
