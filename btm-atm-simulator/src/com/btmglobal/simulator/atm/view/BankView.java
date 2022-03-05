package com.btmglobal.simulator.atm.view;

import com.btmglobal.simulator.atm.model.Customer;
import com.btmglobal.simulator.atm.model.Transfer;

public class BankView extends View {

	public int printUserMenu(Customer theUser) {
		// print a summary user account
		theUser.printAccountsSummary();
		int choice = 0;
		do {
			System.out.printf("Welcome %s, what would you like to do?\n", theUser.getFirstName());
			System.out.println(" 1) Show account transaction history");
			System.out.println(" 2) Withdraw");
			System.out.println(" 3) Deposit");
			System.out.println(" 4) Transfer");
			System.out.println(" 5) Quit");
			System.out.println();
			System.out.print("Enter your choice: ");
			boolean check = false;
			while (!check) {
				try {
					choice = Integer.parseInt(sc.nextLine());
					check = true;
					if (choice < 1 || choice > 5) {
						System.out.println("Invalid choice. Please choose 1-5");
						System.out.print("Enter your choice: ");
						check = false;
					}
				} catch (Exception e) {
					System.out.println("Invalid choice. Please choose 1-5");
					System.out.print("Enter your choice: ");
					check = false;
				}
			}
		} while (choice < 1 || choice > 5);
		return choice;
	}

	public void showTransHistory(Customer user) {
		int theAcct;
		do {
			System.out.printf("Enter the number (1-%d) of the account" + " whose transactions you want to do see: ",
					user.numAccounts());
			theAcct = sc.nextInt() - 1;
			if (theAcct < 0 || theAcct >= user.numAccounts()) {
				System.out.println("Invalid account. Please try again. ");
			}
		} while (theAcct < 0 || theAcct >= user.numAccounts());
		user.printAcctTransHistory(theAcct);
	}

	public static Transfer transferFunds(Customer theUser) {
		int fromAcct;
		int toAcct;
		double amount;
		double acctBal;
		do {
			System.out.printf("Enter the number (1-%d) of the account\n" + "to transfer from: ", theUser.numAccounts());
			fromAcct = sc.nextInt() - 1;
			if (fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again. ");
			}
		} while (fromAcct < 0 || fromAcct >= theUser.numAccounts());
		acctBal = theUser.getAcctBalance(fromAcct);

		do {
			System.out.printf("Enter the number (1-%d) of the account\n" + "to transfer to: ", theUser.numAccounts());
			toAcct = sc.nextInt() - 1;
			if (toAcct < 0 || toAcct >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again. ");
			}
		} while (toAcct < 0 || toAcct >= theUser.numAccounts());

		do {
			System.out.printf("Enter the amount to transfer (max $%.02f): $", acctBal);
			amount = sc.nextDouble();
			if (amount < 0) {
				System.out.println("Amount must be greater than zero.");
			} else if (amount > acctBal) {
				System.out.printf("Amount must not be greater than\n" + "balance of $%.02f.\n", acctBal);
			}
		} while (amount < 0 || amount > acctBal);

		Transfer tranfer = new Transfer(fromAcct, toAcct, amount, "");
		return tranfer;
	}

	public static Transfer withdrawFunds(Customer theUser) {
		int fromAcct;
		double amount;
		double acctBal;
		String memo;

		do {
			System.out.printf("Enter the number (1-%d) of the account\n" + "to withdraw from: ", theUser.numAccounts());
			fromAcct = sc.nextInt() - 1;
			if (fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again. ");
			}
		} while (fromAcct < 0 || fromAcct >= theUser.numAccounts());
		acctBal = theUser.getAcctBalance(fromAcct);
		do {
			System.out.printf("Enter the amount to withdraw (max $%.02f): $", acctBal);
			amount = sc.nextDouble();
			if (amount < 0) {
				System.out.println("Amount must be greater than zero.");
			} else if (amount > acctBal) {
				System.out.printf("Amount must not be greater than\n" + "balance of $%.02f.\n", acctBal);
			}
		} while (amount < 0 | amount > acctBal);

		// gobble up rest of previous input
		sc.nextLine();
		System.out.println("Enter your message: ");
		memo = sc.nextLine();

		// finally
		Transfer transfer = new Transfer(fromAcct, 0, amount, memo);
		return transfer;
	}

	public static Transfer depositFunds(Customer theUser) {
		int toAcct;
		double amount;
		String memo;

		do {
			System.out.printf("Enter the number (1-%d) of the account\n" + "to deposit in: ", theUser.numAccounts());
			toAcct = sc.nextInt() - 1;
			if (toAcct < 0 || toAcct >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again. ");
			}
		} while (toAcct < 0 || toAcct >= theUser.numAccounts());
		theUser.getAcctBalance(toAcct);
		do {
			System.out.printf("Enter the amount to deposit: $");
			amount = sc.nextDouble();
			if (amount < 0) {
				System.out.println("Amount must be greater than zero.");
			}
		} while (amount < 0);

		// gobble up rest of previous input
		sc.nextLine();
		System.out.print("Enter your message: ");
		memo = sc.nextLine();
		Transfer transfer = new Transfer(0, toAcct, amount, memo);
		return transfer;
	}
}