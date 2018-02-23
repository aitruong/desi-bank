package com.desi.bank.customer.soap.service.fault;

public class CustomerSoapFault {

	private String mcode;
	private String message;
	private String cdate;

	public String getMcode() {
		return mcode;
	}

	public void setMcode(String mcode) {
		this.mcode = mcode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	@Override
	public String toString() {
		return "CustomerSoapFault [mcode=" + mcode + ", message=" + message + ", cdate=" + cdate + "]";
	}

}
