package com.example.work.activity;

import java.util.ArrayList;
import java.util.Collection;

import com.example.work.R;
import com.example.work.adapter.HomePagerAdapter;
import com.example.work.fragment.ChangeFragment;
import com.example.work.fragment.PersonFragment;
import com.example.work.fragment.ReleaseFragment;
import com.example.work.fragment.SortFragment;
import com.example.work.widget.CustomActionBar;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class HomeActivity extends BaseActivity {

	private TextView tv_change;
	private TextView tv_sort;
	private TextView tv_person;
	private TextView tv_release;
	private ViewPager pager;
	private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
	private int pagesize;
	private ArrayList<TextView> tv = new ArrayList<TextView>();
	
	@Override
	public int setLayoutId() {
		return R.layout.activity_home;
	}

	@Override
	public void initListener() {
		tv_change.setOnClickListener(this);
		tv_sort.setOnClickListener(this);
		tv_person.setOnClickListener(this);
		tv_release.setOnClickListener(this);
	}

	@Override
	public void initData() {
		initViewPager();
	}

	@Override
	public void initView() {
		
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.actionbar);
		
	     //判断当前SDK版本号，如果是4.4以上，就是支持沉浸式状态栏的  
	    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
	            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
	            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
	        } 
	
		tv_sort = (TextView) findViewById(R.id.tv_sort);
		tv_release = (TextView) findViewById(R.id.tv_release);
		tv_change = (TextView) findViewById(R.id.tv_change);
		tv_person = (TextView) findViewById(R.id.tv_person);
		
		tv.add(tv_sort);
		tv.add(tv_release);
		tv.add(tv_change);
		tv.add(tv_person);
		
		changeColor(0);
	}

	
	private void initViewPager() {
		pager = (ViewPager) findViewById(R.id.vp_content);

		fragments.add(new SortFragment());
        fragments.add(new ReleaseFragment());
        fragments.add(new ChangeFragment());
        fragments.add(new PersonFragment());
        pagesize = fragments.size();

        HomePagerAdapter adapter = new HomePagerAdapter(getSupportFragmentManager(),fragments);
        pager.setAdapter(adapter);

        pager.setCurrentItem(0);
        
        pager.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            	changewithbar(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
	
	protected void changewithbar(int position) {
		switch (position) {
		case 0:
			changeColor(0);
			break;
		case 1:
			changeColor(1);
			break;
		case 2:
			changeColor(2);
			break;
		case 3:
			changeColor(3);
			break;
		default:
			break;
		}
		
	}

	private void changeColor(int position) {
		for(int i=0;i<tv.size();i++){
			if(i == position)
			{
				tv.get(i).setSelected(true);
			}else{
				tv.get(i).setSelected(false);
			}
		}
	}

	@Override
	public void childonClick(View v) {
		switch (v.getId())
		{
        case R.id.tv_sort:
            pager.setCurrentItem(0);
            changeColor(0);
            break;
        case R.id.tv_release:
            pager.setCurrentItem(1);
            changeColor(1);
            break;
        case R.id.tv_change:
            pager.setCurrentItem(2);
            changeColor(2);
            break;
        case R.id.tv_person:
            pager.setCurrentItem(3);
            changeColor(3);
            break;
        default:
            break;
		}
	}

	@Override
	public void setrequestWindowFeature() {
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
	}
}
