package db.classes;
import pojos.*;

import java.sql.*;
import db.inteface.*;


public class DoctorManager implements DoctorManagerInterface{
	
	private Connection c;
	
	public DoctorManager(Connection c) {
		this.c=c;
	}
	
	public void register(Doctor doc) {
	
	}
	public void login(Doctor doc) {
		
	}
	
	public void update(Doctor doc) {
		
	}
	
	public void addPatient(Patient pat) {
		
	}
	
	public void selectProsthetic(Prosthetic prost) {
		
	}
	
	public void assignProsthetic(Date date) {
		
	}
	
	public void searchPatientByTelephone(float tel) {
		//- searchPatientByTelephone( telephone:Float) : List<Patient>
		//;
	}

}