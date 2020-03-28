package db.classes;

import db.inteface.*;

import pojos.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HospitalManager implements HospitalManagerInterface {
	private Connection c;

	public void register(Hospital hospital) {

	}

	public void login(Hospital hospital) {

	}

	public List<Prosthetic> showProsthetics() {
	//we use this method to link a prosthetic to the catalogue of a hospital
	List<Prosthetic> prostheticList = new ArrayList<Prosthetic>();
					
	//we get all the prosthetic availables 
					
	try {
			String sql = "SELECT * FROM prosthetic";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()) {
				Integer prosthId = rs.getInt(prosthId);
				
			}
						
	}
		}

	public void buy(int hospital_id, int prosthetic_id) {

	}
}
