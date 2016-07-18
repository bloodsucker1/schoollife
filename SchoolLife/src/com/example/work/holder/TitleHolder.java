package com.example.work.holder;

import com.example.work.R;
import com.example.work.bean.SoftInfo;
import com.example.work.utils.UIUtils;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class TitleHolder extends BaseHolder<SoftInfo> {

	private TextView tv_title;

	@Override
	public View initView() {
		View view = UIUtils.inflate(R.layout.item_title);
		tv_title = (TextView) view.findViewById(R.id.tv_title);
		return view;
	}

	@Override
	public void refreshView(SoftInfo data) {
		tv_title.setText(data.title);
	}
}
