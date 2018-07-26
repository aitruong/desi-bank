package com.desi.bank.employee.web.controller.form;

public class CustomerAccountRegistrationVO {
	private String name;
	private String userid;
	private String account;
	private String type;
	private String avgbalance;
	private String branch;
	private String currency;
	private String image;
	private String appRef;
	private String bankEmail;
	private String to;
	private String subject;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAvgbalance() {
		return avgbalance;
	}

	public void setAvgbalance(String avgbalance) {
		this.avgbalance = avgbalance;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAppRef() {
		return appRef;
	}

	public void setAppRef(String appRef) {
		this.appRef = appRef;
	}

	public String getBankEmail() {
		return bankEmail;
	}

	public void setBankEmail(String bankEmail) {
		this.bankEmail = bankEmail;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "CustomerAccountRegistrationVO [name=" + name + ", userid=" + userid + ", account="
				+ account + ", type=" + type + ", avgbalance=" + avgbalance + ", branch=" + branch + ", currency="
				+ currency + ", image=" + image + ", appRef=" + appRef + ", bankEmail=" + bankEmail + ", to=" + to
				+ ", subject=" + subject + "]";
	}

}
