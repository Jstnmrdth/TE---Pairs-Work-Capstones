package com.techelevator;

public class SavingsAccount extends BankAccount {
	private static final DollarAmount FEE = new DollarAmount(200);
	private static final DollarAmount LIMIT = new DollarAmount(15000);
	
	public SavingsAccount(String accountNumber, DollarAmount balance) {
		super(accountNumber, balance);
	}
	
	@Override
	public DollarAmount withdraw(DollarAmount amountToWithdraw) {
		DollarAmount savingsBalance = getBalance();
	

		
		if(savingsBalance.minus(amountToWithdraw).isLessThan(DollarAmount.ZERO_DOLLARS)) {
			return savingsBalance;
		} else {
			if(savingsBalance.minus(amountToWithdraw).isLessThan(LIMIT)) {
				return super.withdraw(amountToWithdraw.plus(FEE));
			} else {
				return super.withdraw(amountToWithdraw);
			}
		}
	}
}
	
