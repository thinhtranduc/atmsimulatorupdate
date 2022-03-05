package com.btmglobal.simulator.atm.module;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.btmglobal.simulator.atm.data.ReadXmlDomParser;
import com.btmglobal.simulator.atm.model.Account;
import com.btmglobal.simulator.atm.model.Customer;
import com.btmglobal.simulator.atm.model.Staff;
import com.btmglobal.simulator.atm.model.User;

public final class Bank {

	private static String name;
	private static ArrayList<User> users;
	private static ArrayList<Account> accounts;

	public Bank(String name) {
		Bank.name = name;
		Bank.users = new ArrayList<User>();
		Bank.accounts = new ArrayList<Account>();
		readData();
	}

	public static String getNewUserUUID() {
		String uuid;
		Random rng = new Random();
		int len = 6;
		boolean nonUnique;

		do {
			// generate the number
			uuid = "";
			for (int c = 0; c < len; c++) {
				uuid += ((Integer) rng.nextInt(10)).toString();
			}
			// check to make sure it's unique
			nonUnique = false;
			for (User u : users) {
				if (uuid.compareTo(u.getUUID()) == 0) {
					nonUnique = true;
					break;
				}
			}
		} while (nonUnique);

		return uuid;
	}

	public static String getNewAccountUUID() {

		String uuid;
		Random rng = new Random();
		int len = 8;
		boolean nonUnique;

		do {
			// generate the number
			uuid = "";
			for (int c = 0; c < len; c++) {
				uuid += ((Integer) rng.nextInt(10)).toString();
			}
			// check to make sure it's unique
			nonUnique = false;
			for (Account a : accounts) {
				if (uuid.compareTo(a.getUUID()) == 0) {
					nonUnique = true;
					break;
				}
			}
		} while (nonUnique);

		return uuid;
	}

	public static void addAccount(Account anAcct) {
		accounts.add(anAcct);
	}

	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param pin
	 * @return
	 */
	public static Customer addUser(String firstName, String lastName, String pin) {
		// create a new User object
		Customer newUser = new Customer(firstName, lastName, pin);
		users.add(newUser);

		// create a savings account
		Account newAccount = new Account("Savings", newUser);
		Account newAccount1 = new Account("Checking", newUser);
		newUser.addAcount(newAccount);
		newUser.addAcount(newAccount1);
		addAccount(newAccount);
		addAccount(newAccount1);

		saveData();

		return newUser;
	}

	public static User userLogin(String userID, String pin) {

		for (User u : users) {
			if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
				return u;
			}
		}
		return null;
	}

	public static String getName() {
		return name;
	}

	public static void saveData() {
		ReadXmlDomParser.parseObjectToXML(users);
	}

	public static void readData() {
		users = ReadXmlDomParser.readFile();
	}


	public static List<Customer> listCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		for (User user : users) {
			if (user instanceof Customer) {
				customers.add((Customer) user);
			}
		}
		return customers;
	}

	public static boolean findUserWithId(String id) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUUID().equals(id)) {
				return true;
			}
		}
		return false;
	}

	public static boolean deleteUser(String userId) {
		Iterator<User> iterator = users.iterator();
		boolean check = false;
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getUUID().equals(userId)) {
				iterator.remove();
				check = true;
				break;
			}
		}
		if (check) {
			saveData();
		}
		return check;
	}

	public static boolean updateUserAdmin(String userId) {
		boolean check = false;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUUID().equals(userId)) {
				Staff staff = users.get(i).convertToAdmin();
				users.set(i, staff);
				saveData();
				check = true;
				break;
			}
		}
		if (check) {
			saveData();
		}
		return check;
	}
}