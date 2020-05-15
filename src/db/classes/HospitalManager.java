package db.classes;

import db.inteface.*;

import pojos.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			String sql = "SELECT * FROM prosthetic WHERE available LIKE 1";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				Integer prosthId = rs.getInt("prosthetic_id");
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
			String sql = "INSERT INTO prosthetic(hospital_id)"
					+ "VALUES (?);";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, hospital_id);
			prep.executeUpdate();
			prep.close();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void addhospitalbyRegister(Hospital hosp) {
		//Insert the provided patient pat
		try {
			String sql = "INSERT INTO hospital (name, location, telephone)"
					+ " VALUES (?,?,?);";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, hosp.getName());
			prep.setString(2, hosp.getAddress());
			prep.setString(3, hosp.getTelephone());
			prep.executeUpdate();
			prep.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	public Hospital getHospital(int hospital_id){
		Hospital newHosp= new Hospital();
		
		try {
			String sql = "SELECT * FROM hospital WHERE hospital_id = ? ";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, hospital_id);
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()) {
				int hospId = rs.getInt(1);
				String hospName = rs.getString(2);
				String hospLocation = rs.getString(3);
				String hospTelephone = rs.getString(4);
				
				newHosp = new Hospital(hospId, hospName, hospLocation, hospTelephone);
			}
			
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	return newHosp;
	}
	
	public Hospital searchSpecificHospitalByTelephone(String telephone) {
		Hospital hosp=new Hospital();
		try {
			String sql = "SELECT hospital WHERE telephone LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1,telephone);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("hospital_id");
				String name=rs.getString("name");
				String location=rs.getString("location");
				String tele=rs.getString("telephone");
				int pat_id=rs.getInt("patient_id");
				Patient pat = new Patient(pat_id);	
				hosp=new Hospital(id,name,location,telephone,pat);
				
				}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return hosp;
	}
	
	
}


