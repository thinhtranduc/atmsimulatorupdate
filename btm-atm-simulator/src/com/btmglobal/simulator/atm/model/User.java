package com.btmglobal.simulator.atm.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import com.btmglobal.simulator.atm.module.Bank;

public abstract class User extends Person {
	private String uuid;
	private byte pinHash[];
	private String pin;

	public User(String firstName, String lastName, String pin) {
		// set user's name
		super(firstName, lastName);
		this.setPin(pin);
		//security
		try {
		MessageDigest md = MessageDigest.getInstance("MD5");
		this.pinHash = md.digest(pin.getBytes());
		} catch (NoSuchAlgorithmException e) {
			System.err.println("error");
			e.printStackTrace();
			System.exit(1);
		}
		// get ID for the user
		this.uuid = Bank.getNewUserUUID();
	}

	public User(String firstName, String lastName, byte pinHash[], String uuid) {
		// set user's name
		super(firstName, lastName);
		this.pinHash = pinHash;
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
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash);
		} catch (NoSuchAlgorithmException e) {
			System.err.println("error");
			e.printStackTrace();
			System.exit(1);
		}

		return false;
	}

	protected String getUuid() {
		return uuid;
	}

	public Staff convertToAdmin() {
		return new Staff(firstName, lastName, pinHash, uuid);
	}
	
	@Override
	public String toString() {
		String s = Base64.getEncoder().encodeToString(pinHash);
		return String.format("firstName: %s, lastName: %s, pin: %s, uuid: %s", firstName, lastName, s, uuid);
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public byte[] getPinHash() {
		return pinHash;
	}

	public void setPinHash(byte[] pinHash) {
		this.pinHash = pinHash;
	}
	
	
}
