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

	public HospitalManager(Connection c) {
		this.c = c;
	}

	public void register(Hospital hospital) {

	}
 
	public void login(Hospital hospital) {

	}

	public List<Prosthetic> showProsthetics() {
		// we use this method to link a prosthetic to the catalogue of a hospital
		List<Prosthetic> prostheticList = new ArrayList<Prosthetic>();

		// we get all the available prosthetics 

		try {
			String sql = "SELECT * FROM prosthetic WHERE available LIKE true";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				Integer prosthId = rs.getInt("id");
				String prosthType = rs.getString("type");
				Prosthetic newProsthetic = new Prosthetic(prosthId, prosthType);
				prostheticList.add(newProsthetic);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return prostheticList;
	}

	
	
	public void buy(int hospital_id, int prosthetic_id) {
		//will link a prosthetic to a hospital
		
		try {
			String sql = "INSERT INTO prosthetic(hospital_id, prosthetic_id)"
					+ "VALUES (?,?);";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, hospital_id);
			prep.setInt(2, prosthetic_id);
			prep.executeUpdate();
			prep.close();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}

