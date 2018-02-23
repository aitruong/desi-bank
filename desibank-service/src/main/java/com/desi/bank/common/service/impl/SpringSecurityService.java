package com.desi.bank.common.service.impl;

public interface SpringSecurityService {

public	void updateLoginAttempt(String username, int attemptCount);

public int findAttemptsCountForUser(String username);

public void lockUser(String username);

}
