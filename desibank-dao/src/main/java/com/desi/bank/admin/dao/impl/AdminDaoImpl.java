package com.desi.bank.admin.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.desi.bank.admin.dao.AdminDao;
import com.desi.bank.common.dao.entity.AccountNumberGenerator;
import com.desi.bank.common.dao.entity.Customer;
import com.desi.bank.common.dao.entity.CustomerAccountInfo;
import com.desi.bank.common.dao.entity.Login;
import com.desi.bank.common.dao.entity.TransactionIdGenerator;
import com.desi.bank.constant.DesiBankConstant;
import com.desi.bank.customer.dao.CustomerDao;
import com.desi.bank.customer.web.controller.form.CustomerForm;

@Repository
@Transactional
public class AdminDaoImpl extends HibernateDaoSupport implements AdminDao {
	
	/**
	 * Initiate Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(AdminDaoImpl.class);
	
	private static final int LIMITITEMSPERPAGE = 3;
	
	public AdminDaoImpl(){
		if(logger.isDebugEnabled()){
			logger.debug("_)@)@@@@@@@@@@@AdminDaoImpl@@@@@@@@@@@@@@@@");
			logger.debug("_)@)@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}
	}
	
	@Autowired
	@Qualifier("sessionFactory")
	public void setSpringManageSessionFactory(SessionFactory sessionFactory){
	    super.setSessionFactory(sessionFactory);
	}
	
	@Autowired
	@Qualifier("CustomerDaoImpl")
	private CustomerDao customerDao;
	
	@Override
	public List<CustomerForm> listUnapprovedCustomers() {
		List<Customer> customers=(List<Customer>) getHibernateTemplate().find("from Customer c where c.approved='0' ");
		List<CustomerForm> customerForms=new ArrayList<CustomerForm>(customers.size());
		for(Customer customer:customers) {
			CustomerForm customerForm=new CustomerForm();
			BeanUtils.copyProperties(customer, customerForm);
			customerForms.add(customerForm);
		}
		return customerForms;
	}
	
	@Override
	public boolean addDefaultAccountNumber(long id){
		AccountNumberGenerator accountNumberGenerator=getHibernateTemplate().get(AccountNumberGenerator.class, id);
		if(accountNumberGenerator==null){
			AccountNumberGenerator accountNumberGen=new AccountNumberGenerator();
			accountNumberGen.setAccountNumber(DesiBankConstant.DEFAULT_ACCOUNT_NUMBER);
			getHibernateTemplate().save(accountNumberGen);
			return true;
		}
		return false;
	}

	@Override
	public boolean addDefaultTransactionID(long id){
		TransactionIdGenerator transactionIdGen=getHibernateTemplate().get(TransactionIdGenerator.class, id);
		if(transactionIdGen==null){
			TransactionIdGenerator transactionIdGenerator=new TransactionIdGenerator();
			transactionIdGenerator.setTransactionId(DesiBankConstant.DEFAULT_TX_ID);
			getHibernateTemplate().save(transactionIdGenerator);
			return true;
		}
		return false;
	}
	

	@Override
	public String approveCustomers(String[] id) {
		Customer pcust=BeanUtils.instantiate(Customer.class);
		AccountNumberGenerator acn=getHibernateTemplate().get(AccountNumberGenerator.class, 1L);
		long  currentAccountNumDB=acn.getAccountNumber();
		   for(String x:id) {
			  
			   if(x==null)
				  continue;
			  System.out.println("id " +x);
			  int uid = Integer.parseInt(x) ;
			  List<Customer> customers=(List<Customer>) getHibernateTemplate().find("from Customer where id = ?", uid);
			 if(customers.size()==1){
				 pcust = customers.get(0);
			 }	
			 currentAccountNumDB++;
			 CustomerAccountInfo customerAccountInfo=new CustomerAccountInfo();
			 System.out.println(DesiBankConstant.PREFIX_ACCOUNT_NUMBER+""+currentAccountNumDB);
			 customerAccountInfo.setAccountNumber(DesiBankConstant.PREFIX_ACCOUNT_NUMBER+""+currentAccountNumDB);
			 customerAccountInfo.setAvBalance(1000);
			 customerAccountInfo.setBranch("CA");
			 customerAccountInfo.setCurrency(DesiBankConstant.CURRENCY);
			 customerAccountInfo.setCustomerId(pcust.getUserid());
			 customerAccountInfo.setPayeeName(pcust.getName());
			 customerAccountInfo.setStatusAsOf(new Date());
			 customerAccountInfo.setTavBalance(1000);
			 
			  pcust.setAccountNum(DesiBankConstant.PREFIX_ACCOUNT_NUMBER+currentAccountNumDB+"")	;	   
			   pcust.setApproved("1");
			  getHibernateTemplate().update(pcust);
			  getHibernateTemplate().save(customerAccountInfo); 			 
			  
		   }
		 
		   //Finally updating current account number into table;
		  acn.setAccountNumber(currentAccountNumDB);
		  getHibernateTemplate().update(acn);
			
		return "success";
	}

	@Override
	public List<CustomerForm> listLockedCustomers() {
		Login login = BeanUtils.instantiate(Login.class);
		List<Login> unlockCust = (List<Login>) getHibernateTemplate().find("from Login where locked = ?", "yes");
		System.out.println(unlockCust);
		List<CustomerForm> customerForms=new ArrayList<CustomerForm>();
		//CustomerForm customerForm=new CustomerForm();
		for (Login ln : unlockCust) {
		List<Customer> customers=(List<Customer>) getHibernateTemplate().find("from Customer c where c.userid= ? ",ln.getLoginid());
		System.out.println(customers);
		//customerForms=new ArrayList<CustomerForm>(customers.size());
		//CustomerForm customerForm=new CustomerForm();
		for(Customer customer:customers) {
			CustomerForm customerForm=new CustomerForm();
		BeanUtils.copyProperties(customer, customerForm);
		System.out.println(customerForm);
		customerForms.add(customerForm);
		}
		/*for(Customer customer:customers) {
			CustomerForm customerForm=new CustomerForm();
			BeanUtils.copyProperties(customer, customerForm);
			customerForms.add(customerForm);
		}*/
		System.out.println(customerForms);
		}
		System.out.println(customerForms);
		return customerForms;
	}

	@Override
	public String lockCustomers(String[] id) {
Customer pcust=BeanUtils.instantiate(Customer.class);
Login login = BeanUtils.instantiate(Login.class);
		   for(String x:id) {
			   if(x==null)
				  continue;
			  System.out.println("id " +x);
			  int uid = Integer.parseInt(x) ;
			  List<Customer> customers=(List<Customer>) getHibernateTemplate().find("from Customer where id = ?", uid);
			 if(customers.size()==1){
				 pcust = customers.get(0);
			 }	
			List<Login> ln = (List<Login>) getHibernateTemplate().find("from Login where loginid = ?", pcust.getUserid());
			if(ln.size()==1){
				 login = ln.get(0);
			 }	
			login.setLocked("yes");
			 getHibernateTemplate().update(login);
			 
			  
		   }	
		return "success";	
		}

	@Override
	public List<CustomerForm> listUnlockedCustomers() {
		Login login = BeanUtils.instantiate(Login.class);
		List<Login> unlockCust = (List<Login>) getHibernateTemplate().find("from Login where locked = ?", "no");
		System.out.println(unlockCust);
		List<CustomerForm> customerForms=new ArrayList<CustomerForm>();
		for (Login ln : unlockCust) {
			List<Customer> customers=(List<Customer>) getHibernateTemplate().find("from Customer c where c.userid= ? ",ln.getLoginid());
			System.out.println(customers);
			for(Customer customer:customers) {
				CustomerForm customerForm=new CustomerForm();
				BeanUtils.copyProperties(customer, customerForm);
				System.out.println(customerForm);
				customerForms.add(customerForm);
			}
		}
		return customerForms;
	}
	/*

	public List<Video> listVideosByKids(int page) {

	    Query query = sessionFactory.getCurrentSession().createQuery("from Video where type=1");
	    query.setMaxResults(LIMITITEMSPERPAGE);
	    query.setFirstResult(page * LIMITITEMSPERPAGE);

	    return (List<Video>) query.list();

	}*/
	@Override
	public List<CustomerForm> listPaginatedCustomers(int page) { 
		Login login = BeanUtils.instantiate(Login.class);
		List<Login> unlockCust = (List<Login>) getHibernateTemplate().find("from Login where locked = ?", "no");
		
		List<CustomerForm> customerFormList=new  ArrayList<CustomerForm>();
		
		List<Customer> customerDlist=new ArrayList<Customer>();
		
		for (Login ln : unlockCust) {
		
			Session lsf=super.getHibernateTemplate().getSessionFactory().openSession();
			
			Query query = lsf.createQuery("from Customer c where c.userid= ? ").setParameter(0,ln.getLoginid());
			
			//query.setMaxResults(LIMITITEMSPERPAGE);
		    //query.setFirstResult(page * LIMITITEMSPERPAGE);
		  /*List<Customer> customers=getHibernateTemplate().find("from Customer c where c.userid= ? ",ln.getLoginid());
			for(Customer customer:customers) {
				CustomerForm customerForm=new CustomerForm();
			BeanUtils.copyProperties(customer, customerForm);
			customerForms.add(customerForm);
			}*/
		    List<Customer> dcustomerList=query.list();
		    customerDlist.add(dcustomerList.get(0));
		    //System.out.println(dcustomerList);
		}
		
		return customerFormList;
	}
	
	
	
	
	@Override
	public String unlockCustomers(String[] id) {
		Customer pcust=BeanUtils.instantiate(Customer.class);
		Login login = BeanUtils.instantiate(Login.class);
				   for(String x:id) {
					   if(x==null)
						  continue;
					  System.out.println("id " +x);
					  int uid = Integer.parseInt(x) ;
					  List<Customer> customers=(List<Customer>) getHibernateTemplate().find("from Customer where id = ?", uid);
					 if(customers.size()==1){
						 pcust = customers.get(0);
					 }	
					List<Login> ln = (List<Login>) getHibernateTemplate().find("from Login where loginid = ?", pcust.getUserid());
					if(ln.size()==1){
						 login = ln.get(0);
					 }	
					login.setLocked("no");
					 getHibernateTemplate().update(login);
					 
					  
				   }	
				return "success";	
	}

	@Override
	public List<CustomerForm> showCustomers() {
		List<Customer> customers=(List<Customer>) getHibernateTemplate().find("from Customer");
		List<CustomerForm> customerForms=new ArrayList<CustomerForm>(customers.size());
		for(Customer customer:customers) {
			CustomerForm customerForm=new CustomerForm();
			BeanUtils.copyProperties(customer, customerForm);
			customerForms.add(customerForm);
		}
		return customerForms;
	}

	@Override
	public String changePassword(String userid, String password) {
		Login login = BeanUtils.instantiate(Login.class);
		List<Login> ln = (List<Login>) getHibernateTemplate().find("from Login where loginid = ?",userid );
		login = ln.get(0);
		login.setPassword(password);
		getHibernateTemplate().update(login);
		return "success";
	}

	@Override
	public List<CustomerForm> searchUnapprovedCustomers(String keyword) {
		List<Customer> customers=(List<Customer>) getHibernateTemplate().find("from Customer c where c.approved='0' AND c.name=?",keyword);
		List<CustomerForm> customerForms=new ArrayList<CustomerForm>(customers.size());
		for(Customer customer:customers) {
			CustomerForm customerForm=new CustomerForm();
			BeanUtils.copyProperties(customer, customerForm);
			customerForms.add(customerForm);
		}
		return customerForms;
	}

}
