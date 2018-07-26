package com.desi.bank.employee.web.controller.form;

//VO  =DTO - Data Transfer Object   
public class CustomerAccoutRequestVO {
	private String name;
	private String email;
	private String userid;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "CustomerAccoutRequestVO [name=" + name + ", email=" + email + ", userid=" + userid + "]";
	}

}
