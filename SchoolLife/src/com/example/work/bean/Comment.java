package com.example.work.bean;

public class Comment {
	public String user_id;
	public String c_id;
	public String c_c_id;
	public String comment;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public String getC_c_id() {
		return c_c_id;
	}
	public void setC_c_id(String c_c_id) {
		this.c_c_id = c_c_id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		comment = comment;
	}
	public Comment(String user_id, String c_id, String c_c_id, String comment) {
		super();
		this.user_id = user_id;
		this.c_id = c_id;
		this.c_c_id = c_c_id;
		comment = comment;
	}
	public Comment() {
	}
	@Override
	public String toString() {
		return "Comment [user_id=" + user_id + ", c_id=" + c_id + ", c_c_id="
				+ c_c_id + ", comment=" + comment + "]";
	}
}
