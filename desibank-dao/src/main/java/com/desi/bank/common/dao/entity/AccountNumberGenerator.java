package com.desi.bank.common.dao.entity;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="account_number_generator_tbl")
public class AccountNumberGenerator implements Serializable{
	
	private long id;
	private long accountNumber;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	@Override
	public String toString() {
		return "AccountNumberGenerator [id=" + id + ", accountNumber="
				+ accountNumber + "]";
	}
}

