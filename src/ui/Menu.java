package ui;

import java.awt.Desktop;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

import javax.persistence.Persistence;
import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import db.classes.*;
import db.inteface.*;
import pojos.*;
import xml.utils.CustomErrorHandler;
import xml.utils.Xml2Html;

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

		System.out.println("\nWELCOME! THIS IS A PROSTHETIC DATABASE");
		// initializeDatabaseWithSomeValues();

		while (true) {
			System.out.println("Who are you?");
			System.out.println("1.Patient");
			System.out.println("2.Doctor");
			System.out.println("3.Biomedical Engineer");
			System.out.println("4.Hospital");
			System.out.println("------------------------");
			System.out.println("5. Go to the website.");
			System.out.println("\n0.Exit");
			System.out.println("---------\n");

			num = requestNumber(5);
			userUsingNumber = num;
			userUsing = true;
//------------------------------ while user ocupattion//num=1// en cada menu poner un back to the global menu
			while (userUsing) {
				switch (userUsingNumber) {
				case 1: // patient
					System.out.println("\nPATIENT MENU:");
					System.out.println("What do you want to do?");
					System.out.println("1.Register.");
					System.out.println("2.Login.");
					max = 2;
					if (logged) {
						System.out.println("3.Select a Hospital.");  
						System.out.println("4.View appointments."); 
						System.out.println("5.Change the credentials."); 
						max = 5;
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
						System.out.println("The list of the available hospitals is:\n");
						showHospitals();

						selectHospitalByID();

						break;
					case 4:
						viewDate();
						break;
					case 5:
						System.out.println("1.Change Username.\n2.Change Password.\n3.Change both.");
						num = requestNumber(max);
						User user=userManagerInterface.getUserByTelephone(patientUsing.getTelephone());
						switch(num) {
							case 1: 
								userManagerInterface.updateUser(user,1);
								break;
							case 2:
								userManagerInterface.updateUser(user, 2);
								break;
							case 3:
								userManagerInterface.updateUser(user, 3);
								break;
						}
						
						break;

					default: // back
						userUsing = false;

					}
					break;

//-----------------------------------------------------------------------------------
				case 2: // Doctor
					System.out.println("\nDOCTOR MENU:");
					System.out.println("What do you want to do?");
					System.out.println("1.Register.");
					System.out.println("2.Login.");

					max = 2;
					if (logged) {
						System.out.println("3.Select a Prosthetic.");
						System.out.println("4.Select date of fitting.");
						System.out.println("5.Search a patients file.");
						System.out.println("6.Add/Modify/Delete a patient.");
						System.out.println("7.Generate a Prosthetic XML.");
						System.out.println("8.Put additional information about me.");
						max = 8;
					}
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
					case 8: introduceDepartmentAndHospital(doctorUsing.getId());
				   
					default: // back
						userUsing = false;
					}
					break;

//-----------------------------------------------------------------------------------

				case 3: // Biomedical Engineer
					
					//agregar un switch o if para que no vuelva a salir
					System.out.println("\nBIOMEDICAL ENGINEER MENU:");
					System.out.println("What do you want to do?");
					System.out.println("1.Register.");
					System.out.println("2.Login.");
					max = 2;

					if(logged) {

					System.out.println("3. Upload a new Prosthetic.");
					System.out.println("4. Modify a Prosthetic information.");
					System.out.println("5. View Uploaded Prosthetics.");
					System.out.println("6. Delete a Prosthetic.");
					System.out.println("7. Upload a new Prosthetic through XML.");

				//	System.out.println("7. Edit user or password");
				//	System.out.println("8. Delete account");
					max = 7;

					}
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
						Prosthetic pros = uploadProsthetic();
						int prosID =dbManagerInterface.getLastId(); 
					    designProsthetic(prosID);
						break;
 
					case 4: // Modify Prosthetic information
						searchProsthetic();

						choice = InputFlow.takeInteger(reader, "Introduce the ID of the prosthetic to be modified:");
						modifyProstheticInfo(choice); 
						System.out.println("The prosthetic has been updated!");
						designProsthetic(choice);
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
					//tengo que utilizar el metodo showbiomedics 
						
					//case 7: Edit user or password
					
					//case 8: Delete account
					case 7:
						admitProstheticXML();
						break;

					default: // back
						userUsing = false;
					}
					break;

//-----------------------------------------------------------------------------------

				case 4: // Hospital
					System.out.println("\nHOSPITAL MENU:");
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
						showHospitals();
						int hospId = InputFlow.takeInteger(reader, "Please, introduce the id of the hospital you want to generate the XML");
						generateHospitalXML(hospId);
						break;
					default: // back
						userUsing = false;
					}
					break;
				case 0:// Exit
					dbManagerInterface.disconnect();
					userManagerInterface.disconnect();
					System.exit(0);
					break;
				default: goToWeb();
				userUsing = false;
				}
				
			}

			pressEnter();
		}

	}

