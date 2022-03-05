package com.btmglobal.simulator.atm.controller;

import java.util.List;

import com.btmglobal.simulator.atm.model.Customer;
import com.btmglobal.simulator.atm.model.Staff;
import com.btmglobal.simulator.atm.module.Bank;
import com.btmglobal.simulator.atm.view.AdminView;

public class AdminController {
	private AdminView view;
	private Staff staff;

	public AdminController(Staff staff) {
		super();
		this.staff = staff;
		this.view = new AdminView();
	}

	public void start() {
		boolean stop = false;
		while (!stop) {
			int choice = view.printUserMenu(staff);
			switch (choice) {
			case 1:
				showListCustomer();
				break;
			case 2:
				manageCustomer();
				break;
			case 3:
				stop = true;
				break;
			}
		}
	}

	public void showListCustomer() {
		List<Customer> customers = Bank.listCustomers();
		view.showListUser(customers);
	}

	public void manageCustomer() {
		String userId = view.getUserId();
		if (staff.getUUID().equals(userId)) {
			view.printMess("Cannot work with " + userId + " because this is current user login!!!");
			return;
		}
		boolean check = Bank.findUserWithId(userId);
		if (!check) {
			view.printMess("User not found with id " + userId + "!!!!!");
			return;
		}
		int choice = view.manageUser();
		switch (choice) {
		case 1:
			deleteUser(userId);
			break;
		case 2:
			updateUserAdmin(userId);
			break;
		}
	}

	public void deleteUser(String userId) {
		boolean check = Bank.deleteUser(userId);
		if (check) {
			view.printMess("Successfully");
		} else {
			view.printMess("Something went wrong. Please try again later!!!");
		}
	}

	public void updateUserAdmin(String userId) {
		boolean check = Bank.updateUserAdmin(userId);
		if (check) {
			view.printMess("Successfully");
		} else {
			view.printMess("Something went wrong. Please try again later!!!");
		}
	}

}
