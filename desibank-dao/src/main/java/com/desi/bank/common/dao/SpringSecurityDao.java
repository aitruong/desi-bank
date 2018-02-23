package com.desi.bank.common.dao;

public interface SpringSecurityDao {
	public int findAttemptsCountForUser(String username);
	public 	void updateLoginAttempt(String username, int attemptCount);
	public void lockUser(String username);
}
