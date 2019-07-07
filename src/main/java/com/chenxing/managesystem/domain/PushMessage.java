package com.chenxing.managesystem.domain;

import java.io.Serializable;

public class PushMessage implements Serializable {
	private static final long serialVersionUID = 8965223926827445665L;
	private String sender;
	private String nickName;
	private String content;
	private String chatPerson;

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getChatPerson() {
		return chatPerson;
	}

	public void setChatPerson(String chatPerson) {
		this.chatPerson = chatPerson;
	}

}