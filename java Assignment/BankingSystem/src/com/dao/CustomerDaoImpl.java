package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.BankDto;
import com.exception.InsufficientFundException;
import com.exception.InvalidAccountException;
import com.model.Bank;
import com.utility.DBConnection;

public class CustomerDaoImpl implements CustomerDao{

	@Override
	public void getAccountBalance(String accountNumber) throws SQLException {
		Connection conn = DBConnection.dbConnect();
		List<Bank> l = new ArrayList<>();
		String sql = "select * from account " + "where account_number= ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, accountNumber);
		ResultSet rst = pstmt.executeQuery();
		if (rst.next()) {
			int cid = rst.getInt("customer_id");
			String number = rst.getString("account_number");
			String type = rst.getString("account_type");
			float balance = rst.getFloat("account_balance");

			System.out.println("**********Account Details**********");
			System.out.println("Account Number: " + number +"\nAccount Balance: " + balance);
		}
		else
			throw new NullPointerException("Invalid Account Number Given");
		DBConnection.dbclose();;
	}

	@Override
	public void depositAmount(String accountNum, float amount) throws SQLException, InvalidAccountException {
		Connection conn = DBConnection.dbConnect();
		List<Bank> l = new ArrayList<>();
		String sql = "UPDATE account " + "SET account_balance = account_balance + ? " + "WHERE account_number= ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setFloat(1, amount);
		pstmt.setString(2, accountNum);
		int a = pstmt.executeUpdate();
		if (a == 0)
			throw new InvalidAccountException("Wrong Account Number");
		else
			System.out.println("Value has been updated....\nAmount has been Debited");
		DBConnection.dbclose();;
	}


	@Override
	public void withdraw(String accountNumb, float withdrawnAmount) throws SQLException, InsufficientFundException {
		Connection conn = DBConnection.dbConnect();
		List<Bank> l = new ArrayList<>();
		String sql = "UPDATE account " + "SET account_balance = account_balance - ? " + "WHERE account_number= ? and account_balance-? >= 0";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setFloat(1, withdrawnAmount);
		pstmt.setString(2, accountNumb);
		pstmt.setFloat(3, withdrawnAmount);
		int changes = pstmt.executeUpdate();
		if(changes==0)
			throw new InsufficientFundException("Balance is Less than given Withdraw Amount");
		else
			System.out.println("Value has been updated....\nAmount has been WithDrawn Successfully");
		DBConnection.dbclose();
		}

	@Override
	public void transfer(String senderAcc, String receiverAcc, float transfered)
			throws SQLException, InsufficientFundException {
		Connection conn = DBConnection.dbConnect();
		List<Bank> l = new ArrayList<>();
		String sql = "UPDATE Account " +
                "SET account_balance = CASE " +
                "               WHEN account_number = ? THEN account_balance - ? " +
                "               WHEN account_number = ? THEN account_balance + ? " +
                "               ELSE account_balance " +
                "             END " +
                "WHERE account_number IN (?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, senderAcc);
		pstmt.setFloat(2, transfered);
		pstmt.setString(3, receiverAcc);
		pstmt.setFloat(4, transfered);
		pstmt.setString(5, senderAcc);
        pstmt.setString(6, receiverAcc);
		int c = pstmt.executeUpdate();
		if(c==0)
			throw new InsufficientFundException("Balance is Less than given Transfer Amount");
		else
			System.out.println("Value has been updated....\nAmount has been Transfered Successfully");
		DBConnection.dbclose();
	}

	@Override
	public List<BankDto> getAccountDetails(String accNumber) throws SQLException {
		Connection conn = DBConnection.dbConnect();
		List<BankDto> l = new ArrayList<>();
		String sql = "select * from account , customer " + "where account_number= ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, accNumber);
		ResultSet rst = pstmt.executeQuery();
		if (rst.next()) {
			
			int cid = rst.getInt("customer_id");
			String fname = rst.getString("first_name");
			String lname=rst.getString("last_name");
			String number = rst.getString("account_number");
			String type = rst.getString("account_type");
			float balance = rst.getFloat("account_balance");
			
			BankDto bd=new BankDto();
			bd.setCustomerId(cid);
			bd.setFirstName(fname);
			bd.setLastName(lname);
			bd.setAccountNumber(accNumber);
			bd.setAccountBalance(balance);
			bd.setAccountType(type);
			l.add(bd);
			}
		else
			throw new NullPointerException("Invalid Account Number Given");
		DBConnection.dbclose();
		return l;
	}

}
