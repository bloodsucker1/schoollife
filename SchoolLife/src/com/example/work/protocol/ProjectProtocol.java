package com.example.work.protocol;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.example.work.bean.ProjectBean;
import com.example.work.utils.HttpUtil;
import com.example.work.utils.URLData;

public class ProjectProtocol extends BaseProtocol{

	@Override
	public String getParams() {
		return "";
	}

	@Override
	public String getkey() {
		return "";
	}

	@Override
	public ArrayList<ProjectBean> parsejson(String json) {
		ArrayList<ProjectBean> projectinfos = new ArrayList<ProjectBean>();
		try {
			
			Log.e("json", ""+json);
			JSONArray ja = new JSONArray(json);
			for(int i=0;i<ja.length();i++){
				JSONObject jo1 = ja.getJSONObject(i);
				
				ProjectBean projectinfo = new ProjectBean();
				
				projectinfo.username= jo1.getString("uid");
				projectinfo.message_id= jo1.getString("id");
				projectinfo.message_body= jo1.getString("body");
				projectinfo.message_title = jo1.getString("title");
				projectinfo.QQ = jo1.getString("qqid");
				projectinfo.message_request = jo1.getString("pdemand");
				
				projectinfos.add(projectinfo);
			}
			
			return projectinfos;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void getDataFromUrl() {
		HttpUtil.get(URLData.GetURL);
	}

}
