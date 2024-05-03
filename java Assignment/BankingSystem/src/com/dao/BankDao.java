package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.model.Bank;
import com.model.Customer;

public interface BankDao {
	
	public void createBankAccount(int customer_id,String accNum, String type, float balance) throws SQLException;

	public int createCustomerAccount(Customer customer) throws SQLException;

	public List<Bank> listAccounts() throws SQLException;

	public void calculateInterest(float bal, float rate);

}
