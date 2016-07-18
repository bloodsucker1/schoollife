package com.example.work.manager;

import java.util.ArrayList;
import java.util.List;

import com.example.work.bean.appInfo;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

/**软件管理的业务类
 * 
 * @author Administrator
 *
 */
public class SoftManager {
	
	public static List<appInfo>  getAllSoftInfos(Context context){
		List<appInfo>  softInfos=new ArrayList<appInfo>();
		PackageManager packageManager=context.getPackageManager();//取得包管理器
		//取得所有的安装的包的信息
		List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
		//遍历包
		for(PackageInfo info: installedPackages){
			String packageName=info.packageName;//包名称
			String versionName=info.versionName;//版本名称
			String name=info.applicationInfo.loadLabel(packageManager).toString();//取得应用程序的名称
			Drawable icon=info.applicationInfo.loadIcon(packageManager);//取得应用程序的图标
			boolean isUser=true;//默认为用户程序
			boolean isSdcard=false;//默认不是安装到sdcard
			int flags = info.applicationInfo.flags;//标记，状态集
			if((flags&ApplicationInfo.FLAG_SYSTEM)==ApplicationInfo.FLAG_SYSTEM){ //判断是否为系统用户
				isUser=false;
			}
			if((flags&ApplicationInfo.FLAG_EXTERNAL_STORAGE)==ApplicationInfo.FLAG_EXTERNAL_STORAGE){ //判断是否安装到外村
				isSdcard=true;
			}
			
			appInfo softInfo=new appInfo(name, versionName, packageName, icon, isUser, isSdcard);
			softInfos.add(softInfo);
		}
		return softInfos;
	}

}
