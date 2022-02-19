package com.btmglobal.simulator.atm.view;

import java.util.List;

import com.btmglobal.simulator.atm.model.Customer;
import com.btmglobal.simulator.atm.model.Staff;

public class AdminView extends View{
	public int printUserMenu(Staff staff) {
		int choice = 0;
		do {
			System.out.printf("Welcome %s, what would you like to do?\n", staff.getFirstName());
			System.out.println(" 1) Show list customer");
			System.out.println(" 2) Manage customer");
			System.out.println();
			System.out.print("Enter your choice: ");
			boolean check = false;
			while (!check) {
				try {
					choice = Integer.parseInt(sc.nextLine());
					check = true;
					if (choice < 1 || choice > 2) {
						System.out.println("Invalid choice. Please choose 1-2");
						System.out.print("Enter your choice: ");
						check = false;
					}
				} catch (Exception e) {
					System.out.println("Invalid choice. Please choose 1-2");
					System.out.print("Enter your choice: ");
					check = false;
				}
			}
		} while (choice < 1 || choice > 5);
		return choice;
	}
	
	public void showListUser(List<Customer> customers) {
		System.out.println("---------------------------CUSTOMERS---------------------------");
		for(Customer customer: customers) {
			System.out.println(customer.toString());
		}
	}
	
	public int manageUser() {
		int choice = 0;
		do {
			System.out.println("What would you like to do?");
			System.out.println(" 1) Delete user");
			System.out.println(" 2) Set user to admin");
			System.out.println();
			System.out.print("Enter your choice: ");
			boolean check = false;
			while (!check) {
				try {
					choice = Integer.parseInt(sc.nextLine());
					check = true;
					if (choice < 1 || choice > 2) {
						System.out.println("Invalid choice. Please choose 1-2");
						System.out.print("Enter your choice: ");
						check = false;
					}
				} catch (Exception e) {
					System.out.println("Invalid choice. Please choose 1-2");
					System.out.print("Enter your choice: ");
					check = false;
				}
			}
		} while (choice < 1 || choice > 5);
		return choice;
	}
	
	public String getUserId() {
		System.out.println("---------------------------Manage---------------------------");
		System.out.print("Enter user ID: ");
		return sc.nextLine();
	}
}
