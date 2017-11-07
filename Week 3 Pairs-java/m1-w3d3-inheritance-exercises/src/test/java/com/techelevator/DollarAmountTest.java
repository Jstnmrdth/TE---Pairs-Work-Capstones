package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class DollarAmountTest {

	private DollarAmount money;
	
	@Before
	public void setup() {
		money = new DollarAmount(2387);
	}
	
	@Test
	public void money_initializes_properly() {
		Assert.assertEquals("There should be 87 cents", 87, money.getCents());
		Assert.assertEquals("There should be 23 dollars", 23, money.getDollars());
	}
	
	
	
	// GREATER THAN
	@Test
	public void greater_than_returns_false_when_value_is_less_than() {
		DollarAmount newMoney = new DollarAmount(3000);
		Assert.assertFalse("Variable should be greater than given value", money.isGreaterThan(newMoney));
	}
	
	@Test
	public void greater_than_returns_false_when_value_is_equal() {
		DollarAmount newMoney = new DollarAmount(2387);
		Assert.assertFalse("Variable should be greater than given value", money.isGreaterThan(newMoney));
	}
	
	@Test
	public void greater_than_returns_true_when_value_is_greater_than() {
		DollarAmount newMoney = new DollarAmount(1000);
		Assert.assertTrue("Variable should be greater than given value", money.isGreaterThan(newMoney));
	}
	
	// GREATER THAN OR EQUAL TO
	@Test
	public void greater_than_or_equal_to_returns_false_when_value_is_less_than() {
		DollarAmount newMoney = new DollarAmount(3000);
		Assert.assertFalse("Variable should be greater than or equal to given value", money.isGreaterThanOrEqualTo(newMoney));
	}
	
	@Test
	public void greater_than_or_equal_to_returns_true_when_value_is_equal() {
		DollarAmount newMoney = new DollarAmount(2387);
		Assert.assertTrue("Variable should be greater than or equal to given value", money.isGreaterThanOrEqualTo(newMoney));
	}
	
	@Test
	public void greater_than_or_equal_to_returns_true_when_value_is_greater_than() {
		DollarAmount newMoney = new DollarAmount(1000);
		Assert.assertTrue("Variable should be greater than or equal to given value", money.isGreaterThanOrEqualTo(newMoney));
	}
	
	// LESS THAN
	@Test
	public void less_than_returns_false_when_value_is_greater_than() {
		DollarAmount newMoney = new DollarAmount(1000);
		Assert.assertFalse("Variable should be less than given value", money.isLessThan(newMoney));
	}
	
	@Test
	public void less_than_returns_false_when_value_is_equal() {
		DollarAmount newMoney = new DollarAmount(2387);
		Assert.assertFalse("Variable should be less than given value", money.isLessThan(newMoney));
	}
	
	@Test
	public void less_than_returns_true_when_value_is_less_than() {
		DollarAmount newMoney = new DollarAmount(3000);
		Assert.assertTrue("Variable should be less than given value", money.isLessThan(newMoney));
	}
	
	// LESS THAN OR EQUAL TO
	@Test
	public void less_than_or_equal_to_returns_false_when_value_is_greater_than() {
		DollarAmount newMoney = new DollarAmount(1000);
		Assert.assertFalse("Variable should be less than or equal to given value", money.isLessThanOrEqualTo(newMoney));
	}
	
	@Test
	public void less_than_or_equal_to_returns_true_when_value_is_equal() {
		DollarAmount newMoney = new DollarAmount(2387);
		Assert.assertTrue("Variable should be less than or equal to given value", money.isLessThanOrEqualTo(newMoney));
	}
	
	@Test
	public void less_than_or_equal_to_returns_true_when_value_is_less_than() {
		DollarAmount newMoney = new DollarAmount(3000);
		Assert.assertTrue("Variable should be less than or equal to given value", money.isLessThanOrEqualTo(newMoney));
	}
	
	// IS NEGATIVE
	@Test
	public void positive_value_should_return_false() {
		DollarAmount newMoney = new DollarAmount(1000);
		Assert.assertFalse("Positive variable should return false", newMoney.isNegative());
	}
	
	@Test
	public void zero_value_should_return_false() {
		DollarAmount newMoney = new DollarAmount(0);
		Assert.assertFalse("Zero should return false", newMoney.isNegative());
	}
	
	@Test
	public void negative_value_should_return_true() {
		DollarAmount newMoney = new DollarAmount(-1000);
		Assert.assertTrue("Variable should be less than or equal to given value", newMoney.isNegative());
	}
    
    // MINUS
	@Test
    public void minus_should_return_correct_new_amount() {
    		DollarAmount newMoney = new DollarAmount(1000);
    		Assert.assertEquals("New amount should be 1387", new DollarAmount(1387), money.minus(newMoney));
    }
	
    // PLUS
	@Test
    public void plus_should_return_correct_new_amount() {
    		DollarAmount newMoney = new DollarAmount(1000);
    		Assert.assertEquals("New amount should be 3387", new DollarAmount(3387), money.plus(newMoney));
    }
	
	// COMPARE TO
	@Test
	public void returns_1_if_variable_is_greater_than_given_value () {
		DollarAmount newMoney = new DollarAmount(1000);
		Assert.assertEquals("compareTo should return 1", 1, money.compareTo(newMoney));
	}
	
	@Test
	public void returns__negative_1_if_variable_is_less_than_given_value () {
		DollarAmount newMoney = new DollarAmount(3000);
		Assert.assertEquals("compareTo should return -1", -1, money.compareTo(newMoney));
	}
	
	@Test
	public void returns_0_if_variable_is_equal_to_given_value () {
		DollarAmount newMoney = new DollarAmount(2387);
		Assert.assertEquals("compareTo should return 0", 0, money.compareTo(newMoney));
	}
	
	// EQUALS
	@Test
	public void equals_returns_false_if_DollarAmount_is_not_equal() {
		DollarAmount newMoney = new DollarAmount(1000);
		Assert.assertFalse("should return false because values are not equal", money.equals(newMoney));
	}
	
	@Test
	public void equals_returns_false_when_value_is_null () {
		Assert.assertFalse("should return false because given value is null", money.equals(null));
	}
	
	@Test
	public void equals_returns_false_when_value_is_not_instance_of_DollarAmount () {
		Assert.assertFalse("should return false because given value is null", money.equals(10));
	}
	
	@Test
	public void equals_returns_true_if_DollarAmount_is_equal() {
		DollarAmount newMoney = new DollarAmount(2387);
		Assert.assertTrue("should return true because values are equal", money.equals(newMoney));
	}
	
	
	// HASH CODE
	@Test
	public void hash_code_override_returns_total_amount_in_cents() {
		Assert.assertEquals("should return 2387", 2387, money.hashCode());
	}
	
	// TO STRING
	@Test
	public void to_string_override_returns_correct_string() {
		Assert.assertEquals("toString should return $23.87", "$23.87", money.toString());
		
	}
}
