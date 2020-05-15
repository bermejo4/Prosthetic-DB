package db.inteface;
import pojos.Hospital;
import pojos.Patient;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import db.inteface.*;
import db.inteface.*;



public interface PatientManagerInterface {
	
	public void register(Patient patient);
	public void login(Patient patient);
	public void viewDate(String telephone);
	public Hospital selectHospitalByID(int id);
	public ArrayList<Hospital> showHospitals();
	public void addpatientbyRegister(Patient pat);
	public void assignPatientToAHospital(int hos_id, Patient pat);
	public Patient searchSpecificPatientByTelephone(String telephone);
}

