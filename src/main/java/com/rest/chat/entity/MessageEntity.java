    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.chat.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author raj
 */
@Entity
public class MessageEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
        @Id
	@Column(name = "fromuserid",unique = true, nullable = false)
	private int userId;
	
	@Column(name="groupName")
	private String groupName;
	
	@Column(name="message")
	private String message;
	
	@Column(name="stamptime")
	private String stampTime;

        @Column(name="guId")
	private String guId;
        
        @Column(name = "sessionId")
	private int sessionid;

	
        public void setsessionid(int Id) {
		this.sessionid = Id;
	}

	public int getsessionid() {
		return sessionid;
	}

	public void setGroupName(String grpName) {
		this.groupName = grpName;
	}
public String getGroupName() {
		return groupName;
	}
	

	public void setmessage(String msg) {
		this.message =msg;
	}
public String getmessage() {
		return message;
	}
	

	public void setstampTime(String time) {
		this.stampTime = time;
	}
public String getstampTime() {
		return stampTime;
	}
        

	public void setGuId(String guId) {
		this.guId = guId;
	}
        public String getGuId() {
		return guId;
	}
        public void setUserId(int userId) {
		this.userId = userId;
	}
        public int getUserId() {
		return userId;
	}
}
