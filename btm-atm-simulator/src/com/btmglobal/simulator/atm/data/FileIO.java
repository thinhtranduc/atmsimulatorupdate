package com.btmglobal.simulator.atm.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileIO {
	public static void checkFileExist(String fileName, String folder) {
		String filePath = "";
		if (!folder.equals("")) {
			filePath += folder + "/";
		}
		if (fileName != "") {
			filePath += fileName;
		}
		File myObj = new File(filePath);
		if (!myObj.exists()) {
			try {
				myObj.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static String readFile(String fileName, String folder) {
		String filePath = "";
		if (!folder.equals("")) {
			filePath += folder + "/";
		}
		filePath += fileName;
		String content = "";
		try {
			File myObj = new File(filePath);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				content += myReader.nextLine() + "\n";
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return content;
	}

	public static void writeToFile(String fileContent, String fileName, String folder) {
		String filePath = "";
		if (!folder.equals("")) {
			filePath += folder + "/";
		}
		filePath += fileName;
		try {
			FileWriter myWriter = new FileWriter(filePath);
			myWriter.write(fileContent);
			myWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static List<String> readFromFile() {
		List<String> file = new ArrayList<String>();
		checkFileExist("user.txt", "src/com/btmglobal/simulator/atm/data");
		String fileContent = readFile("user.txt", "src/com/btmglobal/simulator/atm/data");
		String[] lines = fileContent.split("\n");
		for (String str : lines) {
			if (!str.equals("")) {
				file.add(str);
			}
		}
		return file;
	}

	public static void writeToFile(List<String> users) {
		String fileContent = "";
		for (String user : users) {
			fileContent += user;
			fileContent += "\n";
		}
		writeToFile(fileContent, "user.txt", "src/com/btmglobal/simulator/atm/data");
	}
}