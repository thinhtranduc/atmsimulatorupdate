package com.btmglobal.simulator.atm.controller;

import java.util.List;

import com.btmglobal.simulator.atm.model.Customer;
import com.btmglobal.simulator.atm.model.Staff;
import com.btmglobal.simulator.atm.model.User;
import com.btmglobal.simulator.atm.module.Bank;
import com.btmglobal.simulator.atm.view.LoginView;

public class LoginController {

	LoginView view;

	public LoginController() {
		super();
		this.view = new LoginView();
	}

	public void start() {
		int func = view.start();
		User user;
		if (func == 1) {
			user = login();
		} else {
			user = register();
		}
		if (user instanceof Customer) {
			BankController controller = new BankController((Customer) user);
			controller.start();
		} else if (user instanceof Staff) {
			AdminController controller = new AdminController((Staff) user);
			controller.start();
		}
	}

	public User login() {
		List<String> info = view.login();
		User aUser = null;
		if (info != null && info.size() == 2) {
			aUser = Bank.userLogin(info.get(0), info.get(1));
		}
		while (aUser == null) {
			view.printMess("Incorrect user ID/pin. " + "Please try again. ");
			info = view.login();
			if (info != null && info.size() == 2) {
				aUser = Bank.userLogin(info.get(0), info.get(1));
			}
		}
		return aUser;
	}

	public User register() {
		List<String> info = view.register();
		User aUser = null;
		if (info != null && info.size() == 3) {
			aUser = Bank.addUser(info.get(0), info.get(1), info.get(2));
		}
		while (aUser == null) {
			view.printMess("Something when wrong. Please try again");
			info = view.register();
			if (info != null && info.size() == 3) {
				aUser = Bank.addUser(info.get(0), info.get(1), info.get(2));
			}
		}
		view.printMess(String.format("New user %s, %s with ID %s created.", aUser.getLastName(), aUser.getFirstName(),
				aUser.getUUID()));
		return aUser;
	}

}