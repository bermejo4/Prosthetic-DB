package db.inteface;

import java.util.List;

import pojos.*;

public interface BEManagerInterface {
	
	public void delete(Prosthetic pros);

	public void upadate(Prosthetic pros);

	public void insert(Prosthetic pros);

	public Prosthetic getProstheticMM(int prostheticID); 
	
	public void design(int prosthetic_id, int be_id);
	
	public List<Prosthetic> showProsthetic();
	
	public List<Biomedical_Eng> showBiomedics();
	
	public void addBiomedbyRegister(Biomedical_Eng biomed);

	public Prosthetic getProsthetic(int prostheticID);
	public Prosthetic getProstheticWithPatient(int prostheticID);
	public Biomedical_Eng searchSpecificBiomedByTelephone(String telephone);

}

//preguntas: pq salen todos los initialized repetidos
// pq esta fallando el modify ?
//metodo design prosthetic para link los dos?
//hacer en search go back
