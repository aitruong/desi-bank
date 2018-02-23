package com.desi.bank.customer.web.controller;

public class TransferMoneyForm {
	private String transactionMode;
	private String loginid;
	private String selectedPayeeName;
	private String fromAccount;
	private String accountType;
	private float amount;
	private String paymentOption;
	private String paymentDateTime;
	private String description;

	public String getTransactionMode() {
		return transactionMode;
	}

	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}

	

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public String getSelectedPayeeName() {
		return selectedPayeeName;
	}

	public void setSelectedPayeeName(String selectedPayeeName) {
		this.selectedPayeeName = selectedPayeeName;
	}

	public String getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getPaymentOption() {
		return paymentOption;
	}

	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption;
	}

	public String getPaymentDateTime() {
		return paymentDateTime;
	}

	public void setPaymentDateTime(String paymentDateTime) {
		this.paymentDateTime = paymentDateTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "TransferMoneyForm [userid=" + loginid + ", selectedPayeeName=" + selectedPayeeName + ", fromAccount="
				+ fromAccount + ", accountType=" + accountType + ", amount=" + amount + ", paymentOption="
				+ paymentOption + ", paymentDateTime=" + paymentDateTime + ", description=" + description + "]";
	}



}
