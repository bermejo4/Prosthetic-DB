package db.inteface;

import pojos.*;
import java.util.List;

public interface HospitalManager {

	public List<Prosthetic> showProsthetics();
	public void buy(int hospital_id, int prosthetic_id);
	public void register(Hospital hospital);
	public void login(Hospital hospital);
	
	
	
	
	
}
