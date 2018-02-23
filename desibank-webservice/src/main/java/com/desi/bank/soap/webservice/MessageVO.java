package com.desi.bank.soap.webservice;

public class MessageVO {
	private String errorCode;
	private String emessage;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getEmessage() {
		return emessage;
	}

	public void setEmessage(String emessage) {
		this.emessage = emessage;
	}

	@Override
	public String toString() {
		return "MessageVO [errorCode=" + errorCode + ", emessage=" + emessage + "]";
	}

}
