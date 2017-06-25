package com.rest.chat.domain;

import java.io.Serializable;

public class MessageDocument1 implements Serializable{
    private static final long serialVersionUID = 1L;
private int id;

private String messageContent;

private String groupName;

private String guId;

private int sessionid;
public String getMessageContent() {
	return messageContent;
}

public void setMessageContent(String msg) {
	this.messageContent = msg;
}


public int getId() {
	return id;
}

public void setId(int id1) {
	this.id = id1;
}


public String getGroupName() {
	return groupName;
}

public void setGroupName(String grpname) {
	this.groupName = grpname;
}
public String getGuId() {
	return guId;
}

public void setGuId(String guid) {
	this.guId = guid;
}

 public void setsessionid(int Id) {
		this.sessionid = Id;
	}

	public int getsessionid() {
		return sessionid;
	}
}
