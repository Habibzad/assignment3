package com.meritamerica.assignment3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BankAccount {
	
	public double accountBalance;
	public long accountNumber;
	public double interestRate;
	public Date startDate;
	
	static private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	//Constructors
	public BankAccount(double balance) {
		this.accountBalance = balance;
		this.accountNumber = AccountHolder.getNewAccountNumber();
	}
	
	public BankAccount(double balance, double interestRate) {
		this.accountBalance = balance;
		this.interestRate = interestRate;
		this.accountNumber = AccountHolder.getNewAccountNumber();
	}
	
	public BankAccount(long accountNumber, double balance, double interestRate) {
		this.accountNumber = accountNumber;
		this.accountBalance = balance;
		this.interestRate = interestRate;
	}
	
	public BankAccount(long accountNumber, double balance, double interestRate, java.util.Date accountOpenedOn) {
		this.accountNumber = accountNumber;
		this.accountBalance = balance;
		this.interestRate = interestRate;
		this.startDate = accountOpenedOn;
	}
	
	public BankAccount(long accountNumber, double balance, double interestRate, java.util.Date accountOpenedOn, int term) {
		this.accountNumber = accountNumber;
		this.accountBalance = balance;
		this.interestRate = interestRate;
		this.startDate = accountOpenedOn;
		
	}
	
	public long getAccountNumber() {
		return this.accountNumber;
	}
	
	public double getBalance() {
		
		return this.accountBalance;
	}
	
	public boolean withdraw(double amount) {
		if(amount <= this.accountBalance) {
			this.accountBalance -= amount;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean deposit(double amount) {
		if(amount <= 0) {
			return false;
		} else {
			this.accountBalance += amount;
			return true;
		}
	}
	
	//Get opening date
	java.util.Date getOpenedOn() {
		return startDate;
	}
	
	public double futureValue(int years) {
		return getBalance() * (Math.pow(1 + getInterestRate(), years));
	}
	
	public double getInterestRate() {
		return this.interestRate;
	}
	
	public double truncateValue(double toTruncate) {
		toTruncate *= 100; 
		int truncatedInt = (int)toTruncate;
		double truncatedDouble = (double)truncatedInt / 100;
		return truncatedDouble;
	}
	
	public static BankAccount readFromString(String accountData) throws ParseException {
		try {
			
		
		String[] accountInfo = accountData.split(",");
		
		long accountNumber = Long.valueOf(accountInfo[0]);
		double accountBalance = Double.valueOf(accountInfo[1]);
		double interestRate = Double.valueOf(accountInfo[2]);
		Date startDate = formatter.parse(accountInfo[3]);
		return new BankAccount(accountNumber,accountBalance,interestRate,startDate);
		} catch (ParseException e){
			return null;
		}
	}
	
	public String writeToString() {
		String newString = this.accountNumber + "," + this.accountBalance + "," + this.interestRate + "," + this.startDate;
		return newString;
	}

	public String toString() {
		
		return  "Account Balance: $" + getBalance() + "\n" +
				"Account Interest Rate: 0.0001\n" +
				"Account Balance in 3 years: $" + truncateValue(futureValue(3));
	}
	
}
