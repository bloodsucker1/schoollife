package com.example.work.holder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.work.R;
import com.example.work.activity.OldThingsActivity;
import com.example.work.activity.ProjectActivity;
import com.example.work.activity.GoodActivity;
import com.example.work.activity.TogetherActivity;
import com.example.work.bean.SoftInfo;
import com.example.work.utils.UIUtils;

public class ContentHolder extends BaseHolder<SoftInfo> implements OnClickListener{

	private TextView tv_name1;
	private TextView tv_name2;
	private TextView tv_name3;
	private ImageView iv_icon1;
	private ImageView iv_icon2;
	private ImageView iv_icon3;
	private LinearLayout ll_grid1;
	private LinearLayout ll_grid2;
	private LinearLayout ll_grid3;
	private Intent intent;
	private Context context;
	private String activity1;
	private String activity2;
	private String activity3;
	public static HashMap<String,Class> arrayList= new HashMap<String,Class>();

	public ContentHolder(Context context) {
		this.context = context;
	}

	@Override
	public View initView() {
		View view = UIUtils.inflate(R.layout.item_gride);
		tv_name1 = (TextView) view.findViewById(R.id.tv_name1);
		tv_name2 = (TextView) view.findViewById(R.id.tv_name2);
		tv_name3 = (TextView) view.findViewById(R.id.tv_name3);
		
		iv_icon1 = (ImageView) view.findViewById(R.id.iv_icon1);
		iv_icon2 = (ImageView) view.findViewById(R.id.iv_icon2);
		iv_icon3 = (ImageView) view.findViewById(R.id.iv_icon3);
		
		ll_grid1 = (LinearLayout) view.findViewById(R.id.ll_grid1);
		ll_grid2 = (LinearLayout) view.findViewById(R.id.ll_grid2);
		ll_grid3 = (LinearLayout) view.findViewById(R.id.ll_grid3);
		return view;
	}

	@Override
	public void refreshView(SoftInfo data) {
		
		activity1 = data.activity1;
		activity2 = data.activity2;
		activity3 = data.activity3;
		setHashMapList();
		
		tv_name1.setText(data.tv_project1);
		tv_name2.setText(data.tv_project2);
		tv_name3.setText(data.tv_project3);
		iv_icon1.setImageResource(UIUtils.getId(data.iv_project1));
		iv_icon2.setImageResource(UIUtils.getId(data.iv_project2));
		iv_icon3.setImageResource(UIUtils.getId(data.iv_project3));
		
		ll_grid1.setOnClickListener(this);
		ll_grid2.setOnClickListener(this);
		ll_grid3.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		intent = new Intent();
		switch (v.getId()) {
		case R.id.ll_grid1:
			enter((Activity)context,activity1);
			break;
		case R.id.ll_grid2:
			enter((Activity)context,activity2);
			break;
		case R.id.ll_grid3:
			enter((Activity)context,activity3);
			break;
		default:
			break;
		}
	}
	private void enter(Activity activity1,String activity2){
		Log.e("activity2", activity2+"");
		if(!activity2.isEmpty()){
				intent.setClass(activity1,arrayList.get(activity2));
				context.startActivity(intent);
		}else{
			Toast.makeText(context, "尚未开放，敬请期待", 0).show();
		}
	}
	private void setHashMapList(){
		arrayList.put("ProjectActivity", ProjectActivity.class);
		arrayList.put("TogetherActivity",TogetherActivity.class);
		arrayList.put("GoodActivity",GoodActivity.class);
		arrayList.put("OldThingsActivity",OldThingsActivity.class);
	}
}
