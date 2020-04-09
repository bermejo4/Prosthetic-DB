package db.inteface;
import pojos.Patient;
import java.sql.Date;
import java.util.List;
import db.inteface.*;
import db.inteface.*;



public interface PatientManagerInterface {
	
	public void register(Patient patient);
	public void login(Patient patient);
	public void viewDate(float telephone);
}
