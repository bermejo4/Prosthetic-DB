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

	public void update(Patient pat) {
		try {
			//Update every aspect for a particular dog
			String sql = "UPDATE dogs SET name=?, lastname=?, dob=?, dof=?, address=?, telephone=?, gender=?, problem=?, doc_id=? WHERE id=?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setString(1,pat.getName());
			s.setString(2, pat.getLastname());
			s.setDate(3, pat.getDob());
			s.setDate(4, pat.getDof());
			s.setString(5, pat.getAddres());
			s.setFloat(6, pat.getTelephone());
			s.setString(7, pat.getGender());
			s.setString(8, pat.getProblem());
			s.setInt(9, pat.getDoctor_id());
			s.executeUpdate();
			s.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	public void addPatient(Patient pat) {
		//Insert the provided patient pat
		try {
			String sql = "INSERT INTO patients (name, lastname, dob, dof, address, telephone, gender, problem, doctor_id)"
					+ " VALUES (?,?,?,?,?,?,?,?,?);";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, pat.getName());
			prep.setString(2, pat.getLastname());
			prep.setDate(3, pat.getDob());
			prep.setDate(4, pat.getDof());
			prep.setString(5, pat.getAddres());
			prep.setFloat(6, pat.getTelephone());
			prep.setString(7, pat.getGender());
			prep.setString(8, pat.getProblem());
			prep.setInt(9, pat.getDoctor_id());
			prep.executeUpdate();
			prep.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	public void selectProsthetic(Prosthetic prost) {

	}

	public void assignProsthetic(Date date) {

	}

	public List<Patient> searchPatientByTelephone(float tel) {
		//create an empty list of Patients
		List<Patient> patientsList = new ArrayList<Patient>();
		//search for all patients that fit the name
		try {
			String sql = "SELECT * FROM patient WHERE telephone LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			//AQUÍ
			prep.setFloat(1, tel);
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("patient_id");
				String name = rs.getString("name");
				String lastname = rs.getString("lastname");
				Date dob = rs.getDate("dob");
				Date dof = rs.getDate("dof");
				String address = rs.getString("address");
				float telephone = rs.getFloat("telephone");
				String gender = rs.getString("gender");
				String problem = rs.getString("problem");
				int doctor_id=rs.getInt("doc_id");
				// create a new patient
				Patient newpatient = new Patient(id,name,lastname,telephone,dof,dob,gender,problem,address,doctor_id);
				// add it to the list
				patientsList.add(newpatient);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		// Return the list
		return patientsList;
	}

}