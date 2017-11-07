package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class BankAccountTest {

	private BankAccount account1;
	
	@Before
	public void setup() {
		account1 = new BankAccount("12345", new DollarAmount(1000));
	}
	
	@Test
	public void account1_initializes_properly() {
		Assert.assertEquals("The account number should be 12345", "12345", account1.getAccountNumber());
		Assert.assertEquals("There should be 1000 cents in the account", new DollarAmount(1000), account1.getBalance());
	}
	
	@Test
	public void set_account_number_sets_correct_account_number() {
		account1.setAccountNumber("54321");
		Assert.assertEquals("The account number should be 54321", "54321", account1.getAccountNumber());
	}
	
	@Test
	public void deposit_should_change_balance_correctly () {
		account1.deposit(new DollarAmount(2000));
		Assert.assertEquals("Account balance should be 3000", new DollarAmount(3000), account1.getBalance());
	}
	
	@Test
	public void withdraw_should_change_balance_correctly () {
		account1.withdraw(new DollarAmount(500));
		Assert.assertEquals("Account balance should be 500", new DollarAmount(500), account1.getBalance());
	}
	
}
	
	