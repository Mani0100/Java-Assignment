package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.dto.BankDto;
import com.exception.InsufficientFundException;
import com.exception.InvalidAccountException;
import com.service.CustomerService;

public class CustomerController {
	public static void main(String[] args) throws InvalidAccountException, SQLException, InsufficientFundException {
		CustomerService cs=new CustomerService();
		Scanner sc=new Scanner(System.in);
		
		while(true)
		{
			System.out.println("********************Customer********************");
			System.out.println("Press 1. To get Account Balance by account number");
			System.out.println("Press 2. To deposit the amount using account number");
			System.out.println("Press 3. To withdraw amount by account number");
			System.out.println("Press 4. To transfer amount by account numbers");
			System.out.println("Press 5. To get account details by account number");
			System.out.println("Press 0. TO EXIT");
			System.out.println("************************************************");
			int input=sc.nextInt();
			// code to exit
			if(input==0)
			{
				System.out.println("EXITING.......THANK YOU!!!!");
				break;
			}
			// switch case to choose the Options.
			switch(input)
			{
				// 1. getAccountBalance()
			case 1:
				System.out.println("*********Account Balance*********");
				System.out.println("Enter your Account Number:");
				sc.nextLine();
				String accountNumber =sc.nextLine();
				try {
					cs.getAccountBalance(accountNumber);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
				break;
			// ----------------------------------------------------------
				// 2. depositAmount()
			case 2:
				System.out.println("*********Deposit Amount*********");
				System.out.println("Enter your Account Number:");
				sc.nextLine();
				String accountNum =sc.nextLine();
				System.out.println("Enter the Amount: ");
				float amount=sc.nextFloat();
				try {
					cs.depositAmount(accountNum,amount);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;

				// ----------------------------------------------------------
				// 3. withdraw()
			case 3:
				System.out.println("*********WithDraw Amount*********");
				System.out.println("Enter your Account Number:");
				sc.nextLine();
				String accountNumb =sc.nextLine();
				System.out.println("Enter the Amount: ");
				float withdrawnAmount=sc.nextFloat();
				try {
					cs.withdraw(accountNumb,withdrawnAmount);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (InsufficientFundException e) {
					e.printStackTrace();
				}
				break;

			// ----------------------------------------------------------
				// 4. transfer()
			case 4:
				System.out.println("*********Transfer Amount*********");
				System.out.println("Enter your Account Number:");
				sc.nextLine();
				String senderAcc =sc.nextLine();
				System.out.println("Enter the Account Number to be sent:");
				String receiverAcc =sc.nextLine();
				System.out.println("Enter the Amount: ");
				float transfered= sc.nextFloat();
				cs.transfer(senderAcc,receiverAcc,transfered);
				break;
			// ----------------------------------------------------------
				// 5. getAccountDetails()
			case 5:
				System.out.println("*********Account Details*********");
				System.out.println("Enter your Account Number:");
				sc.nextLine();
				String accNumber =sc.nextLine();
				List<BankDto> l1=cs.getAccountDetails(accNumber);
				for(BankDto v :l1)
				{
					System.out.println("CustomerId: " + v.getCustomerId() + "\nAccount Number: " + v.getAccountNumber() + "\nFirst Name: " + v.getFirstName()
					+"\nLast Name: " + v.getLastName() + "\nAccount Type: " + v.getAccountType() + "\nAccount Balance: " + v.getAccountBalance());
				}
				break;
			default:
				break;
			}
		}
	}
}