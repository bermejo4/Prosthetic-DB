package jdbc;
import java.sql.*;

public class SQLConnect {
	private static Connection c;
	
	public static void main(String args[]) {
		
			// Open database connection
			//ESTE CÓDIGO YA ESTÁ MODIFICADO Y TERMINADO (#firmado: Bermejo)
			//Class.forName("org.sqlite.JDBC");
			//Connection c = DriverManager.getConnection("jdbc:sqlite:./db/Prosthetic_DB.db");
			//c.createStatement().execute("PRAGMA foreign_keys=ON");
			//System.out.println("Database connection opened.");
			db.classes.Utilities.OpenDB(c);
			
			// Here is where I do things with the database
			
			// Close database connection
			//c.close();
			//System.out.println("Database connection closed.");
			db.classes.Utilities.CloseDB(c);
	}
	
}
