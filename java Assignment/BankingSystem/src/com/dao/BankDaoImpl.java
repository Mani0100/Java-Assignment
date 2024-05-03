package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.model.Bank;
import com.model.Customer;
import com.utility.DBConnection;


public class BankDaoImpl implements BankDao{
	Scanner sc = new Scanner(System.in);
	@Override
	public void createBankAccount(int customer_id, String accNum, String type, float balance) throws SQLException {
		  Connection conn = DBConnection.dbConnect();
		  String checkCustomerSql = "SELECT id FROM customer WHERE id = ?"; // Check for existing customer
		  PreparedStatement checkPstmt = conn.prepareStatement(checkCustomerSql);
		  checkPstmt.setInt(1, customer_id);
		  ResultSet checkRst = checkPstmt.executeQuery();

		  if (checkRst.next()) { // Customer exists
		    String sql = "INSERT INTO account(customer_id, account_number, account_type, account_balance) VALUES(?,?,?,?)";
		    PreparedStatement pstmt = conn.prepareStatement(sql);
		    pstmt.setInt(1, customer_id);
		    pstmt.setString(2, accNum);
		    pstmt.setString(3, type);
		    pstmt.setFloat(  4, balance);
		    int changes = pstmt.executeUpdate();
		    if (changes == 0) {
		      System.out.println("Error....No updates have been done");
		    } else {
		      System.out.println("New Bank Detail has been Inserted succesfully...");
		    }
		  } else { // Customer doesn't exist
		    System.out.println("Customer with ID " + customer_id + " doesn't exist. Please create a customer first.");
		  }
		  DBConnection.dbclose();
		}

		
	

	@Override
	public int createCustomerAccount(Customer customer) throws SQLException {
		Connection conn=DBConnection.dbConnect();
		String sql = "INSERT INTO customer(first_name,last_name,email,phone_number,address) VALUES(?,?,?,?,?)";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		String fname= customer.getFirstName();
		String lname=customer.getLastName();
		String email=customer.getEmail();
		String phone=customer.getPhoneNumber();
		String address=customer.getAddress();
		pstmt.setString(1, fname);
		pstmt.setString(2, lname);
		pstmt.setString(3, email);
		pstmt.setString(4, phone);
		pstmt.setString(5, address);

		int changes=pstmt.executeUpdate();
		
		if(changes==0)
			System.out.println("Error....No updates have been done");
		else
			System.out.println("New Customer has been Inserted succesfully...\n Add the Account Details");
		DBConnection.dbclose();
		return customer.getId();
	}

	@Override
	public List<Bank> listAccounts() throws SQLException {
		List<Bank> list = new ArrayList<>();
		Connection conn=DBConnection.dbConnect();
		String sql = "select * from account order by customer_id";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rst = pstmt.executeQuery();
		
		while (rst.next()) {
			int cid=rst.getInt("customer_id");
			String accNumb=rst.getString("account_number");
			String accType=rst.getString("account_type");
			Float accBaln= rst.getFloat("account_balance");
			Bank p = new Bank(cid,accNumb,accType,accBaln);
			list.add(p);
		}
		DBConnection.dbclose();
		return list;
	}

	@Override
	public void calculateInterest(float bal, float rate) {
		double interest =bal*(rate*0.01);
		System.out.println("For How many years do you want to find the interest?");
		int y=sc.nextInt();
		if(y==1)
			System.out.println("Total Interest for "+y+" year is: "+interest);
		else
			System.out.println("Total Interest for "+y+ " years is: "+(interest*y));
	}

}