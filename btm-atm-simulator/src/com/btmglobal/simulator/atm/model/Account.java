package com.btmglobal.simulator.atm.model;

import java.util.ArrayList;
import java.util.Date;

import com.btmglobal.simulator.atm.module.Bank;
import com.btmglobal.simulator.atm.module.Transaction;

public class Account {
	private String name;
	private String uuid;
	private ArrayList<Transaction> transactions;

	/**
	 * 
	 * @param name
	 * @param holder
	 * @param theBank
	 */

	public Account(String name, User holder) {

		// set the account name and holder
		this.name = name;
		// get new account UUID
		this.uuid = Bank.getNewAccountUUID();
		// transactions
		this.transactions = new ArrayList<Transaction>();

	}

	public Account(String uuid, String name, ArrayList<Transaction> transactions) {
		this.name = name;
		this.uuid = uuid;
		this.transactions = transactions;
	}

	public String getUUID() {
		return this.uuid;
	}

	public String getSummaryLine() {
		double balance = this.getBalance();
		if (balance >= 0) {
			return String.format("%s : $%.02f : %s", this.uuid, balance, this.name);
		} else {
			return String.format("%s : $(%.02f) : %s", this.uuid, balance, this.name);
		}
	}

	public double getBalance() {
		double balance = 0;
		for (Transaction t : this.transactions) {
			balance += t.getAmount();
		}
		return balance;
	}

	public void printTransHistory() {
		System.out.printf("\nTransaction history for account %s\n", this.uuid);
		for (int t = this.transactions.size() - 1; t >= 0; t--) {
			System.out.printf(this.transactions.get(t).getSummaryLine() + "\n");

		}
		System.out.println();
	}

	public void addTransaction(double amount, String memo) {
		Transaction newTrans = new Transaction(amount, memo, this);
		this.transactions.add(newTrans);
	}

	public void addTransaction(double amount, String memo, Date timeStam) {
		Transaction newTrans = new Transaction(amount, memo, this, timeStam);
		this.transactions.add(newTrans);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}

}