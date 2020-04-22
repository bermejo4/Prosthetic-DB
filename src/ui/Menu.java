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
import pojos.*;

public class Menu {

	// DBManagers
	private static DBManagerInterface dbManagerInterface;
	private static DoctorManagerInterface doctorManagerInterface;
	private static PatientManagerInterface patientManagerInterface;
	private static HospitalManagerInterface hospitalManagerInterface;
	private static BEManagerInterface biomedManagerInterface;

	// Used for parsing dates
	private static BufferedReader reader;
	private static int num;

	// Used for who is using the program
	private static Patient patientUser = new Patient();
	private static Doctor doctorUser = new Doctor();
	private static Hospital hospitalUser = new Hospital();
	private static Biomedical_Eng biomedical_engUser = new Biomedical_Eng();
	private static boolean userUsing;
	private static int userUsingNumber; // Only can be from 1 to 5
	private static boolean logged;

	public static void main(String[] args) throws Exception {
		// Connect with the database.
		dbManagerInterface = new DBManager();
		dbManagerInterface.connect();
		doctorManagerInterface = dbManagerInterface.getDoctorManager();
		patientManagerInterface = dbManagerInterface.getPatientManager();
		hospitalManagerInterface = dbManagerInterface.getHospitalManager();
		biomedManagerInterface = dbManagerInterface.getBiomedManager();

		userUsing = false;

		logged = false;

		reader = new BufferedReader(new InputStreamReader(System.in));

		int max;

		System.out.println("WELCOME! THIS IS A PROSTHETIC DATABASE");
		dbManagerInterface.deleteTables();
		dbManagerInterface.createTables();
		

		while (true) {
			System.out.println("Who are you?");
			System.out.println("1.Patient");
			System.out.println("2.Doctor");
			System.out.println("3.Biomedical Engineer");
			System.out.println("4.Hospital");
			System.out.println("\n5.Exit");
			System.out.println("---------\n");

			num = requestNumber(5);
			userUsingNumber = num;
			userUsing = true;
//------------------------------ while user ocupattion//num=1// en cada menu poner un back to the global menu
			while (userUsing) {
				switch (userUsingNumber) {
				case 1: // patient
					System.out.println("PATIENT MENU:");
					System.out.println("What do you want to do?");
					System.out.println("1.Register.");
					System.out.println("2.Login.");
					max = 2;
					//if (logged) {
						System.out.println("3.Select a Hospital.");
						System.out.println("4.View appointments.");
						max = 4;
					//}
					System.out.println("\n0.Back to choose other user to the main menu.\n");
					num = requestNumber(max);
					switch (num) {
					case 1: // Register
						registerMenu();
						break;
					case 2: // Login
						loginMenu();
						logged = true;
						break;
					case 3:
						// First, we show all the hospitals
						System.out.println("The list of the vailable hospitals is:\n");
						showHospitals();

						// Then, they select the hospital
						System.out.println("Now, you need to choose one of them.");
						selectHospitalByID();

						break;
					case 4:
						
							float telephone = InputFlow.takeFloat(reader, "Introduce your telephone number: ");
							patientManagerInterface.viewDate(telephone);
							break;
						
					default: // back
						userUsing = false;

					}
					break;

//-----------------------------------------------------------------------------------
				case 2: // Doctor
					System.out.println("DOCTOR MENU:");
					System.out.println("What do you want to do?");
					System.out.println("1.Register.");
					System.out.println("2.Login.");
					max = 2;
					//if (logged) {
						System.out.println("3.Select a Prosthetic.");
						System.out.println("4.Select date of fitting.");
						System.out.println("5.Search a patients file.");
						System.out.println("6.Add/Modify/Delete a patient.");
						max = 6;
					//}
					System.out.println("\n0.Back to choose other user to the main menu.\n");
					num = requestNumber(max);
					switch (num) {
					case 1: // Register
						registerMenu();
						break;
					case 2: // Login
						loginMenu();
						break;
					case 3: // Select prosthetic and assign it
						searchProsthetic();
						assignProsthetic();
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
					default: // back
						userUsing = false;
					}
					break;

//-----------------------------------------------------------------------------------

			case 3: // Biomedical Engineer
				System.out.println("BIOMEDICAL ENGINEER MENU:");
				System.out.println("What do you want to do?");
				System.out.println("1.Register.");
				System.out.println("2.Login.");
				max=2;//cambiar a 2 ;solo para probar
				
				/*if(logged) {*/
					
					
					System.out.println("1. View Uploaded Prosthetics.");
					System.out.println("3. Upload a new Prosthetic.");
					System.out.println("4. Modify a Prosthetic information.");
					max = 4;
					//}
				System.out.println("\n0.Back to choose other user to the main menu.");
				num = requestNumber(max);

//<<<<<<< HEAD
					//if (logged) {
						System.out.println("What do want to do?: ");
//=======
				
				//arreglar para meter un while 
				switch (num) {
				case 1: // Register
					registerMenu();
					break;
				case 2: // Login
					loginMenu();
					break;
					
				case 3: //Upload a new Prosthetic
					uploadProsthetic();

					break;

				
				case 4: // Modify Prosthetic info
					searchProsType();
					
					int choice = InputFlow.takeInteger(reader, "Introduce the id of the desired prosthetic:");
					modifyProstheticInfo(choice);
					break;
				default: //back
					userUsing=false;
				}

						System.out.println("3. View Uploaded Prosthetics.");
						System.out.println("4. Upload a new Prosthetic.");
						System.out.println("5. Modify a Prosthetic information.");

						max = 5;
					//}
					System.out.println("\n0.Back to choose other user to the main menu.\n");
					num = requestNumber(max);

					// arreglar para meter un while
					switch (num) {
					case 1: // Register
						registerMenu();
						break;
					case 2: // Login
						loginMenu();
						break;

					case 3: // Upload Prosthetic

						uploadProsthetic();
						break;
					case 4: // Modify Prosthetic info
						searchProsType();

						int choice = InputFlow.takeInteger(reader, "Introduce the id of the desired prosthetic:");
						modifyProstheticInfo(choice);
						break;
					default: // back
						userUsing = false;
					}
//>>>>>>> branch 'master' of https://github.com/bermejo4/Prosthetic-DB.git
//=======
//>>>>>>> branch 'master' of https://github.com/bermejo4/Prosthetic-DB.git

//-----------------------------------------------------------------------------------

				case 4: // Hospital
					System.out.println("HOSPITAL MENU:");
					System.out.println("What do you want to do?");
					System.out.println("1.Register.");
					System.out.println("2.Login.");
					max = 2;
					//if (logged) {
						System.out.println("3.Buy a Prosthetic.");
						max = 3;
					//}
					System.out.println("\n0.Back to choose other user to the main menu.\n");
					num = requestNumber(max);
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
					default: // back
						userUsing = false;
					}
					break;
				default:// Exit
					// dbManagerInterface.deleteTables(); Quitar // cuando esté terminada la
					// práctica
					dbManagerInterface.disconnect();
					System.exit(0);
				}
			}

			pressEnter();
		}

	}

