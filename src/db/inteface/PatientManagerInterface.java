package db.inteface;
import pojos.Hospital;
import pojos.Patient;
import java.sql.Date;
import java.util.List;
import db.inteface.*;
import db.inteface.*;



public interface PatientManagerInterface {
	
	public void register(Patient patient);
	public void login(Patient patient);
	public void viewDate(String telephone);
	public Hospital selectHospitalByID(int id);
	public List<Hospital> showHospitals();
}
