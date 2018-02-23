package com.desi.bank.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.desi.bank.common.dao.SpringSecurityDao;

@Service("SpringSecurityServiceImpl")
public class SpringSecurityServiceImpl  implements SpringSecurityService {
	
	@Autowired
	@Qualifier("SpringSecurityDaoImpl")
	private SpringSecurityDao springSecurityDao;
	
	@Override
	public void updateLoginAttempt(String username, int attemptCount) {
		springSecurityDao.updateLoginAttempt(username, attemptCount);
	}	
	
	@Override
	public int findAttemptsCountForUser(String username){
		int result=springSecurityDao.findAttemptsCountForUser(username);
		return result;
	}
	
	@Override
	public void lockUser(String username){
		springSecurityDao.lockUser(username);
	}

}
