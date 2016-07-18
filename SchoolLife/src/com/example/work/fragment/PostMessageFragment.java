package com.example.work.fragment;

import com.example.work.R;
import com.example.work.activity.ProjectActivity;
import com.example.work.bean.ProjectBean;
import com.example.work.utils.HttpUtil;
import com.example.work.utils.URLData;
import com.example.work.widget.LoadingPage.Result;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PostMessageFragment extends Fragment {
	
	private Context context;
	private EditText title;
	private EditText body;
	private EditText tv_request;
	private EditText QQ;
	private boolean isOkey;
	
		@Override
		public View onCreateView(LayoutInflater inflater,
				@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
			
			View view = inflater.inflate(R.layout.postmessage, null);
			context = getActivity();
			
			title = (EditText) view.findViewById(R.id.tv_message_title);
			body = (EditText) view.findViewById(R.id.tv_message_body);
			QQ = (EditText) view.findViewById(R.id.tv_qq);
			tv_request = (EditText) view.findViewById(R.id.tv_request);
			
			Button btn_post = (Button) view.findViewById(R.id.btn_post);
			
			btn_post.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Log.e("onClick","btn_post");
					String message_title = title.getText().toString();
					String message_body = body.getText().toString();
					String qq = QQ.getText().toString();
					String name = "159";
					String et_request = tv_request.getText().toString();
					
					if(message_body.isEmpty()){
						Toast.makeText(context, "内容输入不能为空", 0).show();
					}else{
						ProjectBean Project = new ProjectBean(message_title,message_body,qq,et_request);
						//提交数据
						HttpUtil.Post(Project, URLData.IdeaURL);
						
						if(CheckResult(Project)){
							enterProjectFragment();
						}
					}
				}
			});
			return view;
		}
		
		//返回界面
		private void enterProjectFragment() {
			
			ProjectFragment fragment = new ProjectFragment();
			
			((ProjectActivity) getActivity()).transition = ((ProjectActivity) getActivity()).manager.beginTransaction();
			((ProjectActivity) getActivity()).transition.replace(R.id.fg_project, fragment);
			((ProjectActivity) getActivity()).transition.commit();
		}
		//对结果进行判断
		private boolean CheckResult(ProjectBean Project) {

			HttpUtil.handler = new Handler(){
				@Override
				public void handleMessage(Message msg) {
					super.handleMessage(msg);
					if((Boolean) msg.obj){
						Toast.makeText(context, "提交成功", 0).show();
						isOkey = true;
					}
				}
			};
			return isOkey;
		}
}
