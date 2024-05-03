package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.BankDao;
import com.dao.BankDaoImpl;
import com.model.Bank;
import com.model.Customer;

public class BankService {
	BankDao bankDao=new BankDaoImpl();
	
	public void createBankAccount(int customer_id,String accNum, String type, float balance) throws SQLException {
		bankDao.createBankAccount(customer_id,accNum,type,balance);
	}
	public int createCustomerAccount(Customer customer) throws SQLException {
		return bankDao.createCustomerAccount(customer);		
	}
	public List<Bank> listAccounts() throws SQLException {
		return bankDao.listAccounts();
	}
	public void calculateInterest(float bal, float rate) {
		bankDao.calculateInterest(bal,rate);
	}

}