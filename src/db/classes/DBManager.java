 package db.classes;
import java.sql.*;

import db.inteface.*;

public class DBManager implements DBManagerInterface{
	private Connection c;
	private DoctorManager doctor;
	private HospitalManager hospital;
	private PatientManager patient;
	
	public DBManager() {
		super();
	}

	
	public void connect() {
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/Prosthetic_DB.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			//System.out.println("Database connection opened.");
			//create DoctorManager
			doctor = new DoctorManager(c);
			//create HospitalManager
			hospital = new HospitalManager(c);
			//create PatientManager
			patient = new PatientManager(c);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	protected Connection getConnection() {
		return c;
	}



	public void setC(Connection c) {
		this.c = c;
	}



	public void disconnect() {
		try {			
			// Close database connection
			c.close();
			//System.out.println("Database connection closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void createTables() {
		try {
			// Open database connection
			//Class.forName("org.sqlite.JDBC");
			//Connection c = DriverManager.getConnection("jdbc:sqlite:./db/Prosthetic_DB.db");
			//c.createStatement().execute("PRAGMA foreign_keys=ON");
			//System.out.println("Database connection opened.");
			//connect();
			
			// Create tables: begin
			Statement stmt1 = c.createStatement();
			String sql1 = "CREATE TABLE doctor "
					+ "(doctor_id	INTEGER NOT NULL UNIQUE,"
					+ "name 	TEXT,"
					+ "department	TEXT,"
					+ "Hosp_id	INTEGER,"
					+ "Date_of_fitting	DATE,"
					+ "PRIMARY KEY('doctor_id'),"
					+ "FOREIGN KEY('Hosp_id') REFERENCES hospital('hospital_id') ON DELETE SET NULL ON UPDATE CASCADE)";
			
			stmt1.executeUpdate(sql1);
			stmt1.close();
			
			Statement stmt2 = c.createStatement();
			String sql2 = "CREATE TABLE hospital "
					+ "(hospital_id	INTEGER NOT NULL UNIQUE,"
					+ "name 	TEXT,"
					+ "location 	TEXT,"
					+ "patient_id	INTEGER,"
					+ "PRIMARY KEY('hospital_id'))";
			
			stmt2.executeUpdate(sql2);
			stmt2.close();
			
			Statement stmt3 = c.createStatement();
			String sql3 = "CREATE TABLE patient "
					   + "patient_id INTEGER NOT NULL UNIQUE,"
					   + "name    TEXT,"
					   + "lastname        TEXT,"
					   + "dob     DATE,"
					   + "dof	  DATE,"
					   + "address   TEXT,"
					   + "telephone     TEXT, "
					   + "gender	TEXT,"
					   + "problem      TEXT, "
					   + "doc_id     INTEGER, "
					   + "PRIMARY KEY('patient_id'),"
					   + "FOREIGN KEY('doc_id') REFERENCES doctor(doctor_id)"
					   + "ON DELETE SET NULL ON UPDATE CASCADE)";
			stmt3.executeUpdate(sql3);
			stmt3.close();
			
			Statement stmt4 = c.createStatement(); 
			String sql4 = "CREATE TABLE prosthetic "
					+ "(prosthetic_id	INTEGER NOT NULL UNIQUE,"
					+ "material 	TEXT,"
					+ "type		TEXT,"
					+ "dimension	TEXT,"
					+ "failures   TEXT,"
					//falta el number_failures
					+ "price 	REAL"
					+ "patient_id	INTEGER,"
					+ "hospital_id 	INTEGER"
					+ "PRIMARY KEY('prosthetic_id'),"
					+ "FOREIGN KEY('Hospital_id') REFERENCES hospital('hospital_id') ON DELETE SET NULL ON UPDATE CASCADE)";
			
			stmt4.executeUpdate(sql4);
			stmt4.close();
			
			Statement stmt5 = c.createStatement(); 
			String sql5 = "CREATE TABLE BiomedEng "
					+ "(be_id		INTEGER NOT NULL UNIQUE,"
					+ " name 		TEXT NOT NULL,"
					+ " lastname	TEXT NOT NULL,"
					+ " PRIMARY KEY('be_id')";
			
			stmt5.executeUpdate(sql5);
			stmt5.close();
			
			Statement stmt6 = c.createStatement(); 
			String sql6 = "CREATE TABLE M_M "
					+ "(prost_id	INTEGER NOT NULL,"
					+ "be_id INTEGER NOT NULL,"
					+ "FOREIGN KEY('prost_id')REFERENCES prosthetic('prosthetic_id') ON DELETE SET NULL ON UPDATE CASCADE,"
					+ "FOREIGN KEY('be_id') REFERENCES biomedical_engineer('be_id') ON DELETE SET NULL ON UPDATE CASCADE)";
			
			stmt6.executeUpdate(sql6);
			stmt6.close();
			
			System.out.println("Tables created.");
			// Create table: end
			//
			// - Set initial values for the Primary Keys
			// - Don't try to understand this until JPA is explained
			// This is usually not needed, since the initial values
			// are set when the first row is inserted, but since we
			// are using JPA and JDBC in the same project, and JPA
			// needs an initial value, we do this.
			//Statement stmtSeq = c.createStatement();
			//String sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('departments', 1)";
			//stmtSeq.executeUpdate(sqlSeq);
			//sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('employees', 1)";
			//stmtSeq.executeUpdate(sqlSeq);
			//sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('reports', 1)";
			//stmtSeq.executeUpdate(sqlSeq);
			//stmtSeq.close();
			
			// Close database connection
			//c.close();
			//System.out.println("Database connection closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DoctorManager getDoctorManager() {
		return doctor;
	}

	public HospitalManager getHospitalManager() {
		return hospital;
	}

	public PatientManager getPatientManager() {
		return patient;
	}
	
	public int getLastId() {
		int result = 0;
		try {
			String query = "SELECT last_insert_rowid() AS lastId";
			PreparedStatement p = c.prepareStatement(query);
			ResultSet rs= p.executeQuery();
			result = rs.getInt("lastId");
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	

}
