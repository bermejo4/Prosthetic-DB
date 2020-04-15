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
			s.setString(6, pat.getTelephone());
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
			String sql = "INSERT INTO patient (name, lastname, dob, address, telephone, gender, problem, doctor_id)"
					+ " VALUES (?,?,?,?,?,?,?,?);";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, pat.getName());
			prep.setString(2, pat.getLastname());
			prep.setDate(3, pat.getDob());
			//prep.setDate(4, pat.getDof());
			prep.setString(4, pat.getAddres());
			prep.setString(5, pat.getTelephone());
			prep.setString(6, pat.getGender());
			prep.setString(7, pat.getProblem());
			prep.setInt(8, pat.getDoctor_id());
			prep.executeUpdate();
			prep.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	public void selectProsthetic(Prosthetic prost) {
		
		
	}

	public void assignProstheticDOF(Date date, Patient pat) {
		try {
			String sql = "UPDATE patient SET dof=? WHERE patient_id=?";
			PreparedStatement up = c.prepareStatement(sql);
			up.setDate(1, date);
			up.setInt(2,pat.getId());
			up.executeUpdate();
			up.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public List<Patient> searchPatientByTelephone(String tel) {
		//create an empty list of Patients
		List<Patient> patientsList = new ArrayList<Patient>();
		//search for all patients that fit the name
		try {
			String sql = "SELECT * FROM patient WHERE telephone LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			//AQUÍ
			prep.setString(1, "%"+tel+"%");
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("patient_id");
				String name = rs.getString("name");
				String lastname = rs.getString("lastname");
				Date dob = rs.getDate("dob");
				//Date dof = rs.getDate("dof");
				String address = rs.getString("address");
				String telephone = rs.getString("telephone");
				String gender = rs.getString("gender");
				String problem = rs.getString("problem");
				int doctor_id=rs.getInt("doctor_id");
				// create a new patient
				Patient newpatient = new Patient(id,name,lastname,telephone,dob,gender,problem,address,doctor_id);
				// add it to the list
				//newpatient.toString();
				//System.out.println(newpatient.getName());
				patientsList.add(newpatient);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		// Return the list
		return patientsList;
	}
	public Patient searchSpecificPatientById(int num_id) {
		//search the patient that fit the id
		Patient patientfound=new Patient();
		try {
			String sql = "SELECT * FROM patient WHERE patient_id LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, num_id);
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("patient_id");
				String name = rs.getString("name");
				String lastname = rs.getString("lastname");
				Date dob = rs.getDate("dob");
				//Date dof = rs.getDate("dof");
				String address = rs.getString("address");
				String telephone = rs.getString("telephone");
				String gender = rs.getString("gender");
				String problem = rs.getString("problem");
				int doctor_id=rs.getInt("doctor_id");
				// create a new patient
				patientfound = new Patient(id,name,lastname,telephone,dob,gender,problem,address,doctor_id);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		// Return the patient
		return patientfound;
	}
	
	public void delete(Patient pat) {
		try {
			String sql = "DELETE FROM patient WHERE patient_id=?";
			PreparedStatement del =c.prepareStatement(sql);
			del.setInt(1, pat.getId());
			del.executeUpdate();
			del.close();
			System.out.println("-->Delete completed<--");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}