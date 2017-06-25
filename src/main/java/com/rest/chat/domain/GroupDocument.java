package com.rest.chat.domain;

import java.io.Serializable;

public class GroupDocument implements Serializable {

    private static final long serialVersionUID = 1L;
	private String guId;
	
	private String groupName;
        
        private int userId1,userId2;

	public String getGuId() {
		return guId;
	}

	public void setGuId(String guId) {
		this.guId = guId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
        public int getUserId1() {
		return userId1;
	}

	public void setUserId1(int userId) {
		this.userId1 = userId;
	}
           public int getUserId2() {
		return userId2;
	}

	public void setUserId2(int userId) {
		this.userId2 = userId;
	}
}
