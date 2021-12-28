package com.btmglobal.simulator.atm.main.entry;

import java.util.Scanner;

import com.btmglobal.simulator.atm.control.Control;
import com.btmglobal.simulator.atm.customer.Account;
import com.btmglobal.simulator.atm.customer.User;
import com.btmglobal.simulator.atm.module.Bank;

public class ATM {
	public static void main(String[] args) {
		Scanner sc = new  Scanner(System.in);
		Bank theBank = new Bank("Bank of Thinh");
		User aUser = theBank.addUser("Thinh","Customer", "1111");
		Account newAccount = new Account("Checking", aUser, theBank);
		aUser.addAcount(newAccount);
		theBank.addAccount(newAccount);
		
		User curUser;
		while (true) {
			// stay in the login prompt until successful login
			curUser = Control.mainMenuPrompt(theBank, sc);
			Control.printUserMenu(curUser, sc);
			
		}
	}

	
}