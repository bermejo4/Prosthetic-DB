package jdbc;
import java.sql.*;

public class SQLConnect {

	public static void main(String args[]) {
		try {
			// Open database connection
			//ESTE CÓDIGO YA ESTÁ MODIFICADO Y TERMINADO (#firmado: Bermejo)
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:./db/Prosthetic_DB.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			
			// Here is where I do things with the database
			
			// Close database connection
			c.close();
			System.out.println("Database connection closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}