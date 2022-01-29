package com.btmglobal.simulator.atm.model;

import java.util.ArrayList;

import com.btmglobal.simulator.atm.module.Bank;

public class User extends Person {
	private String uuid;
//	private byte pinHash[];
	private String pin;

	private ArrayList<Account> accounts;

	public User(String firstName, String lastName, String pin) {
		// set user's name
		super(firstName, lastName);
		this.pin = pin;
//		//security
//		try {
//		MessageDigest md = MessageDigest.getInstance("MD5");
//		this.pinHash = md.digest(pin.getBytes());
//		} catch (NoSuchAlgorithmException e) {
//			System.err.println("error");
//			e.printStackTrace();
//			System.exit(1);
//		}
		// get ID for the user
		this.uuid = Bank.getNewUserUUID();

		// create empty list of account
		this.accounts = new ArrayList<Account>();
	}

	public User(String firstName, String lastName, String pin, String uuid) {
		// set user's name
		super(firstName, lastName);
		this.pin = pin;
//		//security
//		try {
//		MessageDigest md = MessageDigest.getInstance("MD5");
//		this.pinHash = md.digest(pin.getBytes());
//		} catch (NoSuchAlgorithmException e) {
//			System.err.println("error");
//			e.printStackTrace();
//			System.exit(1);
//		}
		// get ID for the user
		this.uuid = uuid;

		// create empty list of account
		this.accounts = new ArrayList<Account>();
	}

	/**
	 * 
	 * @param anAcct
	 */
	public void addAcount(Account anAcct) {
		this.accounts.add(anAcct);
	}

	public String getUUID() {
		return this.uuid;
	}

	/**
	 * 
	 * @param aPin
	 * @return
	 */
	public boolean validatePin(String aPin) {
//		try {
//			MessageDigest md = MessageDigest.getInstance("MD5");
//			return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash);
//		} catch (NoSuchAlgorithmException e) {
//			System.err.println("error");
//			e.printStackTrace();
//			System.exit(1);
//		}

//		return false;
		return aPin.equals(pin);
	}

	public void printAccountsSummary() {
		System.out.printf("\n\n%s's accounts\n", firstName);
		for (int a = 0; a < this.accounts.size(); a++) {
			System.out.printf("%d) %s\n", a + 1, this.accounts.get(a).getSummaryLine());
		}
		System.out.println();
	}

	public int numAccounts() {
		return this.accounts.size();
	}

	public void printAcctTransHistory(int acctIdx) {
		this.accounts.get(acctIdx).printTransHistory();
	}

	public double getAcctBalance(int acctIdx) {
		return this.accounts.get(acctIdx).getBalance();
	}

	public String getAcctUUID(int acctIdx) {
		return this.accounts.get(acctIdx).getUUID();
	}

	public void addAcctTransaction(int acctIdx, double amount, String memo) {
		this.accounts.get(acctIdx).addTransaction(amount, memo);
	}

	@Override
	public String toString() {
		return String.format("firstName: %s, lastName: %s, pin: %s, uuid: %s", firstName, lastName, pin, uuid);
	}
}
