package db.inteface;
import pojos.Patient;
import java.sql.Date;
//jeje

public interface PatientManager {
	public void register(Patient patient);
	public void login(Patient patient);
	public void viewDate(Date date);
}
