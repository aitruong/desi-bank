package com.desi.bank.customer.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.desi.bank.common.dao.entity.PayeeInfo;
import com.desi.bank.customer.dao.CustomerPayeeDao;

/**
 * 
 * @author VC1
 * @since 23-FEB-2018
 */
@Repository("ICustomerPayeeDao")
@Transactional
public class ICustomerPayeeDao extends HibernateDaoSupport  implements CustomerPayeeDao{

	@Autowired
		@Qualifier("sessionFactory")
		public void setSpringManageSessionFactory(SessionFactory sessionFactory) {
			super.setSessionFactory(sessionFactory);
		}
		
	@Override
	public String addPayee(PayeeInfo payee) {
		getHibernateTemplate().persist(payee);
		return "success";
	}

	@Override
	public List<PayeeInfo> getPayeeList(String loginid) {
		List<PayeeInfo> payeeList = (List<PayeeInfo>) getHibernateTemplate().find(
				"from PayeeInfo p where p.customerId = ?", loginid);
		return payeeList;
	}
	
}
