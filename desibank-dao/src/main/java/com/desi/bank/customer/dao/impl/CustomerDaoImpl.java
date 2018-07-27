package com.desi.bank.customer.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.desi.bank.common.advice.MessageLogger;
import com.desi.bank.common.dao.entity.Customer;
import com.desi.bank.common.dao.entity.CustomerAccountInfo;
import com.desi.bank.common.dao.entity.CustomerQuestionAnswer;
import com.desi.bank.common.dao.entity.CustomerSavingEntity;
import com.desi.bank.common.dao.entity.CustomerTransactionHistory;
import com.desi.bank.common.dao.entity.Login;
import com.desi.bank.common.dao.entity.PayeeInfo;
import com.desi.bank.common.dao.entity.SecurityQuestions;
import com.desi.bank.common.dao.entity.TransactionIdGenerator;
import com.desi.bank.customer.dao.CustomerDao;
import com.desi.bank.customer.web.controller.form.CustomerForm;
import com.desi.bank.customer.web.controller.form.MiniStatementVO;
import com.desi.bank.employee.dao.entity.RegistrationLinksEntity;
import com.desi.bank.exception.DesiBankException;
import com.spring.model.UserSessionVO;

@Repository("CustomerDaoImpl")
@Scope("singleton")
@Transactional
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

	@Autowired
	@Qualifier("sessionFactory")
	public void setSpringManageSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	
	
	@Override
	public String savingAccountRequest(CustomerSavingEntity customerSavingEntity) {
		super.getHibernateTemplate().save(customerSavingEntity);
		return "success";
	}

	
	@Transactional(propagation = Propagation.REQUIRED)
	// REQUIRED means this method always execute in transaction
	// This transaction only rollback for unchecked exception
	public String persistCustomer(CustomerForm customer) {
		System.out
				.println("Is spring transaction is enabled  = "
						+ TransactionSynchronizationManager
								.isActualTransactionActive());
		Customer pcustomer = BeanUtils.instantiate(Customer.class);
		BeanUtils.copyProperties(customer, pcustomer);
		Login login = new Login();
		CustomerQuestionAnswer questionanswer = new CustomerQuestionAnswer();
		login.setLoginid(customer.getUserid());
		login.setName(customer.getName());
		login.setPassword(customer.getPassword());
		
		if (customer.getRole()==null) {
			login.setRole("customer");
		} else {
			login.setRole(customer.getRole());
		}	
		login.setLocked("no");
		questionanswer.setQuestion(customer.getQuestion1());
		questionanswer.setAnswer(customer.getAnswer1());
		questionanswer.setUserid(customer.getUserid());
		getHibernateTemplate().save(pcustomer);
		getHibernateTemplate().save(login);
		getHibernateTemplate().save(questionanswer);
		CustomerQuestionAnswer questionanswer2 = new CustomerQuestionAnswer();
		questionanswer2.setQuestion(customer.getQuestion2());
		questionanswer2.setAnswer(customer.getAnswer2());
		questionanswer2.setUserid(customer.getUserid());
		getHibernateTemplate().save(questionanswer2);
		return "success"; // returning the controll
	}

	@Override
	public List<CustomerForm> findCustomers() {
		List<Customer> customers = (List<Customer>) getHibernateTemplate().find("from Customer");
		List<CustomerForm> customerForms = new ArrayList<CustomerForm>(
				customers.size());
		for (Customer customer : customers) {
			CustomerForm customerForm = new CustomerForm();
			BeanUtils.copyProperties(customer, customerForm);
			customerForms.add(customerForm);
		}
		return customerForms;
	}

	@Override
	public byte[] findImageByUserid(String userid) {
		List<Customer> customers = (List<Customer>) getHibernateTemplate().find("from Customer where userid='"+userid+"'");
		if(customers!=null && customers.size()==1){
			return customers.get(0).getImage();
		}else{
			return new byte[]{};
		}
	}
	
	//in HQL we write query on class and attributes of it instead of database table and columns..
	@Override
	@MessageLogger(message="executing method validateCustomer")
	public Login validateCustomer(String userid, String password) {
		//How many records will come in case of user is valid... 
		List<Login> logins = (List<Login>) getHibernateTemplate().find("from Login l where l.loginid = ? and l.password = ?",new Object[] { userid, password });
		List<Customer> customers = (List<Customer>) getHibernateTemplate().find("from Customer c where c.userid = ?", userid);
		String email=""; 
		if(customers!=null && customers.size()==1){
			email=customers.get(0).getEmail();
		}
		if(logins!=null && logins.size()>0){
			Login login=logins.get(0);
			login.setEmail(email);
			return login;
		}else{
			return null;
		}
	}

	@Override
	public boolean deleteCustomer(int id) {
		boolean del = false;
		Session session = getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			Customer customer = (Customer) session.load(Customer.class, id);
			session.delete(customer);
			session.getTransaction().commit();
			session.close();
			del = true;
		} catch (Exception e) {
			e.printStackTrace();
			del = false;
		}
		return del;
	}

	@Override
	public CustomerForm getCustomer(int id) {
		List<Customer> customers = (List<Customer>) getHibernateTemplate().find(
				"from Customer c where c.id = ?", id);
		CustomerForm customerForm = new CustomerForm();
		if (customers.size() == 1)
			BeanUtils.copyProperties(customers.get(0), customerForm);
		return customerForm;

	}

	@Override
	public CustomerForm getCustomer(String userid) {
		List<Customer> customers = (List<Customer>) getHibernateTemplate().find(
				"from Customer c where c.userid = ?", userid);
		CustomerForm customerForm = new CustomerForm();
		if (customers.size() == 1)
			BeanUtils.copyProperties(customers.get(0), customerForm);
		return customerForm;
	}

	@Override
	public String updateCustomer(CustomerForm customer) {
		Customer pcustomer = BeanUtils.instantiate(Customer.class);
		Customer dcustomer = getHibernateTemplate().get(Customer.class, customer.getId());
		BeanUtils.copyProperties(customer, pcustomer);
		pcustomer.setImage(dcustomer.getImage());
		pcustomer.setPhotoName(dcustomer.getPhotoName());
		pcustomer.setAccountNum(dcustomer.getAccountNum());
		getHibernateTemplate().update(pcustomer);

		return "success";
	}

	@Override
	public List<CustomerAccountInfo> getAccountDetails(String userid) {
		List<CustomerAccountInfo> acct = (List<CustomerAccountInfo>) getHibernateTemplate().find(
				"from CustomerAccountInfo a where a.customerId = ?", userid);
		return acct;
	}

	@Override
	public List<CustomerAccountInfo> getAccount(String accountNum) {
		List<CustomerAccountInfo> acct = (List<CustomerAccountInfo>) getHibernateTemplate().find(
				"from CustomerAccountInfo a where a.accountNumber = ?",
				accountNum);
		return acct;
	}

	@Override
	public List<MiniStatementVO> getTransactionDetails(String userid) {
		List<CustomerTransactionHistory> trans = (List<CustomerTransactionHistory>) getHibernateTemplate()
				.find("from CustomerTransactionHistory t where t.loginId = ?",
						userid);

		List<MiniStatementVO> miniStatementVO = new ArrayList<MiniStatementVO>(
				trans.size());
		for (CustomerTransactionHistory tx : trans) {
			MiniStatementVO miniStatement = BeanUtils
					.instantiate(MiniStatementVO.class);
			BeanUtils.copyProperties(tx, miniStatement);
			miniStatementVO.add(miniStatement);
		}
		return miniStatementVO;

	}

	

	/**
	 *  Logic to transfer money from one account to another account
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public String persistCustomerTransaction(
			CustomerTransactionHistory transaction) {
		CustomerAccountInfo custAcct = BeanUtils
				.instantiate(CustomerAccountInfo.class);

		//here we are fetching payee detail from the database as per his account number
		//assuming the payee account number must exist into the database
		List<CustomerAccountInfo> payeeAcctList = getAccount(transaction	.getToAccountNumber());
		//getting payee account detail
		CustomerAccountInfo payeeAcct = payeeAcctList.get(0);
		System.out.println("cust acct before:" + custAcct);
		System.out.println("payee acct before:" + payeeAcct);
		float newCustAvBal, newCustTBal, newPayeeAvBal, newPayeeTBal;
	
		//Fetching customer detail who is going to transfer the money
		custAcct =getAccount(transaction	.getFromAccountNumber()).get(0);

		if (custAcct.getAvBalance() >= transaction.getAmount()) {
			// update customer acct balance
			newCustAvBal = custAcct.getAvBalance() - transaction.getAmount();
			newCustTBal = custAcct.getTavBalance() - transaction.getAmount();
			custAcct.setAvBalance(newCustAvBal);
			custAcct.setTavBalance(newCustTBal);
			System.out.println("cust acct after:" + custAcct);
			
			//updating the account detail of customer who is transferring the money
			getHibernateTemplate().update(custAcct);
			
			// get payee account and update (add) amount to his balance.
			newPayeeAvBal = payeeAcct.getAvBalance() + transaction.getAmount();
			newPayeeTBal = payeeAcct.getTavBalance() + transaction.getAmount();
			payeeAcct.setAvBalance(newPayeeAvBal);
			payeeAcct.setTavBalance(newPayeeTBal);
			System.out.println("payee acct after:" + payeeAcct);
			
			//updating the account detail of payee whom  customer wants to transfer the money
			getHibernateTemplate().update(payeeAcct);
			
			// Add transaction id and persist transaction
			System.out.println("transactiob obj:" + transaction);
			long x = 1;
			
			///What is logic to generate transaction id........................
			List<TransactionIdGenerator> transactionIdGenList = (List<TransactionIdGenerator>) getHibernateTemplate()
					.find("from TransactionIdGenerator where id=?", x);
			TransactionIdGenerator transactionIdGenerator = transactionIdGenList.get(0);
			System.out
					.println("trasactionIdGenerator" + transactionIdGenerator);
			//what ever transaction id is in database we are increament it by one 
			long tx_id = (long) transactionIdGenerator.getTransactionId() + 1;
			
			
			//setting the transaction id for transaction history for the customer who is transferring the money
			transaction.setTransactionId(tx_id);
			transactionIdGenerator.setTransactionId(tx_id);
			
			
			//Here we are persisting 
			getHibernateTemplate().persist(transaction);
			//updating the transaction generator table
			getHibernateTemplate().update(transactionIdGenerator);
			return "success";
		} else {
			return "failed";
		}

	}
	
	@Override
	public String updatePassword(String userid,String newpassword) {
		List<Login> logins = (List<Login>) getHibernateTemplate().find("from Login l where l.loginid = ?", new Object[] { userid });
		if(logins!=null && logins.size()==1){
			Login login=logins.get(0);
			//Automatically dirty checking mechanism
			//updating the password
			login.setPassword(newpassword);
			//update last login time
			login.setLlt(new Timestamp(new Date().getTime()));
		}
		return "success";
	}

	@Override
	public UserSessionVO validateCustomerByUserId(String userid) {
		List<Login> logins = (List<Login>) getHibernateTemplate().find(
				"from Login l where l.loginid = ?", new Object[] { userid });
	
		List<Customer> customers = (List<Customer>) getHibernateTemplate().find("from Customer c where c.userid = ?", userid);
		String email=""; 
		if(customers!=null && customers.size()==1){
			email=customers.get(0).getEmail();
		}
		// UserSessionVO userSessionVO = new UserSessionVO();
		UserSessionVO userSessionVO = BeanUtils
				.instantiate(UserSessionVO.class);
		if (logins.size() == 1) {
			BeanUtils.copyProperties(logins.get(0), userSessionVO);
			userSessionVO.setEmail(email);
		}
		return userSessionVO;
	}

	@Override
	public List<SecurityQuestions> securityQns() {
		@SuppressWarnings("unchecked")
		List<SecurityQuestions> securityQuestions = (List<SecurityQuestions>)getHibernateTemplate().find(
				"from SecurityQuestions sq where sq.qid < 6");
		
		return securityQuestions;

	}
	
	@Override
	public List<SecurityQuestions> securityQns2() {
		@SuppressWarnings("unchecked")
		List<SecurityQuestions> securityQuestions = (List<SecurityQuestions>)getHibernateTemplate().find(
				"from SecurityQuestions sq where sq.qid > 5");
		
		return securityQuestions;

	}
	
	@Override
	public CustomerForm getCustomerDetail(String userid){
		List<Customer> detail = (List<Customer>)getHibernateTemplate().find(
				"from Customer cus where cus.userid=?",new Object[] { userid });
		
		CustomerForm customer = BeanUtils
				.instantiate(CustomerForm.class);
		if (detail.size() == 1) {
			BeanUtils.copyProperties(detail.get(0), customer);
		}
		return customer;
	}
	
	@Override
	public byte[] findPhoto(int id){
		List<Customer> customerList = (List<Customer>)getHibernateTemplate().find(
				"from Customer cus where cus.id=?",new Object[] { id }) ;
		if(customerList!=null&&customerList.size()==1){
			return customerList.get(0).getImage();
		}
		return null;
	}

	@Override
	public List<CustomerQuestionAnswer> getSecurityQns(String id) {
		List<CustomerQuestionAnswer> questionList = (List<CustomerQuestionAnswer>)getHibernateTemplate().find(
				"from CustomerQuestionAnswer cus where cus.userid=?",new Object[] { id }) ;
		return questionList;
		
	}

	@Override
	public String updatePass(String userid, String password, String qn1,
			String qn2, String ans1, String ans2) {
		// TODO Auto-generated method stub
		//Password passUpdate =new Password();
		List<CustomerQuestionAnswer> questionAns= (List<CustomerQuestionAnswer>)getHibernateTemplate().find(
				"from CustomerQuestionAnswer cus where cus.userid =? AND cus.question=? AND cus.answer=?",new Object[] {userid,qn1,ans1});
		if(questionAns!=null&&questionAns.size()==1){
			List<CustomerQuestionAnswer> questionAns2= (List<CustomerQuestionAnswer>)getHibernateTemplate().find(
					"from CustomerQuestionAnswer cus where cus.userid =? AND cus.question=? AND cus.answer=?",new Object[] {userid,qn2,ans2});
			
			if(questionAns2!=null&&questionAns2.size()==1){
				Login changelogin = getHibernateTemplate().get(Login.class, userid);
				changelogin.setPassword(password);
			}
			else{
				return "fail";
			}
		}
		else{
			return "fail";
		}
		
		
		return "success";
	}

	@Override
	public String findEmailByUserid(String userid) {
		List<Customer> customerList = (List<Customer>)getHibernateTemplate().find(
				"from Customer cus where cus.userid=?",new Object[] { userid }) ;
		if(customerList!=null  && customerList.size()==0){
			DesiBankException exception=new DesiBankException("email id does not exist  since userid "+userid+" is not there...");
			throw exception;
		}
		return customerList.get(0).getEmail();
	}
	
	@Override
	public CustomerSavingEntity findCustomerSavingEnquiryByEmail(String email) {
		List<CustomerSavingEntity> customerSavingList = (List<CustomerSavingEntity>)getHibernateTemplate().
				find("from CustomerSavingEntity cus where cus.email=?",new Object[] { email }) ;
		if(customerSavingList!=null  && customerSavingList.size()==0){
			CustomerSavingEntity entity=new CustomerSavingEntity();
			return entity;
		}
		return customerSavingList.get(0);
	}
	
	@Override
	public RegistrationLinksEntity findLinkDetailByCuid(String cuid) {
		List<RegistrationLinksEntity> registrationLinks = (List<RegistrationLinksEntity>)getHibernateTemplate().find("from RegistrationLinksEntity cus where cus.linkurl=?",new Object[] { cuid }) ;
		if(registrationLinks!=null  && registrationLinks.size()==0){
			RegistrationLinksEntity entity=new RegistrationLinksEntity();
			return entity;
		}
		return registrationLinks.get(0);
	}

	@Override
	public List<CustomerTransactionHistory> scheduledTransfer() {
		// TODO Auto-generated method stub
		List<CustomerTransactionHistory> scheduled = (List<CustomerTransactionHistory>) getHibernateTemplate().find(
				"from CustomerTransactionHistory trans where trans.transactionMode=?",new Object[] {"scheduled"});
		return scheduled;
	}

	@Override
	public String scheduledCustomerTransaction(
			CustomerTransactionHistory transaction) {
		getHibernateTemplate().persist(transaction);
		return null;
	}

	@Override
	public String updateScheduledTransaction(
			CustomerTransactionHistory transactionhistory) {
		
		CustomerTransactionHistory updateHistory = getHibernateTemplate().get(CustomerTransactionHistory.class, transactionhistory.getId());
		updateHistory.setTransactionMode(transactionhistory.getTransactionMode());
		
		long x = 1;
		List<TransactionIdGenerator> transactionIdGenList = (List<TransactionIdGenerator>) getHibernateTemplate()
				.find("from TransactionIdGenerator where id=?", x);
		TransactionIdGenerator transactionIdGenerator = transactionIdGenList
				.get(0);
		
		long tx_id = (long) transactionIdGenerator.getTransactionId() + 1;
		updateHistory.setTransactionId(tx_id);
		transactionIdGenerator.setTransactionId(tx_id);
		//
		List<CustomerAccountInfo> acctList = getAccountDetails(transactionhistory.getLoginId());
		CustomerAccountInfo custAcct = BeanUtils
				.instantiate(CustomerAccountInfo.class);

		List<CustomerAccountInfo> payeeAcctList = getAccount(transactionhistory
				.getToAccountNumber());
		CustomerAccountInfo payeeAcct = payeeAcctList.get(0);
		System.out.println("cust acct before:" + custAcct);
		System.out.println("payee acct before:" + payeeAcct);
		float newCustAvBal, newCustTBal, newPayeeAvBal, newPayeeTBal;
		if (acctList.size() == 1)
			custAcct = acctList.get(0);

		if (custAcct.getAvBalance() > transactionhistory.getAmount()) {
			// update customer acct balance
			newCustAvBal = custAcct.getAvBalance() - transactionhistory.getAmount();
			newCustTBal = custAcct.getTavBalance() - transactionhistory.getAmount();
			custAcct.setAvBalance(newCustAvBal);
			custAcct.setTavBalance(newCustTBal);
			System.out.println("cust acct after:" + custAcct);
			getHibernateTemplate().update(custAcct);
			// get payee account and update (add) amount to his balance.
			newPayeeAvBal = payeeAcct.getAvBalance() + transactionhistory.getAmount();
			newPayeeTBal = payeeAcct.getTavBalance() + transactionhistory.getAmount();
			payeeAcct.setAvBalance(newPayeeAvBal);
			payeeAcct.setTavBalance(newPayeeTBal);
			System.out.println("payee acct after:" + payeeAcct);
			getHibernateTemplate().update(payeeAcct);
		}
		return "success";
	}
}
