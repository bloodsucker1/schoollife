package com.example.work.protocol;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.example.work.bean.Comment;
import com.example.work.utils.HttpUtil;
import com.example.work.utils.URLData;

public class DetailsProtocol extends BaseProtocol<Comment> {

	private String i_id;
	
	public DetailsProtocol(String i_id) {
		this.i_id = i_id;
	}

	@Override
	public String getParams() {
		return "";
	}

	@Override
	public String getkey() {
		return "";
	}

	@Override
	public ArrayList<Comment> parsejson(String result) {
		
		Log.e("DetailsProtocol_ArrayList",""+result);
		try {
			JSONArray ja = new JSONArray(result);
			ArrayList<Comment> infos = new ArrayList<Comment>();
			
			for(int i=0;i<ja.length();i++){
				JSONObject jo = ja.getJSONObject(i);
				Comment info = new Comment();
				info.user_id = jo.getString("u_id");
				info.c_id = jo.getString("c_id");
				info.c_c_id = jo.getString("c_c_id");
				info.comment  = jo.getString("comment");
				
				infos.add(info);
			}
			return infos;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void getDataFromUrl() {
		Log.e("i_id", ""+i_id);
		HttpUtil.get(URLData.CommentURL,i_id);
	}
}
