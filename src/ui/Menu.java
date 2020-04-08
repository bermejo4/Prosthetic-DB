package ui;

import java.io.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import db.classes.*;
import db.inteface.*;
import pojos.Patient;

public class Menu {

	// DBManagers
	private static DBManagerInterface dbManagerInterface;
	private static DoctorManagerInterface doctorManagerInterface;
	private static PatientManagerInterface patientManagerInterface;
	private static HospitalManagerInterface hospitalManagerInterface;

	// Used for parsing dates
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private static BufferedReader reader;
	private static int num;

	public static void main(String[] args) throws Exception {
		// Connect with the database.
		dbManagerInterface = new DBManager();
		dbManagerInterface.connect();
		doctorManagerInterface = dbManagerInterface.getDoctorManager();
		patientManagerInterface = dbManagerInterface.getPatientManager();
		hospitalManagerInterface = dbManagerInterface.getHospitalManager();

		reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("telefono cogío: "+ InputFlow.takeTelephone(reader, "Introduce a telephone: "));
		
		System.out.println("WELCOME! THIS IS A PROSTHETIC DATABASE");
		dbManagerInterface.createTables();
		while (true) {
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
					//Aqui introducimos un float del telefono, pues lo guardo y lo uso en view appointments
					break;
				case 3: // Select Hospital
					break;
				case 4: 
					//if(login==true) {
						//uso el mismo float introducido en el login
						//PatientManager.viewDate(telephone);
						//break;
					//}
					//else {
						float telephone = InputFlow.takeFloat(reader, "Introduce your telephone number: ");
						patientManagerInterface.viewDate(telephone);
						break;
					//}
				}
				break;
			case 2: // Doctor
				System.out.println("DOCTOR MENU:");
				System.out.println("What do you want to do?");
				System.out.println("1.Register.");
				System.out.println("2.Login.");
				System.out.println("3.Select a Prosthetic.");
				System.out.println("4.Select date of fitting.");
				System.out.println("5.Search a patients file.");
				System.out.println("6.Add/Modify/Delete a patient.");
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
				System.out.println("3.Upload Prosthetic information");
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
				num = requestNumber(4);
				switch (num) {
				case 1: // Register
					registerHospitalMenu();
					break;
				case 2: // Login
					loginMenu();
					break;
				case 3: // Buy a prosthetic
					break;
				}
				break;
			default:
			}
		}
		// float number = InputFlow.takeFloat(reader, "Introduce a float: ");

	}

	public static int requestNumber(int max) {
		// int max is the maximun option that is acceptable
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
		System.out.println("LOGIN MENU:");
		System.out.println("1.Telephone.");
		System.out.println("2.Password.");
	}

	public static void registerHospitalMenu() {
		System.out.println("REGISTER MENU:");
		System.out.println("1.Name of Hospital.");
		System.out.println("2.Location.");
		System.out.println("3.Telephone.");
		System.out.println("4.Password.");
	}
	
	public static void searchPatientByTelephone() {
		float tel=InputFlow.takeFloat(reader, "Introduce the telephone number of the patient whose want to search:");
		
		
	}
	
	public static void addModifyDelete() {
		System.out.println("What dou you want to do?");
		System.out.println(" 1.- Add patient");
		System.out.println(" 2.- Modify patient");
		System.out.println(" 3.- Delete patient");
		
		switch(requestNumber(3)) {
		case 1: //Add Patient
			
		break;
		case 2: //Modify Patient
		break;
		case 3: //Delete Patient
		break;
		
		}
	}
	
	public static void addPatient() throws Exception{
		System.out.println("Name:");
		String name = reader.readLine();
		System.out.println("Lastname:");
		String lastname= reader.readLine();
		System.out.println("Phone number:");
		String telephone=reader.readLine();
		//dob
		System.out.println("Day of Birthday (yyyy-MM-dd): ");
		String dob=reader.readLine();
		LocalDate dayofbirth= LocalDate.parse(dob, formatter);
		//dof
		System.out.println("Day of Fitting (yyyy-MM-dd)");
		String dof=reader.readLine();
		LocalDate dayoffitting=LocalDate.parse(dof,formatter);
		System.out.println("Gender:");
		String gender=reader.readLine();
		System.out.println("Problem:");
		String problem=reader.readLine();
		System.out.println("Address:");
		String address=reader.readLine();
		int doctor_id=InputFlow.takeInteger(reader, "Doctor id: ");
		
		Patient pat= new Patient(name, lastname, telephone, Date.valueOf(dayofbirth), Date.valueOf(dayoffitting), gender, problem, address, doctor_id);
		doctorManagerInterface.addPatient(pat);
		
	}

}
