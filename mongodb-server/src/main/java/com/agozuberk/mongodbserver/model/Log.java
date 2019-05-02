package com.agozuberk.mongodbserver.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Log {
	@Id
	public ObjectId _id;

	public String serverName;
	public String log;

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

}
