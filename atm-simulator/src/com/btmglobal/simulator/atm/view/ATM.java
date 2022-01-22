package com.btmglobal.simulator.atm.view;

import java.util.Scanner;

import com.btmglobal.simulator.atm.controller.Controller;
import com.btmglobal.simulator.atm.model.Account;
import com.btmglobal.simulator.atm.model.User;
import com.btmglobal.simulator.atm.module.Bank;


public class ATM {
	public static void main(String[] args) {
		Scanner sc = new  Scanner(System.in);
		Bank theBank = new Bank("My Bank");
		User aUser = theBank.addUser("Thinh","", "1111");
		Account newAccount = new Account("Checking", aUser, theBank);
		aUser.addAcount(newAccount);
		theBank.addAccount(newAccount);
		
		User curUser;
		while (true) {
			// stay in the login prompt until successful login
			curUser = Controller.mainMenuPrompt(theBank, sc);
			Controller.printUserMenu(curUser, sc);
			
		}
	}

	
}