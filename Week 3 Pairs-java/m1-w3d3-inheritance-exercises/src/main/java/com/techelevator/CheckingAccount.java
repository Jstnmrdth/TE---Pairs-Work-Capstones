package com.techelevator;

public class CheckingAccount extends BankAccount {
	private static final DollarAmount FEE = new DollarAmount(1000);
	private static final DollarAmount LIMIT = new DollarAmount(-10000);
	
	public CheckingAccount(String accountNumber, DollarAmount balance) {
		super(accountNumber, balance);
	}
	
	@Override
	public DollarAmount withdraw(DollarAmount amountToWithdraw) {
		
		DollarAmount checkingBalance = getBalance();
		
		if(checkingBalance.minus(amountToWithdraw).isGreaterThan(DollarAmount.ZERO_DOLLARS)) {
			return super.withdraw(amountToWithdraw);
		} else {
			if(checkingBalance.minus(amountToWithdraw.plus(FEE)).isLessThan(LIMIT)) {
				return checkingBalance;
			} else {
				return super.withdraw(amountToWithdraw.plus(FEE));
			}
		}
		
		

	
	}
}
