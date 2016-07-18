package com.example.work.fragment;

import java.util.ArrayList;
import java.util.zip.Inflater;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.work.R;
import com.example.work.activity.ProjectDetails;
import com.example.work.adapter.MyAdapter;
import com.example.work.bean.Comment;
import com.example.work.bean.ProjectBean;
import com.example.work.holder.BaseHolder;
import com.example.work.holder.DetailHolder;
import com.example.work.protocol.BaseProtocol;
import com.example.work.protocol.DetailsProtocol;
import com.example.work.protocol.ProjectProtocol;
import com.example.work.utils.UIUtils;
import com.example.work.widget.LoadingPage.Result;

public class DetailFragment extends LoadBaseFragment {

	private TextView tv_user;
	private TextView tv_user_topic;
	private TextView tv_user_content;
	private TextView tv_request;
	private Button btn_add;
	private ListView lv_detail;
	private String i_id ;
	private ArrayList<Comment> mData = new ArrayList<Comment>();
	private static ProjectBean pb;

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		pb = ((ProjectDetails)getActivity()).pb;
		i_id = pb.message_id;
		
		Log.e("DetailFragment_onActivityCreated",""+pb.message_id+"sdf");
		loadData();
	}
	@Override
	public View onCreateSuccessView() {
		View view = View.inflate(getActivity(), R.layout.fragment_details, null);
	
		tv_user = (TextView) view.findViewById(R.id.tv_user);
		tv_user_topic = (TextView) view.findViewById(R.id.tv_user_topic);
		tv_user_content = (TextView) view.findViewById(R.id.tv_user_content);
		tv_request = (TextView) view.findViewById(R.id.tv_request);
		
		tv_user.setText(pb.user);
		tv_user_topic.setText(pb.message_title);
		tv_user_content.setText(pb.message_body);
		tv_request.setText(pb.message_request);
		
		btn_add = (Button) view.findViewById(R.id.btn_add);
		lv_detail = (ListView) view.findViewById(R.id.lv_detail);
		
		lv_detail.setAdapter(new DetailAdapter(mData));
		new DetailAdapter(mData).setListViewHeightBasedOnChildren(lv_detail);
		
		return view;
	}

	@Override
	public Result onLoad() {
	
		BaseProtocol protocol = new DetailsProtocol(i_id);
		mData = protocol.getData(0);
		Log.e("Result",mData.toString());
		return check(mData);
	}
	class DetailAdapter extends MyAdapter<Comment>{

		public DetailAdapter(ArrayList arrayList) {
			super(arrayList);
		}

		@Override
		public BaseHolder getHolder(int position) {
			return new DetailHolder();
		}

		@Override
		public ArrayList loadData() {
//			DetailsProtocol protocol = new DetailsProtocol(i_id);
//			ArrayList<Comment> moreData = (ArrayList<Comment>) protocol.getData(getListSize());
//			return moreData;
			return null;
		}
		public void setListViewHeightBasedOnChildren(ListView listView) {  
	        ListAdapter listAdapter = listView.getAdapter();   
	        if (listAdapter == null) {  
	            return;  
	        }  
	  
	        int totalHeight = 0;  
	        for (int i = 0; i < listAdapter.getCount()-1; i++) {  
	            View listItem = listAdapter.getView(i, null, listView);  
	            listItem.measure(0, 0);  
	            totalHeight += listItem.getMeasuredHeight();  
	        }  
	  
	        ViewGroup.LayoutParams params = listView.getLayoutParams();  
	        params.height = totalHeight;  
	        listView.setLayoutParams(params);  
		}

		@Override
		public int getViewTypeCount() {
			return 3;
		}
	}
	@Override
	public View onCreateEmptyView() {
		View view = UIUtils.inflate(R.layout.layout_datail_empty);
		pb = ((ProjectDetails)getActivity()).pb;
		if(pb!=null){
			((TextView) view.findViewById(R.id.tv_user)).setText(pb.user);
			((TextView) view.findViewById(R.id.tv_user_topic)).setText(pb.message_title);
			((TextView) view.findViewById(R.id.tv_user_content)).setText(pb.message_body);
			btn_add = (Button) view.findViewById(R.id.btn_add);
		}else{
			Log.e("onCreateEmptyView","pb伪空");
		}
		return view;
	}
}

