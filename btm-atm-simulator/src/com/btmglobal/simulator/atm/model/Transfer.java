package com.btmglobal.simulator.atm.model;

public class Transfer {
	int fromAcct;
	int toAcct;
	double amount;
	String memo;

	public Transfer(int fromAcct, int toAcct, double amount, String memo) {
		super();
		this.fromAcct = fromAcct;
		this.toAcct = toAcct;
		this.amount = amount;
		this.memo = memo;
	}

	public int getFromAcct() {
		return fromAcct;
	}

	public void setFromAcct(int fromAcct) {
		this.fromAcct = fromAcct;
	}

	public int getToAcct() {
		return toAcct;
	}

	public void setToAcct(int toAcct) {
		this.toAcct = toAcct;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}