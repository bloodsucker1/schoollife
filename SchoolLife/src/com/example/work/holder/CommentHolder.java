package com.example.work.holder;

import com.example.work.R;
import com.example.work.bean.Comment;
import com.example.work.utils.UIUtils;

import android.view.View;
import android.widget.TextView;

public class CommentHolder extends BaseHolder<Comment> {

	private TextView tv_comment;
	private TextView tv_comment_com;

	@Override
	public View initView() {
		View view = UIUtils.inflate(R.layout.item_c_com);
		tv_comment = (TextView) view.findViewById(R.id.comment_user);
		tv_comment_com = (TextView) view.findViewById(R.id.comment_com); 
		return view;
	}

	@Override
	public void refreshView(Comment data) {
		tv_comment.setText(data.comment);
		tv_comment_com.setText(data.user_id);
	}

}
