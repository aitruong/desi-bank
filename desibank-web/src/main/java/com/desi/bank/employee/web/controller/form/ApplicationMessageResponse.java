package com.desi.bank.employee.web.controller.form;

public class ApplicationMessageResponse {
	private String status;
	private String message;
	private String exception;
	private String url;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "ApplicationMessageResponse [status=" + status + ", message=" + message + ", exception=" + exception
				+ ", url=" + url + "]";
	}

}
