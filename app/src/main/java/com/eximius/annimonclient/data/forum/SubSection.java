package com.eximius.annimonclient.data.forum;

public class SubSection {

    private int id;
	private String text;
	private int hiden;


	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setHiden(int hiden) {
		this.hiden = hiden;
	}

	public int getHiden() {
		return hiden;
	}
}
