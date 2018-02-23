package com.desi.bank.employee.dao.entity;

import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="registation_links_tbl")
public class RegistrationLinksEntity {

	private int lno;
	private String email;
	private String linkurl;
	private Timestamp doe;
	private Timestamp linkexpiredate;
	private int exphrs;
	private String comment;

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getLno() {
		return lno;
	}

	public void setLno(int lno) {
		this.lno = lno;
	}

	@Column(length=100)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	@Column(length=200)
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

	@Column(length=200)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "RegistrationLinksEntity [lno=" + lno + ", email=" + email + ", linkurl=" + linkurl + ", doe=" + doe
				+ ", linkexpiredate=" + linkexpiredate + ", exphrs=" + exphrs + ", comment=" + comment + "]";
	}

}
