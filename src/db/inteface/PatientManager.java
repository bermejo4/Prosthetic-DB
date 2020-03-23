package db.inteface;
import pojos.Patient;
import java.sql.Date;


public interface PatientManager {
	public void register(Patient patient);
	public void login(Patient patient);
	public void viewDate(Date date);
}
