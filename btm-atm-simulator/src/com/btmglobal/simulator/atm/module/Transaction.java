package com.btmglobal.simulator.atm.module;

import java.util.Date;

import com.btmglobal.simulator.atm.model.Account;

public class Transaction {
	private double amount;
	private Date timestamp;
	private String memo;
	private Account inAccount;

	public Transaction() {
		super();
	}

	public Transaction(double amount, Account inAccount) {

		this.amount = amount;
		this.setInAccount(inAccount);
		this.timestamp = new Date();
		this.memo = "";

	}

	public Transaction(double amount, String memo, Account inAccount) {

		// call the constructor
		this(amount, inAccount);

		// set the memo
		this.memo = memo;

	}

	public Transaction(double amount, String memo, Account inAccount, Date timeStam) {

		// call the constructor
		this(amount, inAccount);

		// set the memo
		this.memo = memo;
		this.timestamp = timeStam;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getAmount() {
		return this.amount;
	}

	public String getSummaryLine() {
		if (this.amount >= 0) {
			return String.format("%s : $%.02f : %s", this.timestamp.toString(), this.amount, this.memo);
		} else {
			return String.format("%s : $(%.02f) : %s", this.timestamp.toString(), this.amount, this.memo);
		}
	}

	public Account getInAccount() {
		return inAccount;
	}

	public void setInAccount(Account inAccount) {
		this.inAccount = inAccount;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}