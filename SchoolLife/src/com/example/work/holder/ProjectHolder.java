package com.example.work.holder;

import com.example.work.R;
import com.example.work.bean.ProjectBean;
import com.example.work.utils.BaseApplication;
import com.example.work.utils.UIUtils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProjectHolder extends BaseHolder<ProjectBean> {

	private ImageView iv_user;
	private TextView tv_user_content;
	private TextView tv_user_topic;
	private TextView tv_user;
	@Override
	public View initView() {
		View view =UIUtils.inflate(R.layout.item_project);
		tv_user = (TextView) view.findViewById(R.id.tv_user);
		tv_user_topic = (TextView) view.findViewById(R.id.tv_user_topic);
		tv_user_content = (TextView) view.findViewById(R.id.tv_user_content);
		iv_user = (ImageView) view.findViewById(R.id.iv_user);
		return view;
	}

	@Override
	public void refreshView(ProjectBean data) {
		tv_user.setText(data.getUsername());
		tv_user_topic.setText(data.getMessage_title());
		tv_user_content.setText(data.getMessage_body());
	}
}
