package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CheckingAccountTest {

	private CheckingAccount account1;
	
	@Before
	public void setup() {
		account1 = new CheckingAccount("12345", new DollarAmount(1000));
	}
	
	@Test
	public void account1_initializes_properly() {
		Assert.assertEquals("The account number should be 12345", "12345", account1.getAccountNumber());
		Assert.assertEquals("There should be 1000 cents in the account", new DollarAmount(1000), account1.getBalance());
	}
	
	// WITHDRAW
	@Test
	public void withdraw_override_returns_correct_amount_when_balance_is_less_than_negative_100() {
		account1.withdraw(new DollarAmount(20000));
		Assert.assertEquals("The balance should be 1000", new DollarAmount(1000), account1.getBalance());
	}
	
	@Test
	public void withdraw_override_returns_correct_amount_when_balance_is_less_than_negative_ten() {
		account1.withdraw(new DollarAmount(2500));
		Assert.assertEquals("The balance should be -2500", new DollarAmount(-2500), account1.getBalance());
	}
	
	@Test
	public void withdraw_override_returns_correct_amount_when_balance_is_greater_than_zero() {
		account1.withdraw(new DollarAmount(500));
		Assert.assertEquals("The balance should be 500", new DollarAmount(500), account1.getBalance());
	}

}
