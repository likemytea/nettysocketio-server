package com.chenxing.managesystem.domain;

import java.util.Map;

/**
 * Created by liuxing on 19/6/17.
 */

public class Msg {
	// 消息id，这里指双方的userid组合，组合顺序为字典序升序。
	private String msgId;
	// 这里指聊天对象
	private String userId;

	private Map<Long, String> content;

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Map<Long, String> getContent() {
		return content;
	}

	public void setContent(Map<Long, String> content) {
		this.content = content;
	}

}
