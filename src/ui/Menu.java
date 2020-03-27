package ui;

import java.io.*;

public class Menu {

	private static BufferedReader reader;
	private static int num;

	public static void main(String[] args) throws Exception {
		reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("WELCOME! THIS IS A PROSTHETIC DATABASE");
		System.out.println("Who are you?");
		System.out.println("1.Patient");
		System.out.println("2.Doctor");
		System.out.println("3.Biomedical Engineer");
		System.out.println("4.Hospital");

		num = requestNumber(4);

		switch (num) {
		case 1: // patient
			System.out.println("PATIENT MENU:");
			System.out.println("What do you want to do?");
			System.out.println("1.Register.");
			System.out.println("2.Login.");
			System.out.println("3.Select a Hospital.");
			System.out.println("4.View appointments.");
			num = requestNumber(4);
			switch (num) {
			case 1: // Register
				registerMenu();
				break;
			case 2: // Login
				loginMenu();
				break;
			case 3: // Select Hospital
				break;
			case 4: // View appointments
				break;
			}
			break;
		case 2: // Doctor
			System.out.println("DOCTOR MENU:");
			System.out.println("What do you want to do?");
			System.out.println("1.Register.");
			System.out.println("2.Login.");
			System.out.println("3.Select a Prosthetic.");
			System.out.println("4.Date of fitting.");
			System.out.println("5.Search a patients file.");
			num = requestNumber(5);
			switch (num) {
			case 1: // Register
				registerMenu();
				break;
			case 2: // Login
				loginMenu();
				break;
			case 3: // Select prosthetic
				break;
			case 4: // date of fitting
				break;
			case 5: // Search a patient file
				break;
			}
			break;
		case 3: // Biomedical Engineer
			System.out.println("BIOMEDICAL ENGINEER MENU:");
			System.out.println("What do you want to do?");
			System.out.println("1.Register.");
			System.out.println("2.Login.");
			System.out.println("3.Upload Prothesis information");
			num = requestNumber(3);
			switch (num) {
			case 1: // Register
				registerMenu();
				break;
			case 2: // Login
				loginMenu();
				break;
			case 3: // Upload Prosthetic information
				break;
			}
			break;
		case 4: // Hospital
			System.out.println("HOSPITAL MENU:");
			System.out.println("What do you want to do?");
			System.out.println("1.Register.");
			System.out.println("2.Login.");
			System.out.println("3.Buy a Prosthetic.");
			num=requestNumber(4);
				switch(num) {
				case 1: //Register
					registerHospitalMenu();
					break;
				case 2: //Login
					loginMenu();
					break;
				case 3: //Buy a prosthetic
					break;
				}
			break;
		default:
		}

		// float number = InputFlow.takeFloat(reader, "Introduce a float: ");

	}

	public static int requestNumber(int max) {
		//int max is the maximun option that is acceptable
		int num;
		do {
			max = 4;
			num = InputFlow.takeInteger(reader, "Introduce the number: ");

		} while (InputFlow.CheckOption(num, max));

		return num;
	}
	
	public static void registerMenu() {
		System.out.println("REGISTER MENU:");
		System.out.println("1.Name.");
		System.out.println("2.Lastname.");
		System.out.println("3.Telephone.");
		System.out.println("4.Password.");
	}
	
	public static void loginMenu() {
		System.out.println("REGISTER MENU:");
		System.out.println("1.Telephone.");
		System.out.println("2.Password.");
	}
	
	public static void registerHospitalMenu(){
		System.out.println("REGISTER MENU:");
		System.out.println("1.Name of Hospital.");
		System.out.println("2.Location.");
		System.out.println("3.Telephone.");
		System.out.println("4.Password.");
	}
	

}
