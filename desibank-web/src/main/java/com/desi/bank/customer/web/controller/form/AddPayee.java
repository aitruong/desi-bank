package com.desi.bank.customer.web.controller.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author nagendra
 *	
 */
public class AddPayee {

	@NotEmpty(message = "Please enter the payee's account number.")
	private String payeeAccountNo;

	//@NotEmpty(message = "Please enter the payee's account number.")
	private String confirmPayeeAccountNo;

	@NotEmpty(message = "Please enter the payee's registered name.")
	private String payeeName;

	private String payeeNickName;

	@NotEmpty(message = "Please enter your registered email id.")
	@Email
	private String email;

	public String getPayeeAccountNo() {
		return payeeAccountNo;
	}

	public void setPayeeAccountNo(String payeeAccountNo) {
		this.payeeAccountNo = payeeAccountNo;
	}

	public String getConfirmPayeeAccountNo() {
		return confirmPayeeAccountNo;
	}

	public void setConfirmPayeeAccountNo(String confirmPayeeAccountNo) {
		this.confirmPayeeAccountNo = confirmPayeeAccountNo;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public String getPayeeNickName() {
		return payeeNickName;
	}

	public void setPayeeNickName(String payeeNickName) {
		this.payeeNickName = payeeNickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "AddPayee [payeeAccountNo=" + payeeAccountNo + ", confirmPayeeAccountNo=" + confirmPayeeAccountNo
				+ ", payeeName=" + payeeName + ", payeeNickName=" + payeeNickName + ", email=" + email + "]";
	}

}
