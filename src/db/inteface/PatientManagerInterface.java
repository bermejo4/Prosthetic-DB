package db.inteface;
import pojos.Patient;
import java.sql.Date;
import java.util.List;


public interface PatientManagerInterface {
	public void register(Patient patient);
	public void login(Patient patient);
	public void viewDate(Date date);
}
