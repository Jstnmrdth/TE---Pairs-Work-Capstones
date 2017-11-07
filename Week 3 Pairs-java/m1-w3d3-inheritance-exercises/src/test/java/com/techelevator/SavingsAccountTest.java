package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SavingsAccountTest {

	private SavingsAccount account1;
	
	@Before
	public void setup() {
		account1 = new SavingsAccount("12345", new DollarAmount(20000));
	}
	
	@Test
	public void account1_initializes_properly() {
		Assert.assertEquals("The account number should be 12345", "12345", account1.getAccountNumber());
		Assert.assertEquals("There should be 20000 cents in the account", new DollarAmount(20000), account1.getBalance());
	}
	
	// WITHDRAW
	@Test
	public void withdraw_override_returns_correct_amount_when_balance_is_less_than_150() {
		account1.withdraw(new DollarAmount(10000));
		Assert.assertEquals("The balance should be 9800", new DollarAmount(9800), account1.getBalance());
	}
	
	@Test
	public void withdraw_override_returns_correct_amount_when_balance_is_zero() {
		account1.withdraw(new DollarAmount(30000));
		Assert.assertEquals("The balance should be 20000", new DollarAmount(20000), account1.getBalance());
	}
	
	@Test
	public void withdraw_override_returns_correct_amount_when_balance_is_greater_than_150() {
		account1.withdraw(new DollarAmount(500));
		Assert.assertEquals("The balance should be 19500", new DollarAmount(19500), account1.getBalance());
	}

}
