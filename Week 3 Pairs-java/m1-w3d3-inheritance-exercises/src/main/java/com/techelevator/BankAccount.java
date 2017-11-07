package com.techelevator;

public class BankAccount {
	private String accountNumber;
	private DollarAmount balance = new DollarAmount(0);
	
	public BankAccount(String accountNumber, DollarAmount balance) {
		this.accountNumber = accountNumber;
		this.balance = balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public DollarAmount getBalance() {
		return balance;
	}
	
	public DollarAmount deposit(DollarAmount amountToDeposit) {
		balance = balance.plus(amountToDeposit);
		return balance;
	}
	
	public DollarAmount withdraw(DollarAmount amountToWithdraw) {
		balance = balance.minus(amountToWithdraw);
		return balance;
	}
	
	public void transfer(BankAccount destinationAccount, DollarAmount transferAmount) {
		DollarAmount thisBalance = this.balance;
		DollarAmount thatBalance = destinationAccount.getBalance();

		if (thisBalance.isGreaterThan(transferAmount)) {
		thisBalance = thisBalance.minus(transferAmount);
		thatBalance = thatBalance.plus(transferAmount);
		}
		
	}

}
