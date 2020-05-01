
package db.classes;

import java.sql.*;

import db.inteface.*;
import pojos.User;

public class DBManager implements DBManagerInterface {
	private Connection c;
	private DoctorManager doctor;
	private HospitalManager hospital;
	private PatientManager patient;
	private BEManager biomed; // no creamos al hacer la connection c

	public DBManager() {
		super();
	}

	public void connect() {
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/Prosthetic_DB.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			// System.out.println("Database connection opened.");
			// create DoctorManager
			doctor = new DoctorManager(c);
			// create HospitalManager
			hospital = new HospitalManager(c);
			// create PatientManager
			patient = new PatientManager(c);
			// create Biomed manager
			biomed = new BEManager(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setC(Connection c) {
		this.c = c;
	}

	public void disconnect() {
		try {
			// Close database connection
			c.close();
			// System.out.println("Database connection closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void createTables() {
		try {
			// Open database connection
			// Class.forName("org.sqlite.JDBC");
			// Connection c =
			// DriverManager.getConnection("jdbc:sqlite:./db/Prosthetic_DB.db");
			// c.createStatement().execute("PRAGMA foreign_keys=ON");
			// System.out.println("Database connection opened.");
			// connect();

			// Create tables: begin
			Statement stmt1 = c.createStatement();
			String sql1 = "CREATE TABLE doctor " 
					+ " (doctor_id	INTEGER PRIMARY KEY AUTOINCREMENT," 
					+ "name TEXT,"
					+ "lastname TEXT,"
					+ "telephone TEXT," 
					+ "department TEXT," 
					+ "hospital_id	INTEGER," 
					+ "password BLOB,"
					+ "FOREIGN KEY('hospital_id') REFERENCES hospital('hospital_id') ON DELETE SET NULL ON UPDATE CASCADE)";

			stmt1.executeUpdate(sql1);
			stmt1.close();

			Statement stmt2 = c.createStatement();
			String sql2 = "CREATE TABLE hospital " 
					+ "(hospital_id	INTEGER PRIMARY KEY AUTOINCREMENT," 
					+ "name TEXT,"
					+ "location TEXT," 
					+ "telephone TEXT," 
					+ "patient_id	INTEGER)";

			stmt2.executeUpdate(sql2);
			stmt2.close();

			Statement stmt3 = c.createStatement();
			String sql3 = "CREATE TABLE patient" 
					+ "(patient_id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "name TEXT,"
					+ "lastname TEXT,"
					+ "dob DATE,"
					+ "dof DATE," 
					+ "address TEXT," 
					+ "telephone TEXT,"
					+ "gender TEXT," 
					+ "problem TEXT," 
					+ "doctor_id INTEGER,"
					+ "FOREIGN KEY('doctor_id') REFERENCES doctor(doctor_id) ON DELETE SET NULL ON UPDATE CASCADE)";

			stmt3.executeUpdate(sql3);
			stmt3.close();

			Statement stmt4 = c.createStatement();
			String sql4 = "CREATE TABLE prosthetic " 
					+ "(prosthetic_id INTEGER PRIMARY KEY AUTOINCREMENT," 
					+ "type TEXT,"
					+ "material TEXT," 
					+ "dimension TEXT," 
					+ "number_of_failures INTEGER,"
					+ "failures TEXT NULL," 
					+ "price REAL," 
					+ "available BOOLEAN DEFAULT true,"
					+ "patient_id INTEGER," //9
					+ "hospital_id INTEGER," 
					+ "FOREIGN KEY('hospital_id') REFERENCES hospital('hospital_id') ON DELETE SET NULL ON UPDATE CASCADE,"
					+ "FOREIGN KEY('patient_id') REFERENCES patient('patient_id') ON DELETE SET NULL ON UPDATE CASCADE)";

			stmt4.executeUpdate(sql4);
			stmt4.close();

			Statement stmt5 = c.createStatement();
			String sql5 = "CREATE TABLE biomedical_engineer" 
					+ "(be_id	INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "name 	TEXT,"
					+ "lastname		TEXT,"
					+ "telephone TEXT)";


			stmt5.executeUpdate(sql5);
			stmt5.close();

			Statement stmt6 = c.createStatement();
			String sql6 = "CREATE TABLE biomed_pros" 
					+ "(prosID	INTEGER REFERENCES prosthetic(prosthetic_id) ON DELETE SET NULL ON UPDATE CASCADE," 
					+ "beID INTEGER  REFERENCES biomedical_engineer(be_id) ON DELETE SET NULL ON UPDATE CASCADE,"
					+ "PRIMARY KEY(prosID,beID))";

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
			
			Statement stmtSeq = c.createStatement();
			String sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES('doctor', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('patient', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('hospital', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('prosthetic', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('biomedical_engineer', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			stmtSeq.close();
			
		

			// Close database connection
			// c.close();
			// System.out.println("Database connection closed.");
			
			stmt6 = c.createStatement();
			String sqlbiomed1 = "INSERT INTO biomedical_engineer (name, lastname) VALUES ('Silvia','Torres') "; 
			stmt6.executeUpdate(sqlbiomed1);
			
			
			String sqlbiomed2 = "INSERT INTO biomedical_engineer (name, lastname) VALUES ('Hector','Fernandez') "; 
			stmt6.executeUpdate(sqlbiomed2);
			stmt6.close();
			
			
			
		} catch (SQLException e) {
			if (e.getMessage().contains("already exists")) {
			}else{
				e.printStackTrace();
			}
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
	@Override
	public BEManagerInterface getBiomedManager() {
		return biomed;
	}


	public int getLastId() {
		int result = 0;
		try {
			String query = "SELECT last_insert_rowid() AS lastId";
			PreparedStatement p = c.prepareStatement(query);
			ResultSet rs = p.executeQuery();
			result = rs.getInt("lastId");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void initializeDoctors(String name, String telephone, String department, int hospital_id){
		try {
		Statement stmt = c.createStatement();
		String sql = "INSERT INTO doctor (name, telephone, department, hospital_id) "
				+ "VALUES ('" + name + "', '" + telephone + "','"+ department + "', '" + hospital_id + "');";
			stmt.executeUpdate(sql);
			stmt.close();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void initializeHospitals(String name, String location, String telephone) {
		try {
			Statement stat = c.createStatement();
			String sql = "INSERT INTO hospital (name, location, telephone)"
					+ " VALUES ('"+name+"' , '"+location+"' , '"+telephone+"')";
			stat.executeUpdate(sql);
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void initializeBiomedics(String name, String lastname) {
		try {
			Statement stat = c.createStatement();
			String sql = "INSERT INTO biomedical_engineer (name, lastname)"
					+ " VALUES ('"+name+"' , '"+lastname+"')";
			stat.executeUpdate(sql);
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	 	

	public void deleteTables() {
		try {

			// Drop tables: begin
			Statement stmt1 = c.createStatement();
			String sql1 = "DROP TABLE doctor";
			stmt1.executeUpdate(sql1);
			stmt1.close();
			Statement stmt2 = c.createStatement();
			String sql2 = "DROP TABLE patient";
			stmt2.executeUpdate(sql2);
			stmt2.close();
			Statement stmt3 = c.createStatement();
			String sql3 = "DROP TABLE hospital";
			stmt3.executeUpdate(sql3);
			stmt3.close();
			Statement stmt4 = c.createStatement();
			String sql4 = "DROP TABLE prosthetic";
			stmt4.executeUpdate(sql4);
			stmt4.close();
			Statement stmt5 = c.createStatement();
			String sql5 = "DROP TABLE biomedical_engineer";
			stmt5.executeUpdate(sql5);
			stmt5.close();
			Statement stmt6 = c.createStatement();
			String sql6 = "DROP TABLE biomed_pros";
			stmt6.executeUpdate(sql6);
			stmt6.close();
			System.out.println("Tables removed.");
			// Drop tables: end

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