//-----------------------------------------------------------------------------------

	public static void goToWeb() throws JAXBException {
		prepareWebForProsthetics();
		prepareWebForHospitals();
		System.out.println("Se esta ejecutando la página web");
        File filehtml = new File("");
        System.out.println("uri" + filehtml.toURI().toString()+"\n otro:"+filehtml.getAbsolutePath());
        //openInBrowser("file://"+filehtml.getAbsolutePath()+"/src/arqui/pruebaparabases.html");
        String url="file://"+filehtml.getAbsolutePath()+"/xml/homeProsthetic_db.html";
        try {
            URI uri;
			try {
				uri = new URL(url).toURI();
				Desktop.getDesktop().browse(uri); 
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
        } catch (MalformedURLException ex) {
        	ex.printStackTrace();
            //Logger.getLogger(AbrirArchivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
        	ex.printStackTrace();
            //Logger.getLogger(AbrirArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	private static void prepareWebForProsthetics() throws JAXBException {
		//función para tomar todas las protesis de la base
		Patient pat = new Patient();
		ProstheticsListing prosListWeb = new ProstheticsListing();
		prosListWeb.setProsListWeb(new ArrayList<Prosthetic>());
		
		
		
		//ArrayList<Hospital> hospitalsListWeb = new ArrayList<Hospital>();
		//hospitalList = patientManagerInterface.showHospitals();
		
		
		
		prosListWeb.setProsListWeb(biomedManagerInterface.showProsthetic());
		
				
		//función para transformar a xml la lista
		//Create a JAXBContext
		JAXBContext contextP = JAXBContext.newInstance(ProstheticsListing.class);
		//Get the marshaller from the JAXBContext 
		Marshaller marshalP = contextP.createMarshaller();
		//Pretty formating to predefine things
		marshalP.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		//Marshal the Prosthetic: first to a file and then to the screen
		File fileP=new File("./xml/Output-Prosthetic.xml");
		for (Prosthetic currentpros : prosListWeb.getProsListWeb()) {
            System.out.println(currentpros.toString());
            pat = patientManagerInterface.getPatient(currentpros.getPatient().getId());
            currentpros.setPatient(pat);
        }
		marshalP.marshal(prosListWeb, fileP);
		marshalP.marshal(prosListWeb, System.out);
		//función para ejecutar el xslt
		Xml2Html converter = new Xml2Html();
		converter.simpleTransform("./xml/Output-Prosthetic.xml", "./xml/ProstheticWebPT.xslt", "./xml/Prosthetictmp.html");
		
	}
	
	private static void prepareWebForHospitals() throws JAXBException {
		HospitalsListing hospitalsListWeb = new HospitalsListing();
		hospitalsListWeb.setHosptialListWeb(new ArrayList<Hospital>());
		
		hospitalsListWeb.setHosptialListWeb(patientManagerInterface.showHospitals());
		
		//Hospital
		JAXBContext contextH = JAXBContext.newInstance(HospitalsListing.class);
		//Get the marshaller from the JAXBContext 
		Marshaller marshalH = contextH.createMarshaller();
		//Pretty formating to predefine things
		marshalH.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		File fileH=new File("./xml/Output-Hospital.xml");
		for (Hospital currenthospital : hospitalsListWeb.getHosptialListWeb()) {
			currenthospital.setDoctors(doctorManagerInterface.doctorsInHospital(currenthospital.getId()));
			for (Doctor currentdoctor : currenthospital.getDoctors()) {
				currentdoctor.getHospital().setName(currenthospital.getName());;
			}
            System.out.println(currenthospital.getDoctors().toString());
            //pat = patientManagerInterface.getPatient(currenthospital.getPatient().getId());
            //currenthospital.setPatient(pat);
        }
		marshalH.marshal(hospitalsListWeb, fileH);
		marshalH.marshal(hospitalsListWeb, System.out);
		//función para ejecutar el xslt
		
		Xml2Html converter = new Xml2Html();
		converter.simpleTransform("./xml/Output-Hospital.xml", "./xml/ProstheticWebHPT.xslt", "./xml/Hospitaltmp.html");
		
	}

	private static void generateXML(int prostheticID) throws Exception {
		Prosthetic pros =biomedManagerInterface.getProstheticWithPatient(prostheticID);
		Patient pat = patientManagerInterface.getPatient(pros.getPatient().getId());
		pros.setPatient(pat);
		System.out.println(pros.toStringProstheticXML());
		//Create a JAXBContext
		JAXBContext contextP = JAXBContext.newInstance(Prosthetic.class);
		//Get the marshaller from the JAXBContext 
		Marshaller marshalP = contextP.createMarshaller();
		//Pretty formating to predefine things
		marshalP.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		//Marshal the Prosthetic: first to a file and then to the screen
		File fileP=new File("./xml/Output-Prosthetic.xml");
		marshalP.marshal(pros, System.out);
		marshalP.marshal(pros, fileP);
		
		
	}
	
	
	private static void admitProstheticXML() throws Exception {
		JAXBContext contextP = JAXBContext.newInstance(Prosthetic.class);
		//Here we get the unmarshaller
		Unmarshaller unmarshal = contextP.createUnmarshaller();
		//Open the file
		String nameF= InputFlow.takeString(reader,"Type the filename for the XML document (expected in the xml folder):");
		File file = new File("./xml/"+nameF);
		boolean incorrectPros = false;
		do {
		 try {
	        	// Create a DocumentBuilderFactory
	            DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
	            // Set it up so it validates XML documents
	            dBF.setValidating(true);
	            // Create a DocumentBuilder and an ErrorHandler (to check validity)
	            DocumentBuilder builder = dBF.newDocumentBuilder();
	            xml.utils.CustomErrorHandler customErrorHandler = new CustomErrorHandler();
	            builder.setErrorHandler(customErrorHandler);
	            // Parse the XML file and print out the result
	            Document doc = builder.parse(file);
	            
	        } catch (ParserConfigurationException ex) {
	            System.out.println(file + " error while parsing!");
	            incorrectPros=true;
	        } catch (SAXException ex) {
	            System.out.println(file + " was not well-formed!");
	            incorrectPros=true;
	        } catch (IOException ex) {
	            System.out.println(file + " was not accesible!");
	            incorrectPros=true;
	        }
		}while(incorrectPros);
		//Unmarshall the Prosthetic from a file
		Prosthetic pros = (Prosthetic) unmarshal.unmarshal(file);
		//We print the prosthetic that we are adding to the database
		System.out.println("Added to the Prosthetic database: " + pros.toStringProstheticXML());
		//Now we insert the prosthetic
		biomedManagerInterface.insert(pros);
		//We need the id of the prosthetic because it is not in the XML
		int prosId=dbManagerInterface.getLastId();
		List<Biomedical_Eng> biomeds = pros.getBiomeds();
		for(Biomedical_Eng biomed: biomeds) {
			biomedManagerInterface.design(prosId, biomed.getId());
			
		}
	}
	
	public static Prosthetic uploadProsthetic() throws Exception {

		System.out.println("Introduce the new Prosthetic: "); 
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
		System.out.println("The new prosthetic has been successfully uploaded! ");
		return createProsthetic;

	}

	public static void designProsthetic(int prosID) throws Exception {

		List<Biomedical_Eng> biomedsLists = biomedManagerInterface.showBiomedics();
		
		int biomed_id = biomedical_engUsing.getId(); 
		System.out.println("Biomedic with ID " + biomed_id + ",is now an author of the prosthetic with ID:" + prosID);

		biomedManagerInterface.design(prosID, biomed_id);

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
		System.out.println("Establish the same dimensions or specify new dimensions:");
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
		// int max is the maximum option that is acceptable
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
	
	public static void wrongInfo(){
		System.out.println("Wrong credentials, please select an option: ");
		System.out.println("1. Introduce them again. ");
		System.out.println("0. Go back to the menu. ");
	}
	

	public static void login(Role role) throws Exception { //hacer option para go back 
		boolean check = true;
		do {
			String telephone = InputFlow.takeTelephone(reader, "Introduce the phone number:");
			byte[] password = InputFlow.takePasswordAndHashIt(reader, "Introduce the password:");
			User user = new User(telephone, password, role);
			User userCheck = userManagerInterface.checkPassword(user);

			if (userCheck == null) {
				wrongInfo();
				int option = requestNumber(2);
				switch(option) {
				case 1:
					break;
				case 0:
					System.out.println("Press 0 to go back");
					check=false;
				}
			} else {
				switch (userCheck.getRole().getRole()) {
				case "patient":
					System.out.println("Welcome patient!");
					patientUsing.setTelephone(telephone);
					patientUsing = patientManagerInterface.searchSpecificPatientByTelephone(telephone);
					
					logged = true;
					check=false;
					break;
				case "doctor":
					System.out.println("Welcome doctor!");
					doctorUsing.setTelephone(telephone);
					doctorUsing = doctorManagerInterface.searchSpecificDoctorByTelephone(telephone);
					
					logged = true;
					check=false;
					break;
				case "hospital":
					System.out.println("You are in a hospital.");
					hospitalUsing.setTelephone(telephone);
					hospitalUsing = hospitalManagerInterface.searchSpecificHospitalByTelephone(telephone);
					
					logged = true;
					check=false;
					break;
				case "biomedical_Engineer":
					System.out.println("Welcome biomedical engineer!");
					biomedical_engUsing.setTelephone(telephone);
					biomedical_engUsing = biomedManagerInterface.searchSpecificBiomedByTelephone(telephone);
					
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
	
	public static void deleteUser() {
		
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
		if (coiList.isEmpty()) {
			System.out.println("there�s no patient with that telephone number");
		}
		pressEnter();
	}
	
	public static void showHospitals() {
		// To show all Hospitals in our data base
		ArrayList<Hospital> hospitalList = new ArrayList<Hospital>();
		Hospital hosp;
		hospitalList = patientManagerInterface.showHospitals();
		Iterator it = hospitalList.iterator();
		while (it.hasNext()) {
			hosp = (Hospital) it.next();
			System.out.println(hosp.toStringXML());
			System.out.println("");
		}
	}
	

	public static void selectHospitalByID() {
		Hospital hosp;
		Patient pat = patientUsing;
		int id = InputFlow.takeInteger(reader, "Introduce the id of the hospital you want to select:");
		hosp = patientManagerInterface.selectHospitalByID(id);
		patientManagerInterface.assignPatientToAHospital(id, pat);
		//System.out.println("You have chosen:\n" + hosp.toStringXML());
		//System.out.println("");

	}
	

	public static void viewDate() {
		// To view your own date of fitting
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
		// dob
		LocalDate dayofbirth = InputFlow.takeDate(reader, "Day of Birthday (yyyy-MM-dd): ");
		if (mood) {// If you are modifying the patient you can also modify the day of fitting
			// dof
			LocalDate dayoffitting = InputFlow.takeDate(reader, "Day of Fitting (yyyy-MM-dd)");
		}
		
		System.out.println("Address:");
		String address = reader.readLine();
		System.out.println("Phone number:");
		String telephone = InputFlow.takeTelephone(reader, "Introduce a telephone: ");
		System.out.println("Gender:");
		String gender = InputFlow.takeGender(reader, "");
		System.out.println("Problem:"); 
		String problem = reader.readLine();
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

	public static void searchProsthetic() {
		Prosthetic prost;
		searchProstheticMenu();
		int option = requestNumber(5);
		boolean searched=true;

		List<Prosthetic> prostheticList = new ArrayList<Prosthetic>();
		try {
			do {
				switch (option) {
					
				case 1:// material
					System.out.println("Name of the material you are looking for:");
					String materialpassed = reader.readLine();
					prostheticList = doctorManagerInterface.selectProsthetic("material", materialpassed);
					searched=false;
					break;
				case 2:// type
					System.out.println("Specify which type of prosthetic you are looking for:");
					String typepassed = reader.readLine();
					prostheticList = doctorManagerInterface.selectProsthetic("type", typepassed);
					searched=false;
					break;
				case 3:// dimension
					System.out.println("Specify the dimensions of prosthetic you are looking for:");
					String dimensionpassed = InputFlow.takeDimension();
					prostheticList = doctorManagerInterface.selectProsthetic("dimension", dimensionpassed);
					searched=false;
					break;
				case 4:// failures
					System.out.println("Specify the kind of failures you are looking for:");
					String failurespassed = reader.readLine();
					prostheticList = doctorManagerInterface.selectProsthetic("failures", failurespassed);
					searched=false;
					break;
				case 0: 
					searched=false;
					break;
				}
			} while(searched);
			
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
			int num_id_pat = InputFlow.checkIdAndListPPatient(patientManagerInterface.showPatients());
			doctorManagerInterface.assignProstheticToPatient(num_id_prost, num_id_pat);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}

	public static void buyProsthetic() throws Exception {
		int hospital_id = hospitalUsing.getId();

		// Show the list of all available prosthetic on that specific hospital
		List<Prosthetic> prostheticList = hospitalManagerInterface.showProsthetics();

		for (Prosthetic prosthetic : prostheticList) {
			System.out.println(prosthetic.toStringProstheticXML());
		}

		// Ask for the Id of the prosthetic you want to buy
		System.out.println("Please, type the ID of the prosthetic you want to buy: ");
		int prosthetic_id = Integer.parseInt(reader.readLine());

		// the specific hospital buys the prosthetic choosed
		hospitalManagerInterface.buy(hospital_id , prosthetic_id);
		
		System.out.println("You bought this prosthetic: "+prosthetic_id);
	}
	
	
	private static void generateHospitalXML(int hospital_id) throws Exception{
		Hospital hospital = hospitalManagerInterface.getHospitalwithPatient(hospital_id);
		hospital.setDoctors(doctorManagerInterface.doctorsInHospital(hospital.getId()));
		//Defining and creating  JAXB context
		JAXBContext contextHosp = JAXBContext.newInstance(Hospital.class);
		//Creating the marshaller
		Marshaller marsh = contextHosp.createMarshaller();
		marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		//We create the file to put the xml
		File file = new File("./xml/Output-Hospital.xml");
		marsh.marshal(hospital, file);
		System.out.println("\nThis is the XML of the hospital introduced:");
		marsh.marshal(hospital, System.out);
		pressEnter();	
	}
	
	private static void introduceDepartmentAndHospital(int currentDoctorId) {
		String department=InputFlow.takeString(reader, "Introduce your department");		
		int id= InputFlow.checkIdAndListHospital(patientManagerInterface.showHospitals());
		doctorManagerInterface.insertDepartmentAndHospital(currentDoctorId, department, id);
		
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
