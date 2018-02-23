package com.desi.bank.rest.api.vo;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author nagendra
 *   
 */

@XmlRootElement
@JsonInclude(Include.NON_NULL)
public class CustomerSavingVO {
	
    private int csaid;
    @JsonProperty("cname")
	private String name;
	private String email;
	private String mobile;
	private String location;
	//@JsonSerialize(using=DateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date doa;
	private String status;
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCsaid() {
		return csaid;
	}

	public void setCsaid(int csaid) {
		this.csaid = csaid;
	}

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

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
		return "CustomerSavingForm [name=" + name + ", email=" + email + ", mobile=" + mobile + ", location=" + location
				+ ", doa=" + doa + "]";
	}

}
