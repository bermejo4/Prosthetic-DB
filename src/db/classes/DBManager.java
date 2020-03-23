package db.classes;
import java.sql.*;

import db.inteface.*;

public class DBManager implements DBManagerinterface{
	private Connection c;
	
	public void connect() {
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/Prosthetic_DB.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		try {			
			// Close database connection
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/Prosthetic_DB.db");
			c.close();
			System.out.println("Database connection closed.");
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
			connect();
			
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
			String sql2 = "CREATE TABLE employees "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name     TEXT     NOT NULL, "
					   + " dob      DATE	 NOT NULL, "
					   + " address  TEXT, "
					   + " salary   REAL,"
					   + " photo	BLOB,"
					   + " dep_id	INTEGER REFERENCES departments(id) ON UPDATE CASCADE ON DELETE SET NULL)";
			stmt2.executeUpdate(sql2);
			stmt2.close();
			Statement stmt3 = c.createStatement();
			String sql3 = "CREATE TABLE reports "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name     TEXT     NOT NULL, "
					   + " content  TEXT  	NOT NULL, "
					   + " date		DATE)";
			stmt3.executeUpdate(sql3);
			stmt3.close();
			Statement stmt4 = c.createStatement(); 
			String sql4 = "CREATE TABLE doctor "
					+ "(prosthetic_id	INTEGER NOT NULL UNIQUE,"
					+ "material 	TEXT,"
					+ "type		TEXT,"
					+ "dimension	TEXT"
					+ "failures   TEXT"
					+ "price 	REAL"
					+ "patient_id	INTEGER,"
					+ "hospital_id 	INTEGER"
					+ "PRIMARY KEY('prosthetic_id'),"
					+ "FOREIGN KEY('Hospital_id') REFERENCES hospital('hospital_id') ON DELETE SET NULL ON UPDATE CASCADE)";
			stmt4.executeUpdate(sql4);
			stmt4.close();
			System.out.println("Tables created.");
			// Create table: end
			
			// - Set initial values for the Primary Keys
			// - Don't try to understand this until JPA is explained
			// This is usually not needed, since the initial values
			// are set when the first row is inserted, but since we
			// are using JPA and JDBC in the same project, and JPA
			// needs an initial value, we do this.
			Statement stmtSeq = c.createStatement();
			String sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('departments', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('employees', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('reports', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			stmtSeq.close();
			
			// Close database connection
			c.close();
			System.out.println("Database connection closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
