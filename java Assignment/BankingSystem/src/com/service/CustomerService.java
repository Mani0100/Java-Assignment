package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.CustomerDao;
import com.dao.CustomerDaoImpl;
import com.dto.BankDto;
import com.exception.InsufficientFundException;
import com.exception.InvalidAccountException;

public class CustomerService {
	CustomerDao cdi=new CustomerDaoImpl();
	public void getAccountBalance(String accountNumber) throws SQLException {
		cdi.getAccountBalance(accountNumber);
	}
	public void depositAmount(String accountNum, float amount) throws SQLException, InvalidAccountException {
		cdi.depositAmount(accountNum,amount);
	}
	public void withdraw(String accountNumb, float withdrawnAmount) throws SQLException, InsufficientFundException {
		cdi.withdraw(accountNumb,withdrawnAmount);
	}
	public void transfer(String senderAcc, String receiverAcc, float transfered) throws SQLException, InsufficientFundException {
		cdi.transfer(senderAcc,receiverAcc,transfered);
	}
	public List<BankDto> getAccountDetails(String accNumber) throws SQLException {
		return cdi.getAccountDetails(accNumber);
	}
	
}
