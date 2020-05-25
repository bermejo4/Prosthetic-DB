package db.inteface;

import pojos.*;
import java.util.List;

public interface HospitalManagerInterface {

	public List<Prosthetic> showProsthetics();
	public void buy(int hospital_id, int prosthetic_id);
	public void addhospitalbyRegister(Hospital hosp);
	public Hospital getHospital(int hospital_id);
	public Hospital searchSpecificHospitalByTelephone(String telephone);
	public Hospital getHospitalwithPatient (int hospital_id);
	
	
}
