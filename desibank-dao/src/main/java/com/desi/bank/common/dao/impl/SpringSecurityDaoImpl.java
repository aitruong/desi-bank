package com.desi.bank.common.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.desi.bank.common.dao.SpringSecurityDao;
import com.desi.bank.common.dao.entity.Login;

@Repository("SpringSecurityDaoImpl")
@Scope("singleton")
public class SpringSecurityDaoImpl extends HibernateDaoSupport implements SpringSecurityDao {
	
	@Autowired
	@Qualifier("sessionFactory")
	public void setSpringManageSessionFactory(SessionFactory sessionFactory){
	    super.setSessionFactory(sessionFactory);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void lockUser(String username) {
		Login login = super.getHibernateTemplate().load(Login.class, username);
		login.setLocked("yes");
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void updateLoginAttempt(String username, int attemptCount) {
		Login login = super.getHibernateTemplate().load(Login.class, username);
		login.setNoOfAttempt(attemptCount);
	}

	/**
	 * 
	 * @param username
	 * @return -1 means username does not exist into the database else user
	 *         exists into the database
	 */
	@Override
	public int findAttemptsCountForUser(String username) {
		// load , get
		Login user = (Login) getHibernateTemplate().get(Login.class, username);
		if (user == null) {
			return -1;
		}
		return user.getNoOfAttempt();
	}

}
