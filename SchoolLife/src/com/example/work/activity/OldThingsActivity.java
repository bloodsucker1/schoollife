package com.example.work.activity;

import com.example.work.R;
import com.example.work.utils.UIUtils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class OldThingsActivity extends Activity{
	
	private String[] softinformation ={"自行车","笔记本","考研书","旧课本","旧手机","其他物品"};
	private int[] softpic = {R.drawable.old_bike,R.drawable.old_computer,R.drawable.old_graguate_book,R.drawable.old_book,R.drawable.old_phone,R.drawable.old_otherthing};
	private Context context;
	private int[] vp_content = {R.drawable.home01,R.drawable.home02,R.drawable.home03};
	private ViewPager vp_advertisement;
	private LinearLayout mIndicator;
	private int mPreviousPos;// 上一个圆点位置
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		context = this;
		setContentView(R.layout.actiivity_oldthing);
		
		LinearLayout ll = (LinearLayout) findViewById(R.id.ll_head);
		// 初始化根布局
		RelativeLayout root = new RelativeLayout(UIUtils.getContext());
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				UIUtils.getDimen(R.dimen.list_header_height));
		root.setLayoutParams(params);

		// 给根布局添加viewpager
		vp_advertisement = new ViewPager(UIUtils.getContext());
		RelativeLayout.LayoutParams vpParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
				root.addView(vp_advertisement, vpParams);// 添加viewpager, 宽高填充父窗体

		// 添加线性布局,展示小圆点
		mIndicator = new LinearLayout(UIUtils.getContext());
		mIndicator.setOrientation(LinearLayout.HORIZONTAL);// 水平方向
		RelativeLayout.LayoutParams llParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		// 在相对布局的右下角在展示
		llParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);// 下方
		llParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);// 右侧

		// 设置内边距
		int padding = UIUtils.dip2px(5);
		mIndicator.setPadding(padding, padding, padding, padding);

		root.addView(mIndicator, llParams);
		ll.addView(root);
		
		
		//设置轮播条
		OldAdapter oldadapter = new OldAdapter();
		vp_advertisement.setAdapter(oldadapter);
		vp_advertisement.setCurrentItem(vp_content.length*1000);
		
		// 初始化小圆点
		for (int i = 0; i < vp_content.length; i++) {
			ImageView child = new ImageView(UIUtils.getContext());
			LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);

			if (i > 0) {
				params1.leftMargin = UIUtils.dip2px(3);// 左边距
				child.setImageResource(R.drawable.indicator_normal);
			} else {
				child.setImageResource(R.drawable.indicator_selected);//默认第一个被选中
			}

			child.setLayoutParams(params1);
			mIndicator.addView(child);
		}
		
		vp_advertisement.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				position = position % vp_content.length;

				ImageView child = (ImageView) mIndicator.getChildAt(position);
				child.setImageResource(R.drawable.indicator_selected);// 当前圆点为选中的图片

				// 上一个圆点变为默认图片
				ImageView preChild = (ImageView) mIndicator
						.getChildAt(mPreviousPos);
				preChild.setImageResource(R.drawable.indicator_normal);

				mPreviousPos = position;
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
		new Task().start();
		
		GridView gv_oldthings = (GridView) findViewById(R.id.gv_oldthings);
		
		ThingsAdapter adapter = new ThingsAdapter();
		gv_oldthings.setAdapter(adapter);
	}
	public class ThingsAdapter extends BaseAdapter{

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
				convertView = View.inflate(context, R.layout.item_oldthing, null);
			}
			ImageView iv_old_icon = (ImageView) convertView.findViewById(R.id.iv_old_icon);
			TextView tv_old_name = (TextView) convertView.findViewById(R.id.tv_old_name);
			
			iv_old_icon.setImageResource(softpic[position]);
			tv_old_name.setText(softinformation[position]);
			
			return convertView;
		}
	}
	public class OldAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View)object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView view = new ImageView(context);
			view.setImageResource(vp_content[position%3]);
			container.addView(view);
			return view;
		}
	}
	class Task implements Runnable{
		// 启动轮播动画
		public void start() {
		// 要将之前发送的消息移除掉,避免发送重复消息
			UIUtils.getHandler().removeCallbacksAndMessages(null);// 移除所有消息和Runnable(post)
			UIUtils.getHandler().postDelayed(this, 3000);// 发送延时3秒的Runnable
		}

		@Override
		public void run() {
			// 每个3秒会走run方法
			// 跳到下一个页面
			int currentItem = vp_advertisement.getCurrentItem();
				currentItem++;

				vp_advertisement.setCurrentItem(currentItem);
			// 继续发消息
				UIUtils.getHandler().postDelayed(this, 3000);	
		}
		
	}
}
