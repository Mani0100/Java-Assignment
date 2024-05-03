package com.model;

import java.util.Objects;

public class Bank {

	private int customerId;
	private String accountNumber;
	private String accountType;
	private float accountBalance;
	public Bank(int customerId, String accountNumber, String accountType, float accountBalance) {
		super();
		this.customerId = customerId;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
	}
	public Bank() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public float getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(float accountBalance) {
		this.accountBalance = accountBalance;
	}
	@Override
	public String toString() {
		return "Bank [customerId=" + customerId + ", accountNumber=" + accountNumber + ", accountType=" + accountType
				+ ", accountBalance=" + accountBalance + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(accountBalance, accountNumber, accountType, customerId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bank other = (Bank) obj;
		return Float.floatToIntBits(accountBalance) == Float.floatToIntBits(other.accountBalance)
				&& Objects.equals(accountNumber, other.accountNumber) && Objects.equals(accountType, other.accountType)
				&& customerId == other.customerId;
	}
	
	
}
