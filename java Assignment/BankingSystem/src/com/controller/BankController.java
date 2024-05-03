package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.dto.BankDto;
import com.exception.InsufficientFundException;
import com.exception.InvalidAccountException;
import com.model.Bank;
import com.model.Customer;
import com.service.BankService;
import com.service.CustomerService;

public class BankController {
	public static void main(String[] args) throws SQLException, InvalidAccountException, InsufficientFundException {
		BankService bs=new BankService();
		CustomerService cbs = new CustomerService();
		Scanner sc=new Scanner(System.in);
		
		while(true)
		{
			System.out.println("********************Customer********************");
			System.out.println("Press 1. To Create a Account");
			System.out.println("Press 2. To List All Accounts in Bank");
			System.out.println("Press 3. To calculate Interest for a Balance");
			System.out.println("Press 4. To get Account Balance by account number");
			System.out.println("Press 5. To deposit the amount using account number");
			System.out.println("Press 6. To withdraw amount by account number");
			System.out.println("Press 7. To transfer amount by account numbers");
			System.out.println("Press 8. To get account details by account number");
			System.out.println("Press 0. TO EXIT");
			System.out.println("************************************************");
			int input=sc.nextInt();
			// code to exit
			if(input==0)
			{
				System.out.println("EXITING.......THANK YOU!!!!");
				break;
			}
			
			switch(input)
			{
		// -------------------------------------------------------------------------
			// createAccount()
			case 1:
				System.out.println("**********Creating Account**********");
				System.out.println("Enter Customer id");
				int customer_id =sc.nextInt();
				System.out.println("Enter you first name: ");
				sc.nextLine();
				String fname=sc.nextLine();
				System.out.println("Enter your last name: ");
				String lname=sc.nextLine();
				System.out.println("Enter your email id: ");
				String email=sc.nextLine();
				System.out.println("Enter your phone number: ");
				String phone=sc.nextLine();
				System.out.println("Enter your address: ");
				String address=sc.nextLine();
				
				Customer customer= new Customer(customer_id,fname,lname,email,phone,address);
				int usi = bs.createCustomerAccount(customer);
				
				System.out.println("******Enter Account Details******\nEnter Account Number: ");
			//	sc.nextLine();
				String accNum=sc.nextLine();
				System.out.println("Enter Account Type :\n1.Savings\n2.Current");
				int choice=sc.nextInt();
				String type = null;
				if(choice == 1)
					type = "Saving";
				else if (choice == 2)
					type = "Current";
				System.out.println("Enter the Starting Balance (Minimum balance = Rs:1500-/-) : ");
				float balance=sc.nextFloat();
				if(balance >= 1500)
					bs.createBankAccount(usi,accNum,type,balance);
				else
					System.out.println("Doesn't satisfy the minimum balance constrain");
				break;
			// -------------------------------------------------------------------------	
				// 2. listAccounts()		
			case 2:
				System.out.println("*********Listing All Accounts*********");
				List<Bank> l=bs.listAccounts();
				for( Bank b : l)
					System.out.println(b);
				break;
			// -------------------------------------------------------------------------
				//3. calculateInterest()
			case 3:
				System.out.println("**********Calculate Interest**********");
				System.out.println("Enter the Balance to check: ");
				float bal=sc.nextFloat();
				System.out.println("Enter the Annual Interest Rate: ");
				float rate=sc.nextFloat();
				bs.calculateInterest(bal,rate);
				break;
			// -------------------------------------------------------------------------
			case 4:
				System.out.println("*********Account Balance*********");
				System.out.println("Enter your Account Number:");
				sc.nextLine();
				String accountNumber =sc.nextLine();
				try {
					cbs.getAccountBalance(accountNumber);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			// -------------------------------------------------------------------------
			case 5:
				System.out.println("*********Deposit Amount*********");
				System.out.println("Enter your Account Number:");
				sc.nextLine();
				String accountNum =sc.nextLine();
				System.out.println("Enter the Amount: ");
				float amount=sc.nextFloat();
				try {
					cbs.depositAmount(accountNum,amount);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			// -------------------------------------------------------------------------
			case 6:
				System.out.println("*********WithDraw Amount*********");
				System.out.println("Enter your Account Number:");
				sc.nextLine();
				String accountNumb =sc.nextLine();
				System.out.println("Enter the Amount: ");
				float withdrawnAmount=sc.nextFloat();
				try {
					cbs.withdraw(accountNumb,withdrawnAmount);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (InsufficientFundException e) {
					e.printStackTrace();
				}
				break;
			// -------------------------------------------------------------------------
			case 7:
				System.out.println("*********Transfer Amount*********");
				System.out.println("Enter your Account Number:");
				sc.nextLine();
				String senderAcc =sc.nextLine();
				System.out.println("Enter the Account Number to be sent:");
				String receiverAcc =sc.nextLine();
				System.out.println("Enter the Amount: ");
				float transfered= sc.nextFloat();
				cbs.transfer(senderAcc,receiverAcc,transfered);
				break;
			// -------------------------------------------------------------------------
			case 8:
				System.out.println("*********Account Details*********");
				System.out.println("Enter your Account Number:");
				sc.nextLine();
				String accNumber =sc.nextLine();
				List<BankDto> l1=cbs.getAccountDetails(accNumber);
				for(BankDto v :l1)
					System.out.println("CustomerId: " + v.getCustomerId() + "\nAccount Number: " + v.getAccountNumber() + "\nFirst Name: " + v.getFirstName()
							+"\nLast Name: " + v.getLastName() + "\nAccount Type: " + v.getAccountType() + "\nAccount Balance: " + v.getAccountBalance());
				break;
			// -------------------------------------------------------------------------
			default:
				System.out.println("Invalid Input...");
				break;
			}
		}
	}
}