package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.dto.BankDto;
import com.exception.InsufficientFundException;
import com.exception.InvalidAccountException;

public interface CustomerDao {

	
	public void getAccountBalance(String accountNumber) throws SQLException;

	public void depositAmount(String accountNum, float amount) throws SQLException, InvalidAccountException;

	public void withdraw(String accountNumb, float withdrawnAmount) throws SQLException, InsufficientFundException;

	public void transfer(String senderAcc, String receiverAcc, float transfered) throws SQLException, InsufficientFundException;

	public List<BankDto> getAccountDetails(String accNumber) throws SQLException;
}
