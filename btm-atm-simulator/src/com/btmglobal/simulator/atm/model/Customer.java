package com.btmglobal.simulator.atm.model;

import java.util.ArrayList;

public class Customer extends User {

	private ArrayList<Account> accounts;
	
	public Customer(String firstName, String lastName, String pin) {
		super(firstName, lastName, pin);
		this.accounts = new ArrayList<Account>();
	}

	public Customer(String firstName, String lastName, String pin, String uuid) {
		super(firstName, lastName, pin, uuid);
		this.accounts = new ArrayList<Account>();
	}
	
	/**
	 * 
	 * @param anAcct
	 */
	public void addAcount(Account anAcct) {
		this.accounts.add(anAcct);
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
		return String.format("firstName: %s, lastName: %s, pin: %s, uuid: %s, type: 1", firstName, lastName, getPin(), getUuid());
	}
	
}
