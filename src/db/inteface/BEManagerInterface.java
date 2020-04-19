package db.inteface;

import java.util.List;

import pojos.*;

public interface BEManagerInterface {

	public void register(Biomedical_Eng BE);

	public void login(Biomedical_Eng BE);

	public void upadate(Prosthetic pros);

	public void insert(Prosthetic pros);

	//public List<Prosthetic> searchByID(int id);
	public List<Prosthetic> searchBytype(String type);


}
