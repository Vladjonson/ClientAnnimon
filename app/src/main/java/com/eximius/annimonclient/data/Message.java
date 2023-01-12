package com.eximius.annimonclient.data;

public class Message {
	private int id;
	private String senderAvaUrl;
	private String sender;
	private String message;
	private String dateSend;

    public Message() {

    }

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setSenderAvaUrl(String senderAvaUrl) {
		this.senderAvaUrl = senderAvaUrl;
	}

	public String getSenderAvaUrl() {
		return senderAvaUrl;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSender() {
		return sender;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setDateSend(String dateSend) {
		this.dateSend = dateSend;
	}

	public String getDateSend() {
		return dateSend;
	}}
