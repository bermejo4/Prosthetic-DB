package db.inteface;

import java.util.List;

import pojos.*;

public interface BEManagerInterface {

	public void register(Biomedical_Eng BE);

	public void login(Biomedical_Eng BE);
	
	public void delete(Prosthetic pros);

	public void upadate(Prosthetic pros);

	public void insert(Prosthetic pros);

	public Prosthetic getProsthetic(int prostheticID); 
	
	public void design(int prosthetic_id, int be_id);
	
	public List<Prosthetic> showProsthetic();
	
	public void addBiomedbyRegister(Biomedical_Eng biomed);


}

//preguntas: pq salen todos los initialized repetidos
// pq esta fallando el modify ?
//metodo design prosthetic para link los dos?
//hacer en search go back
