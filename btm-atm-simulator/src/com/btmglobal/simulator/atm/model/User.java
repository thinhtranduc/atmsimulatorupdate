package com.btmglobal.simulator.atm.model;

import com.btmglobal.simulator.atm.module.Bank;

public abstract class User extends Person {
	private String uuid;
//	private byte pinHash[];
	private String pin;

	public User(String firstName, String lastName, String pin) {
		// set user's name
		super(firstName, lastName);
		this.pin = pin;
//		//security
//		try {
//		MessageDigest md = MessageDigest.getInstance("MD5");
//		this.pinHash = md.digest(pin.getBytes());
//		} catch (NoSuchAlgorithmException e) {
//			System.err.println("error");
//			e.printStackTrace();
//			System.exit(1);
//		}
		// get ID for the user
		this.uuid = Bank.getNewUserUUID();
	}

	public User(String firstName, String lastName, String pin, String uuid) {
		// set user's name
		super(firstName, lastName);
		this.pin = pin;
//		//security
//		try {
//		MessageDigest md = MessageDigest.getInstance("MD5");
//		this.pinHash = md.digest(pin.getBytes());
//		} catch (NoSuchAlgorithmException e) {
//			System.err.println("error");
//			e.printStackTrace();
//			System.exit(1);
//		}
		// get ID for the user
		this.uuid = uuid;
	}

	public String getUUID() {
		return this.uuid;
	}

	/**
	 * 
	 * @param aPin
	 * @return
	 */
	public boolean validatePin(String aPin) {
//		try {
//			MessageDigest md = MessageDigest.getInstance("MD5");
//			return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash);
//		} catch (NoSuchAlgorithmException e) {
//			System.err.println("error");
//			e.printStackTrace();
//			System.exit(1);
//		}

//		return false;
		return aPin.equals(pin);
	}

	protected String getUuid() {
		return uuid;
	}

	protected String getPin() {
		return pin;
	}

	public Staff convertToAdmin() {
		return new Staff(firstName, lastName, pin, uuid);
	}
}
