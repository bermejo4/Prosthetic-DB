package db.classes;

import pojos.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
	
	public List<Patient> searchPatientByTelephone(float tel) {
		//- searchPatientByTelephone( telephone:Float) : List<Patient>
		//;
		//create an empty list of Patients
		List<Patient> patientsList = new ArrayList<Patient>();
		//search for all patients that fit the name
		try {
			String sql = "SELECT * FROM patient WHERE telephone LIKE ?";
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return patientsList;
	}

}