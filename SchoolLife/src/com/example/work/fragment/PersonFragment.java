package com.example.work.fragment;

import com.example.work.R;
import com.example.work.activity.PersonInfoActiviy;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/*
 * 个人
 */
public class PersonFragment extends Fragment implements OnClickListener{

	private LinearLayout btn_login;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_person, null);
		btn_login = (LinearLayout) view.findViewById(R.id.btn_login);
		btn_login.setOnClickListener(this);
		return view;
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login:
			Intent intent = new Intent(getActivity(),PersonInfoActiviy.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

}
