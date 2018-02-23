package com.desi.bank.rest.api.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ApplicationStatusMessage {
	private String statusCode;
	private String message;
	private String status;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ApplicationStatusMessage [statusCode=" + statusCode + ", message=" + message + ", status=" + status
				+ "]";
	}

}
