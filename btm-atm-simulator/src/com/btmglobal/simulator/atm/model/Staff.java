package com.btmglobal.simulator.atm.model;

public class Staff extends User {

	public Staff(String firstName, String lastName, String pin) {
		super(firstName, lastName, pin);
	}

	public Staff(String firstName, String lastName, String pin, String uuid) {
		super(firstName, lastName, pin, uuid);
	}

	@Override
	public String toString() {
		return String.format("firstName: %s, lastName: %s, pin: %s, uuid: %s, type: 0", firstName, lastName, getPin(),
				getUuid());

	}

}