//-----------------------------------------------------------------------------------


	public static void uploadProsthetic() throws Exception {

		System.out.println("Introduce the new Prosthetic: ");
		// System.out.print("Name:");
		// String name = reader.readLine();
		System.out.print("Type of Prosthetic(ex. Below the knee, Auricular, etc):");
		String pros_type = reader.readLine();
		System.out.print("Material made of:");
		String material = reader.readLine();
		System.out.print("Prosthetic dimensions:");
		String dimensions = reader.readLine();
		Float price = InputFlow.takeFloat(reader, "Prosthetic price:");
		// Float price = Float.parseFloat(reader.readLine());
		Prosthetic createProsthetic = new Prosthetic(pros_type, material, price, dimensions);
		biomedManagerInterface.insert(createProsthetic);
		System.out.println("Your new prosthetic has been successfully uploaded! ");

	}

	public static void modifyProstheticInfo(int prosID) throws Exception {

		Prosthetic prosToModify = biomedManagerInterface.getProsthetic(prosID);
		System.out.println("Actual prosthetic type: " + prosToModify.getType());
		System.out.println("Establish the new name or press enter to leave it as it is:");
		String newType = reader.readLine();
		if (newType.equals(" ")) {

			newType = prosToModify.getType();

		}
		System.out.println("Actual prosthetic material: " + prosToModify.getMaterial());
		System.out.println("Establish a new material or press enter to leave it as it is:");
		String newMaterial = reader.readLine();
		if (newMaterial.equals(" ")) {

			newMaterial = prosToModify.getMaterial();

		}
		System.out.println("Actual prosthetic dimensions: " + prosToModify.getDimensions());
		System.out.println("Establish new dimensions or press enter to leave it as it is:");
		String newDimensions = reader.readLine();
		if (newDimensions.equals(" ")) {

			newDimensions = prosToModify.getDimensions();

		}
		System.out.println("Actual prosthetic price: " + prosToModify.getPrice());

		Float newPrice = InputFlow.takeFloat(reader, "Establish a new price or press enter to leave it as it is:");
		if (newPrice.equals(" ")) {

			newPrice = prosToModify.getPrice();

		}
		System.out.println("Actual prosthetic failures/limitations: " + prosToModify.getFailures());
		System.out.println("Edit failures/limitations or press enter to leave it as it is:");
		String newFailures = reader.readLine();
		if (newFailures.equals(" ")) {

			newFailures = prosToModify.getFailures();

		}

		Prosthetic updatedaPros = new Prosthetic(prosID, newType, newMaterial, newPrice, newDimensions, newFailures);
		biomedManagerInterface.upadate(updatedaPros);

	}

	public static void searchProsType() throws Exception {

		System.out.println("Search for the prosthetic you want to modify...");
		System.out.println("Filter by type (ex. Below the knee, Auricular, etc):");
		String pros_type = reader.readLine();
		List<Prosthetic> pros = biomedManagerInterface.searchBytype(pros_type);
		// now we need to print the list
		for (Prosthetic prosthetic : pros) {
			System.out.println(prosthetic);

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
		System.out.println("Select the type of search that you want to do?");
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

	public static void showHospitals() {
		// To show all Hospitals in our data base
		List<Hospital> hospitalList = new ArrayList<Hospital>();
		Hospital hosp;
		hospitalList = patientManagerInterface.showHospitals();
		Iterator it = hospitalList.iterator();
		while (it.hasNext()) {
			hosp = (Hospital) it.next();
			System.out.println(hosp.toString());
			System.out.println("");
		}
	}

	
	public static void selectHospitalByID() {
		Hospital hosp;
		int id = InputFlow.takeInteger(reader, "Introduce the id of the hospital you want to select:");
		hosp = patientManagerInterface.selectHospitalByID(id);
		System.out.println("You have chosen:\n" + hosp.toString());
		System.out.println("");
		
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
		LocalDate dayofbirth = InputFlow.takeDate(reader, "Day of Birthday (yyyy-MM-dd): ");
		if (mood) {// If you are modifying the patient you can also modify the day of fitting
			// dof
			LocalDate dayoffitting = InputFlow.takeDate(reader, "Day of Fitting (yyyy-MM-dd)");
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
		LocalDate dayoffitting = InputFlow.takeDate(reader, "\nNow introduce the Day of Fitting (yyyy-MM-dd):");
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
				prostheticList = doctorManagerInterface.selectProsthetic("material", materialpassed);
				break;
			case 2:// type
				String typepassed = reader.readLine();
				prostheticList = doctorManagerInterface.selectProsthetic("type", typepassed);
				break;
			case 3:// dimension
				String dimensionpassed = InputFlow.takeDimension();
				prostheticList = doctorManagerInterface.selectProsthetic("dimension", dimensionpassed);
				break;
			case 4:// failures
				String failurespassed = reader.readLine();
				prostheticList = doctorManagerInterface.selectProsthetic("failures", failurespassed);
				break;
			}
			Iterator it = prostheticList.iterator();
			while (it.hasNext()) {
				prost = (Prosthetic) it.next();
				if (prost.isAvailable()) {
					System.out.println(prost.toString());
					System.out.println("");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void assignProsthetic() {
		try {
			System.out.println("\nThis have been the coincidenses for your search.");
			System.out.println("Now Introduce the Id number of the prosthetic which you want to select:\n");
			int num_id_prost = InputFlow.takeInteger(reader, "Id number:");
			System.out.println("And now introduce the id_number of your patient:");
			int num_id_pat = InputFlow.takeInteger(reader, "Id number:");
			doctorManagerInterface.assignProstheticToPatient(num_id_prost, num_id_pat);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	  public static void buyProsthetic() throws Exception { 
	  int hospital_id = hospitalUser.getId(); // Show the list of all available prosthetic on that specific hospital
	  List<Prosthetic> prostheticList = hospitalManagerInterface.showProsthetics(); 
	  for (Prosthetic prosthetic :prostheticList) { 
	  System.out.println(prosthetic); } // Ask for the Id of the prosthetic you want to buy
	  System.out.println("Please, type the ID of the prosthetic you want to buy: " ); 
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
