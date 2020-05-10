package ui;

import java.io.*;
import java.util.*;

import javax.xml.bind.*;

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
	private static UserManagerInterface userManagerInterface;

	// Used for parsing dates
	private static BufferedReader reader;
	private static int num;

	// Used for who is using the program
	private static Patient patientUsing = new Patient();
	private static Doctor doctorUsing = new Doctor();
	private static Hospital hospitalUsing = new Hospital();
	private static Biomedical_Eng biomedical_engUsing = new Biomedical_Eng();
	private static boolean userUsing;
	private static int userUsingNumber; // Only can be from 1 to 5
	private static boolean logged;

	public static void main(String[] args) throws Exception {
		// Connect with the database using JDBC.
		dbManagerInterface = new DBManager();
		dbManagerInterface.connect();
		// dbManagerInterface.deleteTables();
		dbManagerInterface.createTables();
		// initializeDatabaseWithSomeValues();

		doctorManagerInterface = dbManagerInterface.getDoctorManager();
		patientManagerInterface = dbManagerInterface.getPatientManager();
		hospitalManagerInterface = dbManagerInterface.getHospitalManager();
		biomedManagerInterface = dbManagerInterface.getBiomedManager();

		// Connect with the database using JPA.

		userManagerInterface = new UserManager();
		userManagerInterface.connect();

		Role patientRole = new Role("patient");
		// System.out.println(patientRole.toString());
		userManagerInterface.createRole(patientRole);// id => 1
		Role doctorRole = new Role("doctor");
		userManagerInterface.createRole(doctorRole);// id => 2
		Role hospitalRole = new Role("hospital");
		userManagerInterface.createRole(hospitalRole);// id => 3
		Role biomedicalRole = new Role("biomedical_Engineer");
		userManagerInterface.createRole(biomedicalRole);// id => 4

		userUsing = false;

		logged = false;

		reader = new BufferedReader(new InputStreamReader(System.in));

		int max;

		System.out.println("WELCOME! THIS IS A PROSTHETIC DATABASE");
		// dbManagerInterface.deleteTables();
		// initializeDatabaseWithSomeValues();

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
					if (logged) {
						System.out.println("3.Select a Hospital.");  
						System.out.println("4.View appointments."); 
						max = 4;
					}
					System.out.println("\n0.Back to choose other user to the main menu.\n");
					num = requestNumber(max);
					switch (num) {
					case 1: // Register
						registerMenu();
						register(patientRole);
						break;
					case 2: // Login
						loginMenu();
						login(patientRole); 						
						break;
					case 3:
						// First, we show all the hospitals
						//System.out.println("The list of the available hospitals is:\n");
						//showHospitals();

						// Then, they select the hospital
						//System.out.println("Now, you need to choose one of them.");
						selectHospitalByID();

						break;
					case 4:
						viewDate();
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
						System.out.println("7.Generate a Prosthetic XML.");
						max = 6;
					//}
					System.out.println("\n0.Back to choose other user to the main menu.\n");
					num = requestNumber(max);
					int prosId;
					switch (num) {
					case 1: // Register
						registerMenu();
						register(doctorRole);
						break;
					case 2: // Login
						loginMenu();
						login(doctorRole);
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
					case 7:
						searchProsthetic();
						prosId = InputFlow.takeInteger(reader, "Introduce the ID of the prosthetic you want to create the XML:");
						generateXML(prosId);
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
					max = 2;

					//if(logged) {

					System.out.println("3. Upload a new Prosthetic.");
					System.out.println("4. Modify a Prosthetic information.");
					System.out.println("5. View Uploaded Prosthetics.");
					System.out.println("6. Delete a Prosthetic.");
				//	System.out.println("7. Edit user or password");
				//	System.out.println("8. Delete account");
					max = 6;
					//}
					System.out.println("\n0.Back to choose other user to the main menu.");

					int choice;
					num = requestNumber(max);

					switch (num) {
					case 1: // Register
						registerMenu();
						register(biomedicalRole);
						break;
					case 2: // Login
						loginMenu();
						login(biomedicalRole);
						break;

					case 3: // Upload a new Prosthetic
						uploadProsthetic();
						break;

					case 4: // Modify Prosthetic information
						searchProsthetic();

						choice = InputFlow.takeInteger(reader, "Introduce the ID of the prosthetic to be modified:");
						modifyProstheticInfo(choice); // no esta funcionando
						System.out.println("Your prosthetic has been updated!");
						break;

					case 5:// View Uploaded Prosthetics
						System.out.println("Here are all the existing prosthetics: ");
						showProsthetics();
						break;

					case 6: // Delete Prosthetic
						searchProsthetic();

						choice = InputFlow.takeInteger(reader, "Introduce the ID of the prosthetic to be deleted:");
						deleteProsthetic(choice);
						break;
						
					case 7: //Edit user or password
					
					case 8: //Delete account
						
					
					default: // back
						userUsing = false;
					}
					break;

//-----------------------------------------------------------------------------------

				case 4: // Hospital
					System.out.println("HOSPITAL MENU:");
					System.out.println("What do you want to do?");
					System.out.println("1.Register.");
					System.out.println("2.Login.");
					max = 2;
					if (logged) {
						System.out.println("3.Buy a Prosthetic.");
						System.out.println("4.Generate Hospital XML ");
						max = 4;
					}
					System.out.println("\n0.Back to choose other user to the main menu.\n");
					num = requestNumber(max);
					switch (num) {
					case 1: // Register
						registerHospitalMenu();
						register(hospitalRole);
						break;
					case 2: // Login
						loginMenu();
						login(hospitalRole);
						break;
					case 3: // Buy a prosthetic
						buyProsthetic();
						break;
					case 4: //Generate the XML of the hospital
						//generateHospitalXML(hospital_id);
					default: // back
						userUsing = false;
					}
					break;
				default:// Exit
					// dbManagerInterface.deleteTables(); Quitar // cuando esté terminada la
					// práctica
					dbManagerInterface.disconnect();
					userManagerInterface.disconnect();
					System.exit(0);
				}
			}

			pressEnter();
		}

	}

