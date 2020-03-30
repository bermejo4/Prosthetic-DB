package db.classes;
import db.inteface.*;
import pojos.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientManager implements PatientManagerInterface {
	
	private Connection c;
	
	public PatientManager(Connection c) {
		this.c=c;
	}
	public void register(Patient patient) {
		
	}
	public void login(Patient patient) {
		
	}
	public void viewDate(Date date) {
		System.out.println("Hello! Your next appointment is: " + date);
	}


}
