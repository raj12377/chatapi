package com.rest.chat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ChatRoomEntity")
public class Groupsmembers {

	
        @Column(name="guId")
	private String guId;
	@Id
        @Column(name="groupName")
	private String groupName;
        @Column(name="members")
        private String members;
       
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
       

	public void setMembers(String member) {
		this.members = member;
	}
        public String getMembers() {
		return members;
	}
        
}
