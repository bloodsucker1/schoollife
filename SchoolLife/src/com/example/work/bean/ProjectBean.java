package com.example.work.bean;

import java.io.Serializable;

public class ProjectBean implements Serializable{

	public String message_title;
	public String message_body;
	public String username;
	public String message_id;
	public String QQ;
	public String message_request; 
	public static String user;
	
	
	public String getMessage_request() {
		return message_request;
	}
	public void setMessage_request(String message_request) {
		this.message_request = message_request;
	}
	
	public static String getUser() {
		return user;
	}
	public static void setUser(String user) {
		ProjectBean.user = user;
	}
	public String getQQ() {
		return QQ;
	}
	public void setQQ(String qQ) {
		QQ = qQ;
	}
	public String getMessage_id() {
		return message_id;
	}
	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}
	public String getMessage_title() {
		return message_title;
	}
	public void setMessage_title(String message_title) {
		this.message_title = message_title;
	}
	public String getMessage_body() {
		return message_body;
	}
	public void setMessage_body(String message_body) {
		this.message_body = message_body;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public ProjectBean(String message_title, String message_body,
			 String qQ,String message_request) {
		super();
		this.message_title = message_title;
		this.message_body = message_body;
		QQ = qQ;
		this.message_request = message_request;
	}
	public ProjectBean() {
	}
	@Override
	public String toString() {
		return "ProjectBean [message_title=" + message_title
				+ ", message_body=" + message_body + ", message_id="
				+ message_id + "]";
	}
	
}
