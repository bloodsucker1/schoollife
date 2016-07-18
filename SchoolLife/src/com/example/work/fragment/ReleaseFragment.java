package com.example.work.fragment;

import com.example.work.R;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/*
 * 发布
 */
public class ReleaseFragment extends Fragment{

	private Context context;
	private String[] softinformation ={"自行车","笔记本","考研书","旧课本","旧手机","其他物品","其他"};
	private TextView tv_item_release_soft;
	private Dialog dialog;
	private Button btn_relese_post;
	private Button btn_relese_get;
	private Button btn_relese_give;
	private Button btn_relese_soft;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		context=getActivity();
		View view = inflater.inflate(R.layout.fragment_release, null);
		
		ImageButton ib_release = (ImageButton) view.findViewById(R.id.ib_release);
		btn_relese_post = (Button) view.findViewById(R.id.btn_relese_post);
		btn_relese_get = (Button) view.findViewById(R.id.btn_relese_get);
		btn_relese_give = (Button) view.findViewById(R.id.btn_relese_give);
		Button btn_release_next = (Button) view.findViewById(R.id.btn_release_next);
		
		EditText et_relese_describ = (EditText) view.findViewById(R.id.et_relese_describ);
		EditText et_relese_phone = (EditText) view.findViewById(R.id.et_relese_phone);
		EditText et_relese_price = (EditText) view.findViewById(R.id.et_relese_price);
		EditText et_relese_title = (EditText) view.findViewById(R.id.et_relese_title);
		
		final LinearLayout ll_release_soft = (LinearLayout) view.findViewById(R.id.ll_release_soft);
		btn_relese_soft = (Button) view.findViewById(R.id.btn_relese_soft);
		
		if(btn_relese_soft!=null){
			btn_relese_soft.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					//创建dialog
					Log.e("ds", "tv_item_release_soft");
					createDialog();
					
				}

				private void createDialog() {
					dialog = new Dialog(context,R.style.customDialog);
					View view = View.inflate(context, R.layout.dialog_release_soft, null);
					
					dialog.setContentView(view);
					dialog.show();
					
					ListView lv_release_soft =(ListView) view.findViewById(R.id.lv_release_soft);
					Button button = (Button) view.findViewById(R.id.btn_dialog_release_cancle);
					
					button.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							dialog.dismiss();
						}
					});
					softAdapter adapter = new softAdapter();
					lv_release_soft.setAdapter(adapter);
					
					lv_release_soft.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent, View view,
								int position, long id) {
							dialog.dismiss();
							btn_relese_soft.setText(softinformation[position]);
						}
					});
					
				}
			});
		}
		
		
		ib_release.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//上传照片
			}
		});
//		点击发布的时候
		btn_relese_post.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				btn_relese_post.setSelected(true);
				btn_relese_get.setSelected(false);
				btn_relese_give.setSelected(false);
				ll_release_soft.setVisibility(View.VISIBLE);
				
				
			}
		});
//		点击获取的时候
		btn_relese_get.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				btn_relese_post.setSelected(false);
				btn_relese_get.setSelected(true);
				btn_relese_give.setSelected(false);
				ll_release_soft.setVisibility(View.GONE);
			}
		});
//		赠送区
		btn_relese_give.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				btn_relese_post.setSelected(false);
				btn_relese_get.setSelected(false);
				btn_relese_give.setSelected(true);
				ll_release_soft.setVisibility(View.GONE);
			}
		});
		btn_release_next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				提交信息
			}
		});
		
		return view;
	}
	public class softAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return softinformation.length;
		}

		@Override
		public Object getItem(int position) {
			return softinformation[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView==null){
				convertView = View.inflate(context, R.layout.item_release_soft, null);
			}
			tv_item_release_soft = (TextView) convertView.findViewById(R.id.tv_item_release_soft);
			tv_item_release_soft.setText(softinformation[position]);
			return convertView;
		}
		
	}
}

