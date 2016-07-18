package com.example.work.fragment;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.work.R;
import com.example.work.activity.ProjectActivity;
import com.example.work.activity.GoodActivity;
import com.example.work.adapter.SoftAdapter;
import com.example.work.bean.SoftInfo;
import com.example.work.protocol.softProtocol;
import com.example.work.utils.IOUtils;
import com.example.work.utils.LogUtils;
import com.example.work.widget.CustomActionBar;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

/*
 * 分类
 */
public class SortFragment extends BaseFragment implements OnClickListener{

	private ListView listview;
	private ArrayList<SoftInfo> mdata = new ArrayList<SoftInfo>();
	private Context context;
	private static final int TITLE =1;
	private static final int CONTENT =2; 
	
	@Override
	public int setLayoutId() {
		return R.layout.fragment_sort;
	}
	@Override
	public void initListener() {
	}
	@Override
	public void initData() {
	}
	@Override
	public void initView() {
		
		context = getActivity();
		setData();
		ListView lv_sort = findView(R.id.lv_sort);
		SoftAdapter adapter = new SoftAdapter(context,mdata);
		lv_sort.setAdapter(adapter);
	}

	@Override
	public void childonClick(View v) {
		
	}
	private void setData() {
		String path = context.getFilesDir().getAbsolutePath()+"/soft.json";
		String json = IOUtils.readFile(path);
		
		if(json!=null){
			mdata = new softProtocol().parsejson(json);
		}
		
	}
	
}
