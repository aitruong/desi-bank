 package com.desi.bank.email.service;

import java.io.Serializable;

/**
 * 
 * @author nagendra
 *
 */
public class TestVO implements Serializable {
	private String name;
	private String to;
	private String from;
	private String subject;
	private String message;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "TestVO [name=" + name + ", to=" + to + ", from=" + from + ", subject=" + subject + ", message="
				+ message + "]";
	}

}
