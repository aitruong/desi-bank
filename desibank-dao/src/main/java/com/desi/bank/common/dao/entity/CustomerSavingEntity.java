package com.desi.bank.common.dao.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author nagendra
 *   
 */

@Entity
@Table(name="customer_saving_enquiry_tbl")
public class CustomerSavingEntity {

	private int csaid;
	private String name;
	private String email;
	private String mobile;
	private String location;
	private String status;
	private Date doa;
	
	@Column(length=20)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getCsaid() {
		return csaid;
	}

	public void setCsaid(int csaid) {
		this.csaid = csaid;
	}

	@Column(length=100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length=100)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(length=15)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(length=100)
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDoa() {
		return doa;
	}

	public void setDoa(Date doa) {
		this.doa = doa;
	}

	@Override
	public String toString() {
		return "CustomerSavingEntity [csaid=" + csaid + ", name=" + name + ", email=" + email + ", mobile=" + mobile
				+ ", location=" + location + ", doa=" + doa + "]";
	}

}

