package db.classes;

import pojos.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.inteface.*;

public class DoctorManager implements DoctorManagerInterface {

	private Connection c;

	public DoctorManager(Connection c) {
		this.c = c;
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
			PreparedStatement prep = c.prepareStatement(sql);
			
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("patient_id");
				String name = rs.getString("name");
				String lastname = rs.getString("lastname");
				Date dob = rs.getDate("dob");
				String address = rs.getString("address");
				float telephone = rs.getFloat("telephone");
				String gender = rs.getString("gender");
				String problem = rs.getString("problem");
				int doctor_id=rs.getInt("doc_id");
				// create a new patient
				Patient newpatient = new Patient(id,name,lastname,telephone,dob,gender, String problem,
						String addres);
				
				
				
			}
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return patientsList;
	}

}