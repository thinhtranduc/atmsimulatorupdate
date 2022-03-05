package com.btmglobal.simulator.atm.model;

public class Staff extends User {

	public Staff(String firstName, String lastName, String pin) {
		super(firstName, lastName, pin);
	}

	public Staff(String firstName, String lastName, byte pinHash[], String uuid) {
		super(firstName, lastName, pinHash, uuid);
	}

	@Override
	public String toString() {
		return super.toString() + ", type: 0";

	}

}
