package com.agozuberk.kafkaproducer.model;

public class MessageRequest {
	private String serverName;
	private String message;

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
