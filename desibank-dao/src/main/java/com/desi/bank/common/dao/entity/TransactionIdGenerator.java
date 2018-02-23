package com.desi.bank.common.dao.entity;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="transaction_id_generator_tbl")
public class TransactionIdGenerator implements Serializable{
	
	private long id;
	private long transactionId;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}


	@Override
	public String toString() {
		return "TransactionIdGenerator [id=" + id + ", transactionId="
				+ transactionId + "]";
	}
	
	
}

