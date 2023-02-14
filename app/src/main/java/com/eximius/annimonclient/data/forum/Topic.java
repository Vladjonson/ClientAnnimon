package com.eximius.annimonclient.data.forum;

public class Topic {

    private int id;
	private String title;
	private int isClosed;
	private long time;


	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setIsClosed(int isClosed) {
		this.isClosed = isClosed;
	}

	public int getIsClosed() {
		return isClosed;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public long getTime() {
		return time;
	}
}
