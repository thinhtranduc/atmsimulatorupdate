package com.btmglobal.simulator.atm.controller;

import com.btmglobal.simulator.atm.model.Transfer;
import com.btmglobal.simulator.atm.model.User;
import com.btmglobal.simulator.atm.view.BankView;

public class BankController {
	BankView view;
	User user;

	public BankController(User user) {
		super();
		this.user = user;
		this.view = new BankView();
	}

	public void start() {
		boolean stop = false;
		while (!stop) {
			int choice = view.printUserMenu(user);
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
		Transfer tranfer = BankView.transferFunds(user);
		int fromAcct = tranfer.getFromAcct();
		int toAcct = tranfer.getToAcct();
		double amount = tranfer.getAmount();
		user.addAcctTransaction(fromAcct, -1 * amount,
				String.format("Transfer to account %s", user.getAcctUUID(toAcct)));
		user.addAcctTransaction(toAcct, amount, String.format("Transfer to account %s", user.getAcctUUID(fromAcct)));

	}

	private void depositFunds() {
		Transfer transfer = BankView.depositFunds(user);
		int toAcct = transfer.getToAcct();
		String memo = transfer.getMemo();
		double amount = transfer.getAmount();
		user.addAcctTransaction(toAcct, amount, memo);

	}

	private void withdrawFunds() {
		Transfer transfer = BankView.withdrawFunds(user);
		int fromAcct = transfer.getFromAcct();
		String memo = transfer.getMemo();
		double amount = transfer.getAmount();
		user.addAcctTransaction(fromAcct, -1 * amount, memo);

	}

	private void showTransHistory() {
		view.showTransHistory(user);

	}

}