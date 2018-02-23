package com.desi.bank.common.dao.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer_security_questions_tbl")
public class SecurityQuestions {
	
	private int qid;
	private String questions;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public String getQuestions() {
		return questions;
	}
	public void setQuestions(String questions) {
		this.questions = questions;
	}
	@Override
	public String toString() {
		return "SecurityQuestions [qid=" + qid + ", questions=" + questions
				+ "]";
	}
	
	
	
}
