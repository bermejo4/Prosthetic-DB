package jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLInsert {

	public static void main(String args[]) {
		try {
			// Open database connection
			//ESTE CODIGO NO HAY QUE MODIFICARLO, YA ESTÁ MODIFICADO (#firmado: Bermejo)
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:./db/Prosthetic_DB.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			
			//AQUI VA EL CÓDIGO DE NUESTRA BASE DE DATOS
			//HE DEJADO EL CODIGO ANTERIOR PARA TENERLO DE REFERENCIA (#firmado: Bermejo)

			//// Get the employee info from the command prompt
			//System.out.println("Please, input the department info:");
			//BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			//System.out.print("Name: ");
			//String name = reader.readLine();
			//System.out.print("Address: ");
			//String address = reader.readLine();

			//// Insert new record: begin
			//Statement stmt = c.createStatement();
			//String sql = "INSERT INTO departments (name, address) "
			//		+ "VALUES ('" + name + "', '" + address	+ "');";
			//stmt.executeUpdate(sql);
			//stmt.close();
			//System.out.println("Department info processed");
			//System.out.println("Records inserted.");
			// Insert new record: end

			// Close database connection
			c.close();
			System.out.println("Database connection closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
