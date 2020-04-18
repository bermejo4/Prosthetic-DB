package ui;

import java.io.*;
import java.util.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import db.classes.*;
import db.inteface.*;
import pojos.Doctor;
import pojos.Patient;
import pojos.Prosthetic;
import pojos.Hospital;

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

		boolean loggeado = false;

		reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("WELCOME! THIS IS A PROSTHETIC DATABASE");
		dbManagerInterface.createTables();
		Patient patientUser = new Patient();
		Doctor doctorUser = new Doctor();//crear
		Hospital hospitalUser = new Hospital();
		boolean userOcuppation;
		
		while (true) {
			System.out.println("Who are you?");
			System.out.println("1.Patient");
			System.out.println("2.Doctor");
			System.out.println("3.Biomedical Engineer");
			System.out.println("4.Hospital");
			System.out.println("\n5.Exit");
			System.out.println("---------\n");

			num = requestNumber(5);
//------------------------------ while user ocupattion//num=1// en cada menu poner un back to the global menu
			
			switch (num) {
			case 1: // patient
				System.out.println("PATIENT MENU:");
				System.out.println("What do you want to do?");
				System.out.println("1.Register.");
				System.out.println("2.Login.");
				System.out.println("3.Select a Hospital.");
				System.out.println("4.View appointments.");
				System.out.println("\n5.Back to choose other user to the main menu.");
				num = requestNumber(5);
				switch (num) {
				case 1: // Register
					registerMenu();
					break;
				case 2: // Login
					loginMenu();
					loggeado = true;
					break;
				case 3:
					System.out.println(
							"Choose a Hospital between the list of available hospitals:\n");
					//showHospitals();
					num = requestNumber(5);
					System.out.println("Introduce the id of the chosen hospital:\n ");
					
					
					break;
				case 4:
					// Voy a dejar este hasta que se haga lo del login para que funcione algo.
					if (!loggeado) {
						System.out.println("You need to login first.");
						loginMenu();
					} else {
						float telephone = InputFlow.takeFloat(reader, "Introduce your telephone number: ");
						patientManagerInterface.viewDate(telephone);
						break;
					}

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
				num = requestNumber(6);
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
					selectDayOfFitting();
					break;
				case 5: // Search a patient file
					searchPatientByTelephone();
					break;
				case 6: // Add/modify/Delete patient.
					addModifyDelete();
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
					buyProsthetic();
					break;
				}
				break;
			default:// Exit
				System.exit(0);
			}
			pressEnter();
		}

	}

	public static int requestNumber(int max) {
		// int max is the maximun option that is acceptable
		int num;
		do {

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

	public static void register() {
		System.out.println("Introduce your name:");
		System.out.println("Introduce your lastname:");
		System.out.println("Introduce your telephone:");
		System.out.println("Introduce a password:");
		System.out.println("Introduce again the password:");
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

	public static void searchProstheticMenu() {
		System.out.println("Select the type of search that dou you want to do?");
		System.out.println("By...");
		System.out.println("1.Material");
		System.out.println("2.Type.");
		System.out.println("3.Dimension.");
		System.out.println("4.Failures.");
	}

	public static void searchPatientByTelephone() {
		List<Patient> coiList = new ArrayList<Patient>();
		Patient pat;
		String tel = InputFlow.takeTelephone(reader,
				"Introduce the telephone number of the patient whose want to search:");
		coiList = doctorManagerInterface.searchPatientByTelephone(tel);
		Iterator it = coiList.iterator();
		while (it.hasNext()) {
			pat = (Patient) it.next();
			System.out.println(pat.toString());
			System.out.println("");
		}
		

	}
	

	public static void addModifyDelete() throws Exception {
		System.out.println("What dou you want to do?");
		System.out.println(" 1.- Add patient");
		System.out.println(" 2.- Modify patient");
		System.out.println(" 3.- Delete patient");

		switch (requestNumber(3)) {
		case 1: // Add Patient
			addPatient(false);
			break;
		case 2: // Modify Patient
			addPatient(true); // you are modifying also the dof
			break;
		case 3: // Delete Patient
			DeletePatient();
			break;

		}
	}
	

	public static void DeletePatient() {
		searchPatientByTelephone();
		System.out.println("\nThis have been the coincidenses for the phone introduced.");
		System.out.println("Now Introduce the Id number of the patient who want delete:\n");
		int num_id = InputFlow.takeInteger(reader, "Id number:");
		Patient pac = doctorManagerInterface.searchSpecificPatientById(num_id);
		System.out.println("You have choose this patient:\n");
		if (InputFlow.areYouSure(reader, "Are you sure that do you want to delete this patient?")) {
			doctorManagerInterface.delete(pac);
		}
	}

	
	public static void addPatient(boolean mood) throws Exception {
		int num_id = 0;
		if (mood) {
			searchPatientByTelephone();
			System.out.println("\nThis have been the coincidenses for the phone introduced.");
			System.out.println("Now Introduce the Id number of the patient who want modify:\n");
			num_id = InputFlow.takeInteger(reader, "Id number:");
		}
		Patient pat;
		System.out.println("Name:");
		String name = reader.readLine();
		System.out.println("Lastname:");
		String lastname = reader.readLine();
		System.out.println("Phone number:");
		String telephone = InputFlow.takeTelephone(reader, "Introduce a telephone: ");
		// dob
		System.out.println("Day of Birthday (yyyy-MM-dd): ");
		String dob = reader.readLine();
		LocalDate dayofbirth = LocalDate.parse(dob, formatter);
		if (mood) {// If you are modifying the patient you can also modify the day of fitting
			// dof
			System.out.println("Day of Fitting (yyyy-MM-dd)");
			String dof = reader.readLine();
			LocalDate dayoffitting = LocalDate.parse(dof, formatter);
		}

		System.out.println("Gender:");
		String gender = InputFlow.takeGender(reader, "");
		System.out.println("Problem:");
		String problem = reader.readLine();
		System.out.println("Address:");
		String address = reader.readLine();
		int doctor_id = InputFlow.takeInteger(reader, "Doctor id: ");
		if (mood) {
			pat = new Patient(name, lastname, telephone, Date.valueOf(dayofbirth), gender, problem, address, doctor_id);
			doctorManagerInterface.modify(pat, Date.valueOf(dayofbirth), num_id);
		} else {
			pat = new Patient(name, lastname, telephone, Date.valueOf(dayofbirth), gender, problem, address, doctor_id);
			doctorManagerInterface.addPatient(pat);
		}

	}

	public static void selectDayOfFitting() throws IOException {
		searchPatientByTelephone();
		System.out.println("\nThis have been the coincidenses for the phone introduced.");
		System.out.println("Now Introduce the Id number of the patient who want assign a date of fitting:\n");
		int num_id = InputFlow.takeInteger(reader, "Id number:");
		Patient pac = doctorManagerInterface.searchSpecificPatientById(num_id);
		System.out.println("You have choose this patient:\n");
		System.out.println(pac.toString() + "\n");
		System.out.println("\nNow introduce the Day of Fitting (yyyy-MM-dd):");
		String dof = reader.readLine();
		LocalDate dayoffitting = LocalDate.parse(dof, formatter);
		Date dateToPass = Date.valueOf(dayoffitting);
		doctorManagerInterface.assignDOF(dateToPass, pac);

	}

	public static void searchProsthetic() {
		Prosthetic prost;
		searchProstheticMenu();
		int option = requestNumber(4);
		List<Prosthetic> prostheticList = new ArrayList<Prosthetic>();
		try {
			switch (option) {
			case 1:// material
				String materialpassed = reader.readLine();
				prostheticList=doctorManagerInterface.selectProsthetic("material", materialpassed);
				break;
			case 2:// type
				String typepassed = reader.readLine();
				prostheticList=doctorManagerInterface.selectProsthetic("type", typepassed);
				break;
			case 3:// dimension
				String dimensionpassed = InputFlow.takeDimension();
				prostheticList=doctorManagerInterface.selectProsthetic("dimension", dimensionpassed);
				break;
			case 4:// failures
				String failurespassed = reader.readLine();
				prostheticList=doctorManagerInterface.selectProsthetic("failures", failurespassed);
				break;
			}
			Iterator it =prostheticList.iterator();
			while (it.hasNext()) {
				prost = (Prosthetic) it.next();
				if(prost.isAvailable()) {
				System.out.println(prost.toString());
				System.out.println("");
				}
			System.out.println("\nThis have been the coincidenses for your search.");
			System.out.println("Now Introduce the Id number of the prosthetic which you want to select:\n");
			int num_id_prost = InputFlow.takeInteger(reader, "Id number:");
			System.out.println("And now introduce the id_number of your patient:");
			int num_id_pat = InputFlow.takeInteger(reader, "Id number:");
			doctorManagerInterface.assignProstheticToPatient(num_id_prost, num_id_pat);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void buyProsthetic() throws Exception {
		int hospital_id = hospitalUser.getId(); 
		// Show the list of all available prosthetic on that specific hospital
		List<Prosthetic> prostheticList = hospitalManagerInterface.showProsthetics();
		for (Prosthetic prosthetic : prostheticList) {
			System.out.println(prosthetic);
		}
		// Ask for the Id of the prosthetic you want to buy
		System.out.println("Please, type the ID of the prosthetic you want to buy: ");
		int prosthetic_id = Integer.parseInt(reader.readLine());

		// the specific hospital buys the prosthetic choosed
		hospitalManagerInterface.buy(hospital_id, prosthetic_id);

	}

	public static void pressEnter() {
		System.out.println("Press enter to go to the main menu and continue...");
		try {
			String nothing;
			nothing = reader.readLine();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
