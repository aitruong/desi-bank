package com.spring.model;

public class Password {
	private String answer1;
	private String answer2;
	private String password;
	public String getAnswer1() {
		return answer1;
	}
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}
	public String getAnswer2() {
		return answer2;
	}
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Password [answer1=" + answer1 + ", answer2=" + answer2
				+ ", password=" + password + "]";
	}
	
	
}
