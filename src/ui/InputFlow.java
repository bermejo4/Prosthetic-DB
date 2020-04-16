package ui;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class InputFlow {

	public static boolean CheckOption(int num, int max) {
		if (num > max || num < 0) {
			System.out.println("This number is not an option");
			System.out.println("The number must be between 1 and " + max);
			System.out.println("Try again!");
			return true;
		} else {
			return false;
		}
	}

	public static int takeInteger(BufferedReader reader, String text) {
		boolean check = false;
		int data = 0;

		System.out.println(text);

		while (!check || data < 0) {
			try {
				data = Integer.parseInt(reader.readLine());
				check = true;
			} catch (IOException ex) {
				System.out.println("Error reading");
			} catch (NumberFormatException nfex) {
				System.out.println("You have not introduced a Integer, you must do it.");
				System.out.println(text);
			}

		}
		return data;
	}

	public static float takeFloat(BufferedReader reader, String text) {
		boolean check = false;
		float data = 0;

		System.out.println(text);

		while (!check || data < 0) {
			try {
				data = Float.parseFloat(reader.readLine());
				check = true;
			} catch (IOException ex) {
				System.out.println("Error reading");
			} catch (NumberFormatException nfex) {
				System.out.println("You have not introduced a Float, you must do it.");
				System.out.println(text);
			}

		}
		return data;
	}

	public static String takeGender(BufferedReader reader, String text) {
		String gender = " ";
		String answer;
		try {
			do {
				System.out.println(text + "(m/f)");
				answer = reader.readLine();
				switch (answer) {
				case "M":
				case "m":
					gender = "Male";
					break;
				case "F":
				case "f":
					gender = "Female";
					break;
				default:
					System.out.println("The data introduced is NOT correct.");
					System.out.println("Please introduce m in case of Male or f in case of Female");
					System.out.println("Try again.");
					gender = " ";
					break;
				}
			} while (gender.equals(" "));
		} catch (IOException ex) {
			System.out.println("Error reading");
		}
		return gender;
	}

	public static boolean areYouSure(BufferedReader reader, String text) {
		boolean resp = false;
		boolean loop = false;
		String answer = "";
		try {
			do {
				System.out.println(text+" (y/n):");
				answer = reader.readLine();
				if (answer.equals("y") || answer.equals("n")) {
					loop = false;
					if (answer.contains("y")) {
						resp = true;
					} else {
						resp = false;
					}
				}else {
					System.out.println("The answer is not correct. Try again.");
					loop=true;
				}

			} while (loop);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return resp;
	}

	public static String takeTelephone(BufferedReader reader, String text) {
		// check a telephone number
		// and return a number telephone without +34 prefix if the user have added.
		String num = "error in takeTelephone()";
		boolean check = true;
		char cad[];
		try {
			do {
				System.out.println(text + " (without spaces)");
				num = reader.readLine();
				cad = num.toCharArray();
				check = false;
				for (int i = 0; i < num.length(); i++) {
					// System.out.println(cad[i] + " y... "+Character.isDigit(cad[i]) );
					if (Character.isDigit(cad[i]) || num.substring(0, 1).equals("+")
							|| !Character.isSpaceChar(cad[i])) {

					} else {
						check = true;
					}
				}
				if (check == true) {
					System.out.println("You don't introduce a telephone number.");
					System.out.println("Please introduce numbers.");
				}
			} while (check);
			if (num.substring(0, 1).contains("+")) {
				// short to a number without +34
				num = num.substring(3, num.length());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return num;

	}
	
	public static String takeDimension(){
		String dimension="";
		BufferedReader reader;
		reader = new BufferedReader(new InputStreamReader(System.in));
		float long_v=takeFloat(reader, "Introduce the long(cm):");
		float wide=takeFloat(reader, "Introduce the wide(cm):");
		float deep=takeFloat(reader, "Introduce the deep(cm):");
		dimension=long_v+"x"+wide+"x"+deep;
		return dimension;
	}
	
	public boolean checkIdAndList(int id, ArrayList<Integer> list) {
		boolean check=true;
		Iterator<Integer> it = list.iterator();
		while(it.hasNext()) {
			int num=it.next();
			if(num==id) {
				check=false;
			}
		}
		return check;
	}
	
}
