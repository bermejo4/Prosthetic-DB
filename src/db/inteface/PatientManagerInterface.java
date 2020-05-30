package db.inteface;
import pojos.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import db.inteface.*;
import db.inteface.*;



public interface PatientManagerInterface {
	
	public void viewDate(String telephone);
	public Doctor selectDoctorByID(int id);
	public ArrayList<Doctor> showDoctors();
	public ArrayList<Hospital> showHospitals();
	public void addpatientbyRegister(Patient pat);
	public void assignPatientToADoctor(int doc_id, Patient pat);
	public Patient searchSpecificPatientByTelephone(String telephone);
	public Patient getPatient(int pat_id);
	public ArrayList<Patient> showPatients();
}

