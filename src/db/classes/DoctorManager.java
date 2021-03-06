package db.classes;

import pojos.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import db.inteface.*;

public class DoctorManager implements DoctorManagerInterface {

	private Connection c;

	public DoctorManager(Connection c) {
		this.c = c;
	}


	public void modify(Patient pat, Date dof, int id) {
		try {
			//Update every aspect for a particular patient including dof
			String sql = "UPDATE patient SET name=?, lastname=?, dob=?, dof=?, address=?, telephone=?, gender=?, problem=?, doctor_id=? WHERE patient_id=?";
			PreparedStatement mod = c.prepareStatement(sql);
			mod.setString(1,pat.getName());
			mod.setString(2, pat.getLastname());
			mod.setDate(3, pat.getDob());
			mod.setDate(4, dof);
			mod.setString(5, pat.getAddres());
			mod.setString(6, pat.getTelephone());
			mod.setString(7, pat.getGender());
			mod.setString(8, pat.getProblem());
			mod.setInt(9, pat.getDoctor().getId());
			mod.setInt(10, id);
			mod.executeUpdate();
			mod.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	public void insertDepartmentAndHospital(int id, String department, int hospital_id) {
		String sql="UPDATE doctor SET department=?, hospital_id=? WHERE doctor_id=?";		
		try {
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, department);
			prep.setInt(2, hospital_id);
			prep.setInt(3, id);
			prep.executeUpdate();
			prep.close();
		} catch (SQLException e) {
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
			prep.setInt(8, pat.getDoctor().getId());
			prep.executeUpdate();
			prep.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	public List<Prosthetic> selectProsthetic(String col, String find) {
		List<Prosthetic> prostList = new ArrayList<Prosthetic>();
		try {
			String sql = "SELECT * FROM prosthetic WHERE "+col+" LIKE ?";
			PreparedStatement find1 = c.prepareStatement(sql);
			find1.setString(1, "%"+find+"%");
			ResultSet rs = find1.executeQuery();
			while(rs.next()) {
				int prosthetic_id = rs.getInt("prosthetic_id");
				String material = rs.getString("material");
				String type = rs.getString("type");
				String dimension = rs.getString("dimension");
				int numOF=rs.getInt("number_of_failures");
				String failures = rs.getString("failures");
				float price=rs.getFloat("price");
				boolean available=rs.getBoolean("available");
				int patient_id=rs.getInt("patient_id");
				int hospital_id=rs.getInt("hospital_id");
				Patient patient = new Patient(patient_id);
				Hospital hospital= new Hospital(hospital_id);
				Prosthetic newprosthetic = new Prosthetic(prosthetic_id, type, material, price, dimension, failures, numOF, available, patient, hospital);
				prostList.add(newprosthetic);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return prostList;
	}

	public void assignDOF(Date date, Patient pat) {
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
	
	public void assignProstheticToPatient(int prost_id, int pat_id) {
		try {
			String sql = "UPDATE prosthetic SET patient_id=?, available=? WHERE prosthetic_id=?";
			PreparedStatement up = c.prepareStatement(sql);
			up.setInt(1, pat_id);
			up.setBoolean(2, false);
			up.setInt(3, prost_id);
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
			prep.setString(1, "%"+tel+"%");
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("patient_id");
				String name = rs.getString("name");
				String lastname = rs.getString("lastname");
				Date dob = rs.getDate("dob");
				Date dof = rs.getDate("dof");
				String address = rs.getString("address");
				String telephone = rs.getString("telephone");
				String gender = rs.getString("gender");
				String problem = rs.getString("problem");
				int doctor_id=rs.getInt("doctor_id");
				Doctor doctor =new Doctor(doctor_id);
				// create a new patient
				Patient newpatient = new Patient(id,name,lastname,telephone,dob,dof,gender,problem,address,doctor);
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
				Date dof = rs.getDate("dof");
				String address = rs.getString("address");
				String telephone = rs.getString("telephone");
				String gender = rs.getString("gender");
				String problem = rs.getString("problem");
				int doctor_id=rs.getInt("doctor_id");
				Doctor doctor = new Doctor(doctor_id);
				// create a new patient
				patientfound = new Patient(id,name,lastname,telephone,dob,dof,gender,problem,address,doctor);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		// Return the patient
		return patientfound;
	}
	public Doctor searchSpecificDoctorByTelephone(String telephone) {
		Doctor doctorfound=new Doctor();
		try {
			String sql = "SELECT * FROM doctor WHERE telephone LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, telephone);
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("doctor_id");
				String name = rs.getString("name");
				String lastname = rs.getString("lastname");
				String tele = rs.getString("telephone");
				int hosp_id=rs.getInt("hospital_id");
				Hospital hosp = new Hospital(hosp_id);
				doctorfound= new Doctor(id,name,lastname,tele, hosp);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return doctorfound;
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
	
	public void addDoctorbyRegister(Doctor doc) {
		//Insert the provided patient pat
		try {
			String sql = "INSERT INTO doctor (name, lastname,  telephone)"
					+ " VALUES (?,?,?);";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, doc.getName());
			prep.setString(2, doc.getLastname());
			prep.setString(3, doc.getTelephone());
			prep.executeUpdate();
			prep.close();
			
			System.out.println("Succesfully registered!!");
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	public ArrayList<Doctor> doctorsInDatabase() {
		ArrayList<Doctor> docList = new ArrayList<Doctor>();
		try {
			Statement stmt = c.createStatement();
			String sql = "SELECT * FROM doctor";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				
				int id=rs.getInt("doctor_id");
				String name=rs.getString("name");
				String lastname=rs.getString("lastname");
				String tel=rs.getString("telephone");
				int hospital_id=rs.getInt("hospital_id");
				Hospital hospital= new Hospital(hospital_id);
				Doctor doctor=new Doctor(id,name,lastname,tel,hospital);
				docList.add(doctor);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return docList;
	}
	
	public ArrayList<Doctor> doctorsInHospital(int hospitalId) {
		ArrayList<Doctor> docList = new ArrayList<Doctor>();
		try {
			String sql = "SELECT * FROM doctor WHERE hospital_id LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, hospitalId);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				
				int id=rs.getInt("doctor_id");
				String name=rs.getString("name");
				String lastname=rs.getString("lastname");
				String tel=rs.getString("telephone");
				String department=rs.getString("department");
				int hospital_id=rs.getInt("hospital_id");
				Hospital hospital= new Hospital(hospital_id);
				Doctor doctor=new Doctor(id,name,lastname,tel,department,hospital);
				docList.add(doctor);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return docList;
	}
	
	public void assignDoctortoHospital(int hospital_id, String doctelephone) {
		try {
			String sql = "UPDATE doctor SET hospital_id=? WHERE telephone=?";
			PreparedStatement prep = c.prepareStatement(sql);
			
			prep.setInt(1, hospital_id);
			prep.setString(2, doctelephone);
			
			prep.executeUpdate();
			prep.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Patient> allPatientsOfDoctor(int doctor_id) {
		ArrayList<Patient> patientsOfDoctor = new ArrayList<Patient>();
		try {
			String sql = "SELECT * FROM patient WHERE doctor_id LIKE ?";
			PreparedStatement prep=c.prepareStatement(sql);
			prep.setInt(1, doctor_id);
			ResultSet rs= prep.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("patient_id");
				String name = rs.getString("name");
				String lastname = rs.getString("lastname");
				Date dob = rs.getDate("dob");
				Date dof = rs.getDate("dof");
				String address = rs.getString("address");
				String telephone = rs.getString("telephone");
				String gender = rs.getString("gender");
				String problem = rs.getString("problem");
				Doctor doctor = new Doctor(doctor_id);
				
				Patient ptmp=new Patient(id,name,lastname,telephone,dob,dof,gender,problem,address,doctor);
				patientsOfDoctor.add(ptmp);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patientsOfDoctor;
	}
	
	
	

	

	
	
}