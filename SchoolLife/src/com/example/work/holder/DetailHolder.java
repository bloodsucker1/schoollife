package com.example.work.holder;

import com.example.work.R;
import com.example.work.bean.Comment;
import com.example.work.utils.UIUtils;

import android.view.View;
import android.widget.TextView;

public class DetailHolder extends BaseHolder<Comment> {

	private TextView tv_commment;
	private TextView tv_user;

	@Override
	public View initView() {
		View view = UIUtils.inflate(R.layout.item_comment);
		tv_user = (TextView) view.findViewById(R.id.tv_user);
		tv_commment = (TextView) view.findViewById(R.id.tv_commment);
		
		return view;
	}

	@Override
	public void refreshView(Comment data) {
			tv_user.setText(data.user_id);
			tv_commment.setText(data.comment);
	}
}
