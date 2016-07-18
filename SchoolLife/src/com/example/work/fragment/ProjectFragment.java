package com.example.work.fragment;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import com.example.work.R;
import com.example.work.activity.ProjectDetails;
import com.example.work.adapter.MyAdapter;
import com.example.work.bean.ProjectBean;
import com.example.work.holder.BaseHolder;
import com.example.work.holder.ProjectHolder;
import com.example.work.protocol.ProjectProtocol;
import com.example.work.utils.HttpUtil;
import com.example.work.utils.UIUtils;
import com.example.work.widget.LoadingPage.Result;

public class ProjectFragment extends LoadBaseFragment {

	private ArrayList<ProjectBean> mData;
	private View view;
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		loadData();
	}
	@Override
	public View onCreateSuccessView() {
		view = View.inflate(getActivity(), R.layout.fragment_project, null);
		ListView listview = (ListView) view.findViewById(R.id.lv_project);
		ImageButton PostMessage = (ImageButton)view.findViewById(R.id.PostMessage);  
		ProjectAdapter adapter = new ProjectAdapter(mData);
		listview.setAdapter(adapter);
		Log.e("onCreateSuccessView", "onLoad");
		
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				ProjectBean info = mData.get(position);
				Intent intent = new Intent(getActivity(),ProjectDetails.class);
				Bundle bundle = new Bundle();
				if(info!=null){
					Log.e("setOnItemClickListener",info.toString());
				}else{
					Log.e("setOnItemClickListener","info是空");
				}
				bundle.putSerializable("ProjectBean",info);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		return view;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Result onLoad() {
		Log.e("ProjectFragment", "onLoad");
		ProjectProtocol protocol =new ProjectProtocol();
			mData = (ArrayList<ProjectBean>) protocol.getData(0);
			Log.e("Result",mData.toString());
		return check(mData);
	}
	public class ProjectAdapter extends MyAdapter<ProjectBean>{

		public ProjectAdapter(ArrayList<ProjectBean> data) {
			super(data);
		}

		@Override
		public BaseHolder getHolder(int position) {
			return new ProjectHolder();
		}

		@Override
		public ArrayList loadData() {
//			ProjectProtocol protocol = new ProjectProtocol();
//			ArrayList<ProjectBean> moreData = (ArrayList<ProjectBean>) protocol.getData(getListSize());
//			return moreData;
			return null;
		}
	}
	@Override
	public View onCreateEmptyView() {
		return UIUtils.inflate(R.layout.layout_empty);
	}
}
