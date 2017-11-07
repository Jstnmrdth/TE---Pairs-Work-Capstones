package com.techelevator;

/*
 Write a command line program which prompts the user for the total bill, and the 
 amount tendered. It should then display the change required.
 
 $ java MakeChange
 Please enter the amount of the bill: 23.65
 Please enter the amount tendered: 100.00
 The change required is 76.35
 */

import java.util.Scanner;

public class MakeChange {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Please enter the amount of the bill.");
		double bill = input.nextDouble();
		input.nextLine();
		
		double paid = 0;
		
		boolean fullyPaid = false;
		boolean cantAffordIt = false;
		
		while (!fullyPaid && !cantAffordIt) {
			
			System.out.println("Please enter the amount paid.");
			paid += input.nextDouble();
			input.nextLine();
			System.out.println("You have paid $" + paid);
		
			if (paid < bill) {
				double amountNeeded = bill - paid;
				System.out.println("This is not enough money. You still owe $" + amountNeeded);
				System.out.println("Do you have any more money?");
				String answer = input.nextLine();
	    			if (!answer.equalsIgnoreCase("Y") && !answer.equalsIgnoreCase("Yes")) {
	    				System.out.println("You can't leave the store with the item and everyone is looking at you funny.");
	    				cantAffordIt = true;
	    			}
			}
			
			if (paid > bill) {
				double change = paid - bill;
				System.out.println("The change required is $" + change);
				fullyPaid = true;
			} else if (paid == bill) {
				System.out.println("There is no change required.");
				fullyPaid = true;
			}
		}
	}

}
