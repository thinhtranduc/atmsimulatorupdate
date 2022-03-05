package com.btmglobal.simulator.atm.data;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.btmglobal.simulator.atm.model.Account;
import com.btmglobal.simulator.atm.model.Customer;
import com.btmglobal.simulator.atm.model.Staff;
import com.btmglobal.simulator.atm.model.User;
import com.btmglobal.simulator.atm.module.Transaction;

public class ReadXmlDomParser {
	
	 private static final String FILEPATH = "src/com/btmglobal/simulator/atm/data/users.xml";
	 
	 public static void checkFileExist() {
			File myObj = new File(FILEPATH);
			if (!myObj.exists()) {
				try {
					myObj.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	 
	public static ArrayList<User> readFile() {
		checkFileExist();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		ArrayList<User> users = new ArrayList<User>();
		try {
			 dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			 DocumentBuilder db = dbf.newDocumentBuilder();
			 Document doc = db.parse(new File(FILEPATH));
			 doc.getDocumentElement().normalize();
			 NodeList list = doc.getElementsByTagName("user");
			 for (int temp = 0; temp < list.getLength(); temp++) {
				 Node node = list.item(temp);
				 if (node.getNodeType() == Node.ELEMENT_NODE) {
					 Element element = (Element) node;
					 String type = element.getElementsByTagName("type").item(0).getTextContent().trim();
					 String uuid = element.getElementsByTagName("uuid").item(0).getTextContent().trim();
					 String firstname = element.getElementsByTagName("firstName").item(0).getTextContent().trim();
					 String lastname = element.getElementsByTagName("lastName").item(0).getTextContent().trim();
					 String pin = element.getElementsByTagName("pin").item(0).getTextContent().trim();
					 if(type.equals("0")||type.equals("1")) {
						 User user;
						 if(type.equals("0")) {
							 user = new Staff(firstname, lastname, Base64.getDecoder().decode(pin), uuid);
							 users.add(user);
						 } else {
							 NodeList accList = element.getElementsByTagName("account");
							 ArrayList<Account> accounts = new ArrayList<Account>();
							 for (int i = 0; i < accList.getLength(); i++) {
								 Node account = accList.item(i);
								 if (account.getNodeType() == Node.ELEMENT_NODE) {
									 Element accElement = (Element) account;
									 String accUUID = accElement.getElementsByTagName("uuid").item(0).getTextContent().trim();
									 String accName = accElement.getElementsByTagName("name").item(0).getTextContent().trim();
									 ArrayList<Transaction> transactions = new ArrayList<Transaction>();
									 Account accObj = new Account(accUUID, accName, transactions);
									 accounts.add(accObj);
									 NodeList transactionList = accElement.getElementsByTagName("transaction");
									 for (int j = 0; j < transactionList.getLength(); j++) {
										 Node trans = transactionList.item(j);
										 if (trans.getNodeType() == Node.ELEMENT_NODE) {
											 Element transElement = (Element) trans;
											 String amount = transElement.getElementsByTagName("amount").item(0).getTextContent().trim();
											 String memo = transElement.getElementsByTagName("memo").item(0).getTextContent().trim();
											 String timestamp = transElement.getElementsByTagName("timestamp").item(0).getTextContent().trim();
											 Date date1=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(timestamp);  
											 accObj.addTransaction(Double.parseDouble(amount), memo, date1);
										 }
									 }
								 }
							 }
							 user = new Customer(firstname, lastname, Base64.getDecoder().decode(pin), uuid, accounts);
							 users.add(user);
						 }
					 }
				 }
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public static void parseObjectToXML(List<User> users) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBulder = docFactory.newDocumentBuilder();
			Document doc = docBulder.newDocument();
			Element rootElement = doc.createElement("users");
			doc.appendChild(rootElement);
			for(User user:users) {
				Element userEle = doc.createElement("user");
				rootElement.appendChild(userEle);
				Element uuid = doc.createElement("uuid");
				uuid.appendChild(doc.createTextNode(user.getUUID()));
				Element firstName = doc.createElement("firstName");
				firstName.appendChild(doc.createTextNode(user.getFirstName()));
				Element lastName = doc.createElement("lastName");
				lastName.appendChild(doc.createTextNode(user.getLastName()));
				Element pin = doc.createElement("pin");
				pin.appendChild(doc.createTextNode(Base64.getEncoder().encodeToString(user.getPinHash())));
				Element type = doc.createElement("type");
				type.appendChild(doc.createTextNode((user instanceof Customer ? "1" : "0")));
				userEle.appendChild(uuid);
				userEle.appendChild(firstName);
				userEle.appendChild(lastName);
				userEle.appendChild(pin);
				userEle.appendChild(type);
				if(user instanceof Customer) {
					for(Account account : ((Customer) user).getAccounts()) {
						Element accountEle = doc.createElement("account");
						userEle.appendChild(accountEle);
						Element accuuid = doc.createElement("uuid");
						accountEle.appendChild(accuuid);
						accuuid.appendChild(doc.createTextNode(account.getUUID()));
						Element accname = doc.createElement("name");
						accname.appendChild(doc.createTextNode(account.getName()));
						accountEle.appendChild(accname);
						for(Transaction transaction : account.getTransactions()) {
							Element transactionEle = doc.createElement("transaction");
							accountEle.appendChild(transactionEle);
							Element amount = doc.createElement("amount");
							transactionEle.appendChild(amount);
							amount.appendChild(doc.createTextNode(String.valueOf(transaction.getAmount())));
							Element memo = doc.createElement("memo");
							transactionEle.appendChild(memo);
							memo.appendChild(doc.createTextNode(transaction.getMemo()));
							Element timestamp = doc.createElement("timestamp");
							transactionEle.appendChild(timestamp);
							String pattern = "MM/dd/yyyy HH:mm:ss";
							DateFormat df = new SimpleDateFormat(pattern);
							String dateFormat = df.format(transaction.getTimestamp());
							timestamp.appendChild(doc.createTextNode(dateFormat));
						}
					}
				}
			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(new File(FILEPATH));
            transformer.transform(domSource, streamResult);
		} catch (ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}
	}
	 

}
