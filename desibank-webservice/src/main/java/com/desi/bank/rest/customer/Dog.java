package com.desi.bank.rest.customer;

public class Dog {
	private String did;
	private String name;
	private String color;
	private String email;
	
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Dog [name=" + name + ", color=" + color + ", email=" + email + "]";
	}
	
	
}
