package db.inteface;
import pojos.*;
import java.sql.*;
import java.util.List;


public interface DoctorManagerInterface {
	public void register(Doctor doc);
	public void login(Doctor doc);
	public void update(Patient pat);
	public void delete(Patient pat);
	public void addPatient(Patient pat);
	public void selectProsthetic(Prosthetic prost);
	public void assignProstheticDOF(Date date, Patient pac);
	public List<Patient> searchPatientByTelephone(String tel);
	public Patient searchSpecificPatientById(int num_id);
}
