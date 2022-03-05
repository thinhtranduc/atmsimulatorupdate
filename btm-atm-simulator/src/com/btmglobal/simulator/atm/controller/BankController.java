package com.btmglobal.simulator.atm.controller;

import com.btmglobal.simulator.atm.model.Customer;
import com.btmglobal.simulator.atm.model.Transfer;
import com.btmglobal.simulator.atm.module.Bank;
import com.btmglobal.simulator.atm.view.BankView;

public class BankController {
	BankView view;
	Customer customer;

	public BankController(Customer user) {
		super();
		this.customer = user;
		this.view = new BankView();
	}

	public void start() {
		boolean stop = false;
		while (!stop) {
			int choice = view.printUserMenu(customer);
			switch (choice) {
			case 1:
				showTransHistory();
				break;
			case 2:
				withdrawFunds();
				break;
			case 3:
				depositFunds();
				break;
			case 4:
				transferFunds();
				break;
			case 5:
				stop = true;
				break;
			}
		}
	}

	private void transferFunds() {
		Transfer tranfer = BankView.transferFunds(customer);
		int fromAcct = tranfer.getFromAcct();
		int toAcct = tranfer.getToAcct();
		double amount = tranfer.getAmount();
		customer.addAcctTransaction(fromAcct, -1 * amount,
				String.format("Transfer to account %s", customer.getAcctUUID(toAcct)));
		customer.addAcctTransaction(toAcct, amount,
				String.format("Transfer to account %s", customer.getAcctUUID(fromAcct)));
		Bank.saveData();
	}

	private void depositFunds() {
		Transfer transfer = BankView.depositFunds(customer);
		int toAcct = transfer.getToAcct();
		String memo = transfer.getMemo();
		double amount = transfer.getAmount();
		customer.addAcctTransaction(toAcct, amount, memo);
		Bank.saveData();
	}

	private void withdrawFunds() {
		Transfer transfer = BankView.withdrawFunds(customer);
		int fromAcct = transfer.getFromAcct();
		String memo = transfer.getMemo();
		double amount = transfer.getAmount();
		customer.addAcctTransaction(fromAcct, -1 * amount, memo);
		Bank.saveData();
	}

	private void showTransHistory() {
		view.showTransHistory(customer);

	}

}