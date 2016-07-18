package com.example.work.holder;


import com.example.work.R;
import com.example.work.utils.UIUtils;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
/**
 * @author Administrator
 * 加载更多Holder
 * 1.可以加载更多
 * 2.不能加载更多
 * 3.加载更多失败
 */
public class MoreHolder extends BaseHolder<Integer> {

	public static final int TYPE_HAS_MORE = 0;//可以加载更多
	public static final int TYPE_NO_MORE = 1;//不能加载更多
	public static final int TYPE_MORE_ERROR = 2;//加载更多失败
	private LinearLayout llisloading;
	private TextView loaderror;
	
	public MoreHolder(boolean hasMore) {
		//根据是否可以加载更多，设置当前的加载类型，一旦有数据就调用refreshView
		setData(hasMore?TYPE_HAS_MORE:TYPE_NO_MORE);
	}
	@Override
	public View initView() {
		View view = UIUtils.inflate(R.layout.item_listview_more);
		llisloading = (LinearLayout) view.findViewById(R.id.llisloading);
		loaderror = (TextView)view.findViewById(R.id.tv_loaderror);
		return view;
	}
	@Override
	public void refreshView(Integer data) {
		switch (data) {
		case TYPE_HAS_MORE:
			//可以加载更多,展示加载界面
			Log.e("TYPE_HAS_MORE", "TYPE_HAS_MORE");
			llisloading.setVisibility(View.VISIBLE);
			loaderror.setVisibility(View.GONE);
			break;
		case TYPE_NO_MORE:
			//不能加载更多
			Log.e("TYPE_NO_MORE", "TYPE_NO_MORE");
			llisloading.setVisibility(View.GONE);
			loaderror.setVisibility(View.GONE);
			break;
		case TYPE_MORE_ERROR:
			//加载更多失败
			Log.e("TYPE_MORE_ERROR", "TYPE_MORE_ERROR");
			llisloading.setVisibility(View.GONE);
			loaderror.setVisibility(View.GONE);
//			Toast.makeText(UIUtils.getContext(), "加载失败", 0).show();
			break;
		default:
			break;
		}
	}
}
