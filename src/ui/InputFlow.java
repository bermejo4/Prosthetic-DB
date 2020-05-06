package ui;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;

import pojos.*;

public class InputFlow {

	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
				System.out.println("You have not introduced a valid number. Try again.");
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

	public static boolean takeAvailable(String choice) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		boolean answer = true;
		boolean loop = false;

		try {
			do {
				if (loop == true) {
					choice = reader.readLine();

				}

				if (choice.equalsIgnoreCase("a") || choice.equalsIgnoreCase("na")) {
					loop = false;
					if (choice.contains("n")) {
						answer = false;

					} else {
						answer = true;
					}

				} else {
					System.out.println("The answer given is not correct. Try again.");
					System.out.println("Please introduce 'A' for available or 'NA' for not available");
					loop = true;
				}

			} while (loop);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return answer;

	}

	public static boolean areYouSure(BufferedReader reader, String text) {
		boolean resp = false;
		boolean loop = false;
		String answer = "";
		try {
			do {
				System.out.println(text + " (y/n):");
				answer = reader.readLine();
				if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n")) {
					loop = false;
					if (answer.contains("y")) {
						resp = true;
					} else {
						resp = false;
					}
				} else {
					System.out.println("The answer is not correct. Try again.");
					loop = true;
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

					if (Character.isDigit(cad[i]) || cad[i] == '+') {

					} else if (Character.isSpaceChar(cad[i])) {
						check = true;
						break;
					} else {
						check = true;
						break;
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

	public static String takeDimension() {
		String dimension = "";
		BufferedReader reader;
		reader = new BufferedReader(new InputStreamReader(System.in));
		float long_v = takeFloat(reader, "Introduce the long(cm):");
		float wide = takeFloat(reader, "Introduce the wide(cm):");
		float deep = takeFloat(reader, "Introduce the deep(cm):");
		dimension = long_v + "x" + wide + "x" + deep;
		return dimension;
	}

	public static LocalDate takeDate(BufferedReader reader, String text) {
		boolean check = false;
		String data = "";
		LocalDate day = LocalDate.now();
		System.out.println(text);
		while (!check || data.equals("")) {
			try {
				data = reader.readLine();
				day = LocalDate.parse(data, formatter);
				check = true;
			} catch (IOException ex) {
				System.out.println("Error reading");
			} catch (DateTimeParseException nfex) {
				System.out.println("You have not introduced a valid Date. Try again.");
				System.out.println(text);
			}
		}
		return day;
	}

	public int checkIdAndListPatient(int id, ArrayList<Patient> list) {
		boolean check = true;
		Iterator<Patient> it = list.iterator();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		do {
			while (it.hasNext()) {
				int num = it.next().getId();
				if (num == id) {
					check = false;
				}
			}
			if (check == true) {
				System.out.println("This is the available Patient's list:\n");
				while (it.hasNext()) {
					System.out.println(it.next().toString());
					int num2 = takeInteger(reader, "Introduce the id again:");
					id = num2;
					while (it.hasNext()) {
						int num = it.next().getId();
						if (num == id) {
							check = false;
						}
					}
				}
			}
		} while (check);
		
		return id;
	}
	public int checkIdAndListProsthetic(int id, ArrayList<Prosthetic> list) {
		boolean check = true;
		Iterator<Prosthetic> it = list.iterator();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		do {
			while (it.hasNext()) {
				int num = it.next().getId();
				if (num == id) {
					check = false;
				}
			}
			if (check == true) {
				System.out.println("This is the available Patient's list:\n");
				while (it.hasNext()) {
					System.out.println(it.next().toString());
					int num2 = takeInteger(reader, "Introduce the id again:");
					id = num2;
					while (it.hasNext()) {
						int num = it.next().getId();
						if (num == id) {
							check = false;
						}
					}
				}
			}
		} while (check);
		
		return id;
	}
	public int checkIdAndListDoctor(int id, ArrayList<Doctor> list) {
		boolean check = true;
		Iterator<Doctor> it = list.iterator();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		do {
			while (it.hasNext()) {
				int num = it.next().getId();
				if (num == id) {
					check = false;
				}
			}
			if (check == true) {
				System.out.println("This is the available Patient's list:\n");
				while (it.hasNext()) {
					System.out.println(it.next().toString());
					int num2 = takeInteger(reader, "Introduce the id again:");
					id = num2;
					while (it.hasNext()) {
						int num = it.next().getId();
						if (num == id) {
							check = false;
						}
					}
				}
			}
		} while (check);
		
		return id;
	}
	

	public static byte[] takePasswordAndHashIt(BufferedReader reader, String text) {
		System.out.println(text);
		byte[] returnValue = null;
		try {
			String password = reader.readLine();
			// Create the password's hash
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			returnValue = md.digest();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnValue;

	}

	public static String takeString(BufferedReader reader, String text) {
		String string = "";
		System.out.println(text);
		try {
			string = reader.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return string;
	}

}
