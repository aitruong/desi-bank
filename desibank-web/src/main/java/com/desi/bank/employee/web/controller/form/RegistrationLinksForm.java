package com.desi.bank.employee.web.controller.form;

import java.sql.Timestamp;

public class RegistrationLinksForm {

	private int lno;
	private String email;
	private String linkurl;
	private Timestamp doe;
	private Timestamp linkexpiredate;
	private int exphrs;
	private String comment;

	public int getLno() {
		return lno;
	}

	public void setLno(int lno) {
		this.lno = lno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLinkurl() {
		return linkurl;
	}

	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}

	public Timestamp getDoe() {
		return doe;
	}

	public void setDoe(Timestamp doe) {
		this.doe = doe;
	}

	public Timestamp getLinkexpiredate() {
		return linkexpiredate;
	}

	public void setLinkexpiredate(Timestamp linkexpiredate) {
		this.linkexpiredate = linkexpiredate;
	}

	public int getExphrs() {
		return exphrs;
	}

	public void setExphrs(int exphrs) {
		this.exphrs = exphrs;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "RegistrationLinksForm [lno=" + lno + ", email=" + email + ", linkurl=" + linkurl + ", doe=" + doe
				+ ", linkexpiredate=" + linkexpiredate + ", exphrs=" + exphrs + ", comment=" + comment + "]";
	}

}
