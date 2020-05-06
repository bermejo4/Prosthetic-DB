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

	public static void printPatientArrayList(ArrayList<Patient> list) {
		Iterator<Patient> it = list.iterator();
		Patient pat;
		while(it.hasNext()) {
			pat=(Patient) it.next();
			System.out.println(pat.toString());
		}
	}
	public static int checkIdAndListPPatient(ArrayList<Patient> list) {
		boolean check = false;
		Patient pat;
		int numId,id;
		Iterator<Patient> it = list.iterator();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		do {
			System.out.println("This is the available Prosthetic's list:\n");
			printPatientArrayList(list);
			id = takeInteger(reader, "Introduce the id:");
			while (it.hasNext()) {
				pat=(Patient) it.next();
				numId=pat.getId();
				if (numId == id) {
					check = true;
					break;
				}
			}
			System.out.println("This id is not in the available list. Try again.\n");
		}while(!check);
		return id;
	}
	public static void printProstheticArrayList(ArrayList<Prosthetic> list) {
		Iterator<Prosthetic> it = list.iterator();
		Prosthetic pros;
		while(it.hasNext()) {
			pros=(Prosthetic) it.next();
			System.out.println(pros.toString());
		}
	}
	public static int checkIdAndListProsthetic(ArrayList<Prosthetic> list) {
		boolean check = false;
		Prosthetic pros;
		int numId,id;
		Iterator<Prosthetic> it = list.iterator();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		do {
			System.out.println("This is the available Prosthetic's list:\n");
			printProstheticArrayList(list);
			id = takeInteger(reader, "Introduce the id:");
			while (it.hasNext()) {
				pros=(Prosthetic) it.next();
				numId=pros.getId();
				if (numId == id) {
					check = true;
					break;
				}
			}
			System.out.println("This id is not in the available list. Try again.\n");
		}while(!check);
		return id;
	}
	public static void printDoctorArrayList(ArrayList<Doctor> list) {
		Iterator<Doctor> it = list.iterator();
		Doctor doc;
		while(it.hasNext()) {
			doc=(Doctor) it.next();
			System.out.println(doc.toString());
		}
	}
	public static int checkIdAndListDoctor(ArrayList<Doctor> list) {
		boolean check = false;
		Doctor doc;
		int numId,id;
		Iterator<Doctor> it = list.iterator();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		do {
			System.out.println("This is the available Doctor's list:\n");
			printDoctorArrayList(list);
			id = takeInteger(reader, "Introduce the id:");
			while (it.hasNext()) {
				doc=(Doctor) it.next();
				numId=doc.getId();
				if (numId == id) {
					check = true;
					break;
				}
			}
			System.out.println("This id is not in the available list. Try again.\n");
		}while(!check);
		return id;
	}
	
	public static void printHospitalArrayList(ArrayList<Hospital> list) {
		Iterator<Hospital> it = list.iterator();
		Hospital hosp;
		while(it.hasNext()) {
			hosp=(Hospital) it.next();
			System.out.println(hosp.toString());
		}
	}
	
	public static int checkIdAndListHospital(ArrayList<Hospital> list) {
		boolean check = false;
		Hospital hosp;
		int numId,id;
		Iterator<Hospital> it = list.iterator();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		do {
			System.out.println("This is the available Hospital's list:\n");
			printHospitalArrayList(list);
			id = takeInteger(reader, "Introduce the id:");
			while (it.hasNext()) {
				hosp=(Hospital) it.next();
				numId=hosp.getId();
				if (numId == id) {
					check = true;
					break;
				}
			}
			System.out.println("This id is not in the available list. Try again.\n");
		}while(!check);
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
