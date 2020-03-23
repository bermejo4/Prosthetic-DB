package db.classes;

import java.sql.Connection;
import java.sql.DriverManager;

//-----------------------------------------------
//ESTE CÓDIGO ESTÁ EN PRUEBAS (#firmado: Bermejo)
//-----------------------------------------------
//VA A SERVIR PARA NO TENER QUE PONER SIEMPRE LAS 15 LINEAS DE CODIGO PARA ABRIR Y CERRAR LA BASE DE DATOS

public class Utilities {
	public static void OpenDB(Connection c) {
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
	
	public static void CloseDB(Connection c) {
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
}
