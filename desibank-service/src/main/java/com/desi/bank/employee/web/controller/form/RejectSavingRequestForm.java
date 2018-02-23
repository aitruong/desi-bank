package com.desi.bank.employee.web.controller.form;

public class RejectSavingRequestForm {
	private int csaid;
	private String customerName;
	private String email;
	private String reason;
	

	public int getCsaid() {
		return csaid;
	}

	public void setCsaid(int csaid) {
		this.csaid = csaid;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "RejectSavingRequestForm [customerName=" + customerName + ", email=" + email + ", reason=" + reason
				+ "]";
	}

}
