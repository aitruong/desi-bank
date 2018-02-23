package com.desi.bank.common.dao.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer_question_answer_tbl")
public class CustomerQuestionAnswer {

	private int id;
	private String question;
	private String answer;
	private String userid;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	
	@Override
	public String toString() {
		return "CustomerQuestionAnswer [question=" + question + ", answer="
				+ answer + ", userid=" + userid + "]";
	}
	
	
	
}
