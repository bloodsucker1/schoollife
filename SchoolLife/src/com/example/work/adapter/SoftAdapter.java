package com.example.work.adapter;

import java.util.ArrayList;
import com.example.work.R;
import com.example.work.bean.SoftInfo;
import com.example.work.bean.item_detail;
import com.example.work.holder.BaseHolder;
import com.example.work.holder.ContentHolder;
import com.example.work.holder.TitleHolder;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SoftAdapter extends BaseAdapter{

	private static final int TITLE = 0;
	private static final int BODY = 1;
	private Context context;
	private ArrayList<SoftInfo> mdata;
	
	public SoftAdapter(Context context, ArrayList<SoftInfo> mdata) {
		this.context = context;
		this.mdata = mdata;
	}

	@Override
	public int getCount() {
		return mdata.size();
	}

	@Override
	public Object getItem(int position) {
		return mdata.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BaseHolder holder;
		if(convertView == null){
			if(getItemViewType(position) == TITLE){
				holder = new TitleHolder();
			}else{
				holder = new ContentHolder(context);
			}
		}else{
			holder = (BaseHolder)convertView.getTag();
		}
		holder.setData(mdata.get(position));
		return holder.getMyRootView();
	}

	@Override
	public int getItemViewType(int position) {
		if(mdata.get(position).istitle){
			return TITLE;
		}
		return BODY;
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}

	
}
