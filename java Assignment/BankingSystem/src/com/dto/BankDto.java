package com.dto;

import java.util.Objects;

public class BankDto {

	private int customerId;
	private String accountNumber;
	private String firstName;
	private String lastName;
	private String accountType;
	private float accountBalance;
	public BankDto(int customerId, String accountNumber, String firstName, String lastName, String accountType,
			float accountBalance) {
		super();
		this.customerId = customerId;
		this.accountNumber = accountNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
	}
	public BankDto() {
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
		return "BankDto [customerId=" + customerId + ", accountNumber=" + accountNumber + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", accountType=" + accountType + ", accountBalance=" + accountBalance
				+ "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(accountBalance, accountNumber, accountType, customerId, firstName, lastName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankDto other = (BankDto) obj;
		return Float.floatToIntBits(accountBalance) == Float.floatToIntBits(other.accountBalance)
				&& Objects.equals(accountNumber, other.accountNumber) && Objects.equals(accountType, other.accountType)
				&& customerId == other.customerId && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName);
	}
	
	
}
