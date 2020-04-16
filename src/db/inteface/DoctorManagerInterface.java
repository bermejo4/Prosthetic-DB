package db.inteface;
import pojos.*;
import java.sql.*;
import java.util.List;


public interface DoctorManagerInterface {
	public void register(Doctor doc);
	public void login(Doctor doc);
	public void modify(Patient pat, Date date, int id);
	public void delete(Patient pat);
	public void addPatient(Patient pat);
	public List<Prosthetic> selectProsthetic(Prosthetic prost, String col, String find);
	public void assignProstheticDOF(Date date, Patient pac);
	public List<Patient> searchPatientByTelephone(String tel);
	public Patient searchSpecificPatientById(int num_id);
}
