package com.example.work.bean;

import android.graphics.drawable.Drawable;

/**软件信息的实体类
 * 
 * @author Administrator
 *
 */
public class appInfo {
	private String name;//应用程序名称
	private String versionName;//版本名称
	private String packageName;//包名
	private Drawable  icon;//应用程序图标
	private boolean isUser;//是否为用户程序
	private boolean isSdcard;//是否安装在sdcard外存中
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public Drawable getIcon() {
		return icon;
	}
	public void setIcon(Drawable icon) {
		this.icon = icon;
	}
	public boolean isUser() {
		return isUser;
	}
	public void setUser(boolean isUser) {
		this.isUser = isUser;
	}
	public boolean isSdcard() {
		return isSdcard;
	}
	public void setSdcard(boolean isSdcard) {
		this.isSdcard = isSdcard;
	}
	@Override
	public String toString() {
		return "SofeInfo [name=" + name + ", versionName=" + versionName
				+ ", packageName=" + packageName + ", icon=" + icon
				+ ", isUser=" + isUser + ", isSdcard=" + isSdcard + "]";
	}
	public appInfo() {
		super();
	}
	public appInfo(String name, String versionName, String packageName,
			Drawable icon, boolean isUser, boolean isSdcard) {
		super();
		this.name = name;
		this.versionName = versionName;
		this.packageName = packageName;
		this.icon = icon;
		this.isUser = isUser;
		this.isSdcard = isSdcard;
	}
	
	
	

}
