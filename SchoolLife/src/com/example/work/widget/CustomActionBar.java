package com.example.work.widget;

import java.io.NotActiveException;

import com.example.work.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CustomActionBar{
	
	private static Activity mActivity;
	private static Context context;
	  
    /** 
     * @see [自定义标题栏] 
     * @param activity 
     * @param title 
     */  
    public static void getTitleBar(Activity activity,String title) {  
        mActivity = activity;
        context = activity;
        
        activity.setContentView(R.layout.actionbar);  
        activity.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,  
                R.layout.actionbar);  
        TextView textView = (TextView) activity.findViewById(R.id.tv_action_bar_title);  
        textView.setText(title);  
        ImageButton btn_action_bar_left = (ImageButton) activity.findViewById(R.id.btn_action_bar_left);  
        btn_action_bar_left.setOnClickListener(new OnClickListener() {  
            public void onClick(View v) {  
               Toast.makeText(context, "左按钮", Toast.LENGTH_SHORT).show();
            }  
        });  
    }  

}
