package com.btmglobal.simulator.atm.main;

import com.btmglobal.simulator.atm.controller.LoginController;
import com.btmglobal.simulator.atm.module.Bank;

public class App {

	public static void main(String[] args) {
		new Bank("BTM Bank");
		LoginController controller = new LoginController();
		controller.start();
	}

}