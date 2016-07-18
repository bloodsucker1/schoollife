package com.example.work.fragment;

import com.example.work.R;
import com.example.work.activity.PersonInfoActiviy;
import com.example.work.interfaces.CallBackInterface;
import com.example.work.utils.FragmentUtils;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class PersonLoginFragment extends Fragment{

	private TextView right_image_btn;
	private ImageView left_image_btn;
	
	private RelativeLayout phone_quick_regest;
	private EditText et_phone_num;
	private EditText et_phone_verify;
	private EditText et_phone_password;
	private TextView tv_phone;
	private Button btn_phone;
	private String p_pass;
	private TextView zhuce;
	private Button btn_get_verify;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.login_regeist, null);
		
		right_image_btn = (TextView) view.findViewById(R.id.right_image_btn);
		left_image_btn = (ImageView) view.findViewById(R.id.left_image_btn);
		
		et_phone_num = (EditText) view.findViewById(R.id.et_phone_num);
		et_phone_verify = (EditText) view.findViewById(R.id.et_phone_verify);
		et_phone_password = (EditText) view.findViewById(R.id.et_phone_password);
		btn_get_verify = (Button) view.findViewById(R.id.btn_get_verify);
		
		btn_phone = (Button)view.findViewById(R.id.btn_phone);
		zhuce = (TextView) view.findViewById(R.id.zhuce);
		
		left_image_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		right_image_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new FragmentUtils(getActivity()).startFrgment(R.id.fg_login_regist,new UserLoginFragment());
			}
		});
		
		btn_get_verify.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		btn_phone.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		zhuce.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new FragmentUtils(getActivity()).startFrgment(R.id.fg_login_regist,new PersonRegistFragment());
			}
		});
		
		return view;
	}
}
