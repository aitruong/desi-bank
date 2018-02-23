package com.desi.bank.common.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author This is pojo/model which is used to carry data from view to
 *         model/controller
 * 
 */
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "customer_tbl")
public class Customer {

	private int id;
	private String userid;
	private String password;
	private String name;
	private String address;
	private String email;
	private String mobile;
	private String ssn;
	private int age;
	private String gender;
	private String dob;
	private String father;
	private String qualification;
	private String jobTitle;
	private String approved;
	// private String locked;
	private String accountNum;
	private String photoName;
	private byte[] image;

	
	
    @Column(columnDefinition="longblob")
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getFather() {
		return father;
	}

	public void setFather(String father) {
		this.father = father;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	/*
	 * public String getLocked() { return locked; }
	 * 
	 * public void setLocked(String locked) { this.locked = locked; }
	 */
	/*
	 * @SequenceGenerator(name = "entity1Seq", sequenceName="ENTITY1_SEQ",
	 * allocationSize=1)
	 * 
	 * @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
	 * "entity1Seq")
	 */
	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	@Override
	public String toString() {
		return "Customer [userid=" + userid + ", password=" + password
				+ ", name=" + name + ", address=" + address + ", email="
				+ email + ", mobile=" + mobile + ", ssn=" + ssn + ", age="
				+ age + ", gender=" + gender + ", dob=" + dob + ", father="
				+ father + ", qualification=" + qualification + ", jobTitle="
				+ jobTitle + "]";
	}

}
