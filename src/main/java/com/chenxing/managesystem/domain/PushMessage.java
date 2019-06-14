package com.chenxing.managesystem.domain;
public class PushMessage {

	private String loginUserNum;
    private String content;
	private String userId;
	private String userName;
	private String receiveUserId;


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getReceiveUserId() {
		return receiveUserId;
	}

	public void setReceiveUserId(String receiveUserId) {
		this.receiveUserId = receiveUserId;
	}

	public String getLoginUserNum() {
		return loginUserNum;
	}

	public void setLoginUserNum(String loginUserNum) {
		this.loginUserNum = loginUserNum;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}