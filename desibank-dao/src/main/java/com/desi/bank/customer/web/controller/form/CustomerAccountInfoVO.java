package com.desi.bank.customer.web.controller.form;


import java.util.Date;

/**
 * 
 * @author nagendra.yadav
 * This contains Customer Details
 * 
 * 
 */
public class CustomerAccountInfoVO {

	private long id;
	// this is loginId
	private String customerId;
	private String accountNumber;
	private String currency;
	private String branch;
	private long tavBalance;
	private long avBalance;
	private Date StatusAsOf;
	private String payeeName;
	private String accountType;
	
	
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	

	public long getTavBalance() {
		return tavBalance;
	}

	public void setTavBalance(long tavBalance) {
		this.tavBalance = tavBalance;
	}

	public long getAvBalance() {
		return avBalance;
	}

	public void setAvBalance(long avBalance) {
		this.avBalance = avBalance;
	}

	public Date getStatusAsOf() {
		return StatusAsOf;
	}

	public void setStatusAsOf(Date statusAsOf) {
		StatusAsOf = statusAsOf;
	}


}

