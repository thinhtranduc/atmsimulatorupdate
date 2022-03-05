package com.btmglobal.simulator.atm.view;

import java.util.ArrayList;
import java.util.List;

import com.btmglobal.simulator.atm.module.Bank;

public class LoginView extends View {
	public List<String> login() {
		System.out.println("---------------------------LOGIN---------------------------");
		System.out.print("Enter user ID: ");
		String userID = sc.nextLine();
		System.out.print("Enter pin: ");
		String pin = sc.nextLine();

		System.out.println("-----------------------------------------------------------");
		List<String> info = new ArrayList<String>();
		info.add(userID);
		info.add(pin);
		return info;
	}

	public List<String> register() {
		System.out.println("--------------------------REGISTER-------------------------");
		System.out.println("Your first name: ");
		String firstName = sc.nextLine();
		System.out.println("Your last name: ");
		String lastName = sc.nextLine();
		System.out.println("Your PIN (4 digits): ");
		String pin = sc.nextLine();
		boolean check = false;
		try {
			Double.parseDouble(pin);
			check = true;
			if (pin.length() != 4) {
				check = false;
			}
		} catch (NumberFormatException e) {
			check = false;
		}
		while (!check) {
			System.out.println("Please enter your pin with 4 digits: ");
			pin = sc.nextLine();
			try {
				Double.parseDouble(pin);
				check = true;
				if (pin.length() != 4) {
					check = false;
				}
			} catch (NumberFormatException e) {
				check = false;
			}
		}

		System.out.println("-----------------------------------------------------------");
		List<String> info = new ArrayList<String>();
		info.add(firstName);
		info.add(lastName);
		info.add(pin);
		return info;
	}

	public int start() {
		System.out.printf("Welcome to %s", Bank.getName());
		System.out.printf("\nDo you have an account(y/n)? ", Bank.getName());
		String input = sc.nextLine();
		while (!(input.toLowerCase().trim().equals("y") || input.toLowerCase().trim().equals("n"))) {
			System.out.printf("\nIncorrect. Please input y/n....\n");
			System.out.printf("\nDo you have an account(y/n)? \n", Bank.getName());
			input = sc.nextLine();
		}
		if (input.toLowerCase().trim().equals("y")) {
			return 1;
		}
		return 2;
	}
}