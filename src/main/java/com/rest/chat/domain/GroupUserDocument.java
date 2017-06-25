package com.rest.chat.domain;


public class GroupUserDocument {

	private UserDocument user;

	private GroupDocument chatRoom;
	
	private String loginTime;
	
	public UserDocument getUser() {
		return user;
	}

	public void setUser(UserDocument user) {
		this.user = user;
	}

	public GroupDocument getChatRoom() {
		return chatRoom;
	}

	public void setChatRoom(GroupDocument chatRoom) {
		this.chatRoom = chatRoom;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	
}