//-----------------------------------------------------------------------------------

	private static void generateXML(int prostheticID) throws Exception {
		Prosthetic pros =biomedManagerInterface.getProsthetic(prostheticID);
		//Create a JAXBContext
		JAXBContext contextP = JAXBContext.newInstance(Prosthetic.class);
		//Get the marshaller from the JAXBContext 
		Marshaller marshalP = contextP.createMarshaller();
		//Pretty formating to predefine things
		marshalP.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		//Marshal the Prosthetic: first to a file and then to the screen
		File fileP=new File("./xml/Output-Prosthetic.xml");
		marshalP.marshal(pros, fileP);
		marshalP.marshal(pros, System.out);
	}
	
	public static void uploadProsthetic() throws Exception {

		System.out.println("Introduce the new Prosthetic: ");
		// System.out.print("Name:");
		// String name = reader.readLine();
		System.out.print("Specify Prosthetic type(ex. Below the knee, Auricular, etc):");
		String pros_type = reader.readLine();
		System.out.print("Material made of:");
		String material = reader.readLine();
		System.out.println("Prosthetic dimensions:");
		String dimensions = InputFlow.takeDimension();
		Float price = InputFlow.takeFloat(reader, "Prosthetic price:");
		System.out.println("Prosthetic Failures/limitations:");
		String failures = reader.readLine();
		System.out.print("Prosthetic Availability:");
		System.out.println("Type 'A' for available or 'NA' for not available");
		String newAV = reader.readLine();
		boolean available = InputFlow.takeAvailable(newAV);

		Prosthetic createProsthetic = new Prosthetic(pros_type, material, price, dimensions, failures, available);
		biomedManagerInterface.insert(createProsthetic);
		System.out.println("Your new prosthetic has been successfully uploaded! ");

	}

	public static void designProsthetic(int prosID) throws Exception {

		// System.out.println(" your ID is :" + );

		// biomedManagerInterface.design(prosID, be_id);

	}

	public static void deleteProsthetic(int prosID) throws Exception {

		Prosthetic pros = new Prosthetic();
		pros = biomedManagerInterface.getProsthetic(prosID);
		
		if (InputFlow.areYouSure(reader, "Are you sure that do you want to delete this prosthetic?")) {
			biomedManagerInterface.delete(pros);
			System.out.println("The prosthetic has been deleted");
		} 

	}

	public static void modifyProstheticInfo(int prosID) throws Exception {

		Prosthetic prosToModify = biomedManagerInterface.getProsthetic(prosID);
		System.out.println("Actual prosthetic type: " + prosToModify.getType());
		System.out.println("Establish the new type or press enter to leave it as it is:");
		String newType = reader.readLine();
		if (newType.equals("")) {

			newType = prosToModify.getType();

		}
		System.out.println("Actual prosthetic material: " + prosToModify.getMaterial());
		System.out.println("Establish a new material or press enter to leave it as it is:");
		String newMaterial = reader.readLine();
		if (newMaterial.equals("")) {

			newMaterial = prosToModify.getMaterial();

		}
		System.out.println("Actual prosthetic dimensions: " + prosToModify.getDimensions());
		System.out.println("Establish new or same dimensions:");
		String newDimensions = InputFlow.takeDimension();

		System.out.println("Actual prosthetic price: " + prosToModify.getPrice());

		Float newPrice = InputFlow.takeFloat(reader, "Establish a new price or press 0 to leave it as it is:");
		if (newPrice == 0) {

			newPrice = prosToModify.getPrice();

		}
		System.out.println("Actual prosthetic failures/limitations: " + prosToModify.getFailures());
		System.out.println("Edit failures/limitations or press enter to leave it as it is:");
		String newFailures = reader.readLine();
		if (newFailures.equals("")) {

			newFailures = prosToModify.getFailures();

		}

		System.out.println("Actual prosthetic availability: " + getAvailable(prosID));
		System.out.println("To change availability type 'A' for available or 'NA' for not available");
		System.out.println("For no change, press enter to leave it as it is:");

		String newAV = reader.readLine();
		boolean av = true;
		av = InputFlow.takeAvailable(newAV);

		if (newAV.equals("")) {

			av = prosToModify.getAvailable();
		}

		Prosthetic updatedPros = new Prosthetic(prosID, newType, newMaterial, newPrice, newDimensions, newFailures, av);
		biomedManagerInterface.upadate(updatedPros);

	}

	public static String getAvailable(int prosID) {
		Prosthetic prosToModify = biomedManagerInterface.getProsthetic(prosID);

		String state;
		if (prosToModify.getAvailable() == true) {
			return state = "Available";

		} else {
			return state = "NO longer Available";
		}
	}

	public static void showProsthetics() {

		List<Prosthetic> prosList = new ArrayList<Prosthetic>();

		Prosthetic pros;
		prosList = biomedManagerInterface.showProsthetic();
		Iterator it = prosList.iterator();
		while (it.hasNext()) {
			pros = (Prosthetic) it.next();
			System.out.println(pros.toString());
			System.out.println("");
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

	public static void initializeDatabaseWithSomeValues() {
		dbManagerInterface.initializeHospitals("La Paz", "Madrid", "902120120");
		dbManagerInterface.initializeHospitals("12 de Octubre", "Madrid", "902100900");
		dbManagerInterface.initializeHospitals("Fundacion Jimenez Diaz", "Madrid", "900333222");
		dbManagerInterface.initializeHospitals("Ramon y Cajal", "Madrid", "932564786");
		dbManagerInterface.initializeHospitals("Nacional de Paraplejicos", "Madrid", "987640998");

		dbManagerInterface.initializeDoctors("Juan", "657901456", "traumatologist", 1);
		dbManagerInterface.initializeDoctors("Rosa", "646321211", "cardiologist", 2);
		/*
		 * <<<<<<< HEAD dbManagerInterface.initializeBiomedics("Gabriela", "Api");
		 * dbManagerInterface.initializeBiomedics("Marina", "Gonzales"); =======
		 * dbManagerInterface.initializeBiomedics("Gabriela", "Apicella");
		 * dbManagerInterface.initializeBiomedics("Marina", "Miguelez"); >>>>>>> branch
		 * 'master' of https://github.com/bermejo4/Prosthetic-DB.git
		 * dbManagerInterface.initializeBiomedics("Jaime", "Bermejo");
		 * dbManagerInterface.initializeBiomedics("Maria Celeste", "Ortega");
		 */

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

	//para que imprimir el register menu?
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
		System.out.println("0.Back.");
	}

	public static void login(Role role) throws Exception { //hacer option para go back 
		boolean check = true;
		do {
			String telephone = InputFlow.takeTelephone(reader, "Introduce the phone number:");
			byte[] password = InputFlow.takePasswordAndHashIt(reader, "Introduce the password:");
			User user = new User(telephone, password, role);
			User userCheck = userManagerInterface.checkPassword(user);

			if (userCheck == null) {
				System.out.println("Wrong credentials. Introduce them again.");
			} else {
				switch (userCheck.getRole().getRole()) {
				case "patient":
					System.out.println("Welcome patient!");
					patientUsing.setTelephone(telephone);
					logged = true;
					check=false;
					break;
				case "doctor":
					System.out.println("Welcome doctor!");
					doctorUsing.setTelephone(telephone);
					logged = true;
					check=false;
					break;
				case "hospital":
					System.out.println("You are in a hospital.");
					hospitalUsing.setTelephone(telephone);
					logged = true;
					check=false;
					break;
				case "biomedical_Engineer":
					System.out.println("Welcome biomedical engineer!");
					biomedical_engUsing.setTelephone(telephone);
					logged = true;
					check=false;
					break;
				default:
					System.out.println("Invalid role.");
					break;
				}
			}
		} while (check);
	}

	public static void register(Role role) {
		String name = InputFlow.takeString(reader, "Introduce your name:");
		String lastname = "error";
		String address = "error";

		if (role.getRole().equals("hospital")) {
			address = InputFlow.takeString(reader, "Introduce the address: ");
		} else {
			lastname = InputFlow.takeString(reader, "Introduce your lastname: ");
		}

		String telephone = InputFlow.takeTelephone(reader, "Introduce your phone number:");
		byte[] password = InputFlow.takePasswordAndHashIt(reader, "Introduce a password:");

		User user = new User(telephone, password, role);
		userManagerInterface.createUser(user);

		switch (user.getRole().getRole()) {
		case "patient":
			Patient newpatient = new Patient(name, lastname, telephone);
			patientManagerInterface.addpatientbyRegister(newpatient);
			break;

		case "doctor":
			Doctor newdoc = new Doctor(name, lastname, telephone);
			doctorManagerInterface.addDoctorbyRegister(newdoc);
			break;

		case "hospital":
			Hospital newhosp = new Hospital(name, address, telephone);
			hospitalManagerInterface.addhospitalbyRegister(newhosp);
			break;

		case "biomedical_Engineer":
			Biomedical_Eng newbiomed = new Biomedical_Eng(name, lastname, telephone);
			biomedManagerInterface.addBiomedbyRegister(newbiomed);
			break;

		default:
			System.out.println("Error in register");

		}

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
	//Este es el de antes cuando funcionaba y ahora no:(((((((((((((((((
	/*public static void showHospitals() {
		// To show all Hospitals in our data base
		ArrayList<Hospital> hospitalList = new ArrayList<Hospital>();
		Hospital hosp;
		hospitalList = patientManagerInterface.showHospitals();
		Iterator it = hospitalList.iterator();
		while (it.hasNext()) {
			hosp = (Hospital) it.next();
			System.out.println(hosp.toString());
			System.out.println("");
		}
		return hospitalList;
	}*/
	public static void showHospitals() {
		// To show all Hospitals in our data base
		ArrayList<Hospital> hospitalList = new ArrayList<Hospital>();
		Hospital hosp;
		hospitalList = patientManagerInterface.showHospitals();
		Iterator it = hospitalList.iterator();
		while (it.hasNext()) {
			hosp = (Hospital) it.next();
			System.out.println(hosp.toString());
			System.out.println("");
		}
	}
	//Este es el de antes cuando funcionaba y ahora no:(((((((((((((((((
	/*public static void selectHospitalByID() {
		Hospital hosp;
		int id = InputFlow.takeInteger(reader, "Introduce the id of the hospital you want to select:");
	
		hosp = patientManagerInterface.selectHospitalByID(id);
		System.out.println("You have chosen:\n" + hosp.toString());
		System.out.println("");

	}*/

	public static void selectHospitalByID() {
		
		ArrayList<Hospital> hospitalList;
		Hospital hosp;
		hospitalList = patientManagerInterface.showHospitals();
		int idChecked = InputFlow.checkIdAndListHospital(hospitalList);
		hosp = patientManagerInterface.selectHospitalByID(idChecked);
		System.out.println("You have chosen:\n" + hosp.toString());
		System.out.println("");

	}

	public static void viewDate() {
		// To view your own date of fitting

		//String telephone = InputFlow.takeTelephone(reader, "Introduce your telephone number: ");
		String myTelephone = patientUsing.getTelephone();
		patientManagerInterface.viewDate(myTelephone);

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
		//doctorManagerInterface.doctorsInDatabase();
		int doctor_id =InputFlow.checkIdAndListDoctor(doctorManagerInterface.doctorsInDatabase());
		
		Doctor doctor = new Doctor(doctor_id);
		if (mood) {
			pat = new Patient(name, lastname, telephone, Date.valueOf(dayofbirth), gender, problem, address, doctor);
			doctorManagerInterface.modify(pat, Date.valueOf(dayofbirth), num_id);
		} else {
			pat = new Patient(name, lastname, telephone, Date.valueOf(dayofbirth), gender, problem, address, doctor);
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

	public static void searchProsthetic() {// falta que si no la encuentra go back
		Prosthetic prost;
		searchProstheticMenu();
		int option = requestNumber(4);

		List<Prosthetic> prostheticList = new ArrayList<Prosthetic>();
		try {
			switch (option) {
			case 1:// material
				System.out.println("Name of the material you are looking for:");
				String materialpassed = reader.readLine();
				prostheticList = doctorManagerInterface.selectProsthetic("material", materialpassed);
				break;
			case 2:// type
				System.out.println("Specify which type of prosthetic you are looking for:");
				String typepassed = reader.readLine();
				prostheticList = doctorManagerInterface.selectProsthetic("type", typepassed);
				break;
			case 3:// dimension
				System.out.println("Specify the dimensions of prosthetic you are looking for:");
				String dimensionpassed = InputFlow.takeDimension();
				prostheticList = doctorManagerInterface.selectProsthetic("dimension", dimensionpassed);
				break;
			case 4:// failures
				System.out.println("Specify the kind of failures you are looking for:");
				String failurespassed = reader.readLine();
				prostheticList = doctorManagerInterface.selectProsthetic("failures", failurespassed);
				break;
			default: // back?? si no la encuentra pueda volver al menu

			}

			if (prostheticList.isEmpty()) {

				System.out.println("No file found for that search..Try again");
				searchProsthetic();

			} else {

				Iterator it = prostheticList.iterator();
				while (it.hasNext()) {
					prost = (Prosthetic) it.next();

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
		// int hospital_id = hospitalUser.getId();

		// Show the list of all available prosthetic on that specific hospital
		List<Prosthetic> prostheticList = hospitalManagerInterface.showProsthetics();

		for (Prosthetic prosthetic : prostheticList) {
			System.out.println(prosthetic);
		}

		// Ask for the Id of the prosthetic you want to buy
		System.out.println("Please, type the ID of the prosthetic you want to buy: ");
		int prosthetic_id = Integer.parseInt(reader.readLine());

		// the specific hospital buys the prosthetic choosed
		hospitalManagerInterface.buy(/* hospital_id */1, prosthetic_id);
	}
	
	public static void generateHospitalXML(int hospital_id) throws Exception{
		
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
