package config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {


        public String getFileData(String dataName) {

		StringBuffer buffer = new StringBuffer();

		try (Scanner sc = new Scanner(new File(dataName))) {

			while (sc.hasNextLine()) {
				buffer.append(sc.nextLine() + "\n");
			}
		} catch (FileNotFoundException e) {
			System.err.print(dataName + " not found");
		}

		return buffer.toString();
	}
	
	public static File getExternalConfig() {
		return new File("config.txt");

	}
	
	public static String getAlias() {

		String data = "";

		try {
			Scanner sc = new Scanner(new File("alias.txt"));

			while (sc.hasNextLine()) {
				data += sc.nextLine() + "\n";
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.print("Cant find the alias.txt");
			e.printStackTrace();
		}

		return data;

	}
	
	public String getColors() {

		String data = "";

		try {
			Scanner sc = new Scanner(new File("colors.txt"));

			while (sc.hasNextLine()) {
				data += sc.nextLine() + "\n";
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.print("Cant find the colors.txt");
			e.printStackTrace();
		}

		return data;

	}

	public String getConfig() {

		String data = "";

		try {
			Scanner sc = new Scanner(getExternalConfig());

			while (sc.hasNextLine()) {
				data += sc.nextLine() + "\n";
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.print("Cant find the config.txt");
			e.printStackTrace();
		}

		return data;

	}

}
