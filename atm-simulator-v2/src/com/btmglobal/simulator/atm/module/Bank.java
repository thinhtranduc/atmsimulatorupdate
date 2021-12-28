package com.btmglobal.simulator.atm.module;

import java.util.ArrayList;
import java.util.Random;

import com.btmglobal.simulator.atm.customer.Account;
import com.btmglobal.simulator.atm.customer.User;


public class Bank {
	private String name;
	private ArrayList<User> users;
	private ArrayList<Account> accounts;
	
	public Bank(String name) {
		this.name = name;
		this.users = new ArrayList<User>();
		this.accounts = new ArrayList<Account>();
	}
	

	public String getNewUserUUID() {
		String uuid;
		Random rng = new Random();
		int len = 6;
		boolean nonUnique;
		
		do {
			//generate the number
			uuid = "";
			for (int c = 0; c < len; c++) {
				uuid += ((Integer)rng.nextInt(10)).toString();	
			}
			//check to make sure it's unique
			nonUnique = false;
			for (User u : this.users) {
				if(uuid.compareTo(u.getUUID()) == 0){
					nonUnique = true;
					break;
				}
			}
		} while(nonUnique);
		
		return uuid;
	}
	
	
	public String getNewAccountUUID() {

		String uuid;
		Random rng = new Random();
		int len = 8;
		boolean nonUnique;
		
		do {
			//generate the number
			uuid = "";
			for (int c = 0; c < len; c++) {
				uuid += ((Integer)rng.nextInt(10)).toString();	
			}
			//check to make sure it's unique
			nonUnique = false;
			for (Account a : this.accounts) {
				if(uuid.compareTo(a.getUUID()) == 0){
					nonUnique = true;
					break;
				}
			}
		} while(nonUnique);
		
		return uuid;
	}

	public void addAccount(Account anAcct) {
		this.accounts.add(anAcct);
	}
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param pin
	 * @return
	 */
	public User addUser(String firstName, String lastName, String pin) {
		//create a new User object
		User newUser = new User(firstName, lastName, pin, this);
		this.users.add(newUser);
		
		//create a savings account
		Account newAccount = new Account("Savings",newUser, this);
		newUser.addAcount(newAccount);
		this.addAccount(newAccount);
		
		return newUser;
	}
	public User userLogin(String userID, String pin) {
		
		for(User u : this.users) {
			if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
				return u;
			}
		}
		return null;
	}
	public String getName() {
		return this.name;
	}

}