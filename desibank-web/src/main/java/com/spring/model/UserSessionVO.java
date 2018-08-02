package com.spring.model;

import java.sql.Timestamp;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
//when this object UserSessionVO is added into the session? when user logs in the application
// session.setAttribute(DesiBankConstant.USER_SESSION_DATA, userSessionVO);
public class UserSessionVO  implements HttpSessionBindingListener{
	private String loginid;
	private String name;
	private String password;
	private String role;
	private String locked;
	private String approved;
	private String email;
	private Timestamp llt;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getLlt() {
		return llt;
	}

	public void setLlt(Timestamp llt) {
		this.llt = llt;
	}

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public String getLocked() {
		return locked;
	}

	public void setLocked(String locked) {
		this.locked = locked;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		 //When this method is called...................?
		 //the moment UserSessionVO is added into the session
		 
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}

}
