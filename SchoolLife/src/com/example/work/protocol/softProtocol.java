package com.example.work.protocol;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.work.bean.SoftInfo;
import com.google.gson.JsonArray;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

public class softProtocol {
	
	public ArrayList<SoftInfo> parsejson(String json){
		ArrayList<SoftInfo> infos = new ArrayList<SoftInfo>();
		
		Log.e("json",""+json);
		try {
			JSONArray ja = new JSONArray(json);
			for(int i=0;i<ja.length();i++){
				SoftInfo info = new SoftInfo();
				JSONObject jo = ja.getJSONObject(i);
				
				info.activity1 = jo.getString("activity1");
				info.activity2 = jo.getString("activity2");
				info.activity3 = jo.getString("activity3");
				info.istitle = jo.getBoolean("istitle");
				info.iv_project1 = jo.getString("iv_project1");
				info.iv_project2 = jo.getString("iv_project2");
				info.iv_project3 = jo.getString("iv_project3");
				info.title = jo.getString("title");
				info.tv_project1 = jo.getString("tv_project1");
				info.tv_project2 = jo.getString("tv_project2");
				info.tv_project3 = jo.getString("tv_project3");
				
				infos.add(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return infos;
	}
}
