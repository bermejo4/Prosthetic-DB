package db.inteface;
import pojos.*;
import java.sql.*;
import java.util.List;


public interface DoctorManagerInterface {
	public void register(Doctor doc);
	public void login(Doctor doc);
	public void update(Patient pat);
	public void addPatient(Patient pat);
	public void selectProsthetic(Prosthetic prost);
	public void assignProsthetic(Date date);
	public List<Patient> searchPatientByTelephone(String tel);
}
