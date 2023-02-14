package com.eximius.annimonclient.data.forum;

public class Post {

    private String user;
	private int userId;
	private String text;
	private long time;
	private int messageId;
	private int replyToId;


	public void setUser(String user) {
		this.user = user;
	}

	public String getUser() {
		return user;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public long getTime() {
		return time;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setReplyToId(int replyToId) {
		this.replyToId = replyToId;
	}

	public int getReplyToId() {
		return replyToId;
	}
}
