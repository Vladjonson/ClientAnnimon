package com.eximius.annimonclient.data.forum;
import java.util.ArrayList;

public class Section {

    private int id;
	private String text;
	private int hiden;
	private ArrayList<SubSection> subsections;


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

	public void setSubsections(ArrayList<SubSection> subsections) {
		this.subsections = subsections;
	}

	public ArrayList<SubSection> getSubsections() {
		return subsections;
	}
}
