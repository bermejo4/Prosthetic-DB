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
				String prosMaterial = rs.getString("material");
				float prosPrice = rs.getFloat("price");
				String prosDimensions = rs.getString("dimension");
				String prosFailures = rs.getString("failures");
				int prosnumbFail = rs.getInt("number_of_failures");
				boolean prosAvailable = rs.getBoolean("available");
				int pati_id = rs.getInt("patient_id");
				int hospi_id = rs.getInt("hospital_id");
				Patient p = new Patient(pati_id);
				Hospital h = new Hospital(hospi_id);
				
				Prosthetic newprosthetic = new Prosthetic(prosthId, prosthType, prosMaterial, prosPrice, prosDimensions, prosFailures,prosnumbFail,
						prosAvailable, p, h);
				prostheticList.add(newprosthetic);

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
			String sql = "SELECT * FROM hospital WHERE telephone LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1,telephone);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("hospital_id");
				String name=rs.getString("name");
				String location=rs.getString("location");
				String tele=rs.getString("telephone");
				hosp=new Hospital(id,name,location,telephone);
				
				}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return hosp;
	}
	
	
	
	public Hospital getHospitalwithPatient(int hospital_id) {
		Hospital hosp = new Hospital();
		try {
			String sql = "SELECT * FROM hospital WHERE hospital_id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, hospital_id);
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()) {
				int hospId = rs.getInt(1);
				String hospName = rs.getString(2);
				String hospAddress = rs.getString(3);
				String hospTel = rs.getString(4);
				
				hosp = new Hospital(hospId, hospName, hospAddress, hospTel);
				
			}
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return hosp;
	}
	
	public ArrayList<Prosthetic> getProstheticsFromHospital(Hospital hospital){
		ArrayList<Prosthetic> prostheticsInHospital = new ArrayList<Prosthetic>();
		try {
			String sql="SELECT * FROM prosthetic WHERE hospital_id LIKE ?";
			PreparedStatement prep= c.prepareStatement(sql);
			prep.setInt(1, hospital.getId());
			ResultSet rs= prep.executeQuery();
			
			while (rs.next()) {
				Integer prosthId = rs.getInt("prosthetic_id");
				String prosthType = rs.getString("type");
				String prosMaterial = rs.getString("material");
				float prosPrice = rs.getFloat("price");
				String prosDimensions = rs.getString("dimension");
				String prosFailures = rs.getString("failures");
				int prosnumbFail = rs.getInt("number_of_failures");
				boolean prosAvailable = rs.getBoolean("available");
				int pati_id = rs.getInt("patient_id");
				//int hospi_id = rs.getInt(hospital.getId());
				Patient p = new Patient(pati_id);

				Prosthetic newprosthetic = new Prosthetic(prosthId, prosthType, prosMaterial, prosPrice, prosDimensions, prosFailures,prosnumbFail, prosAvailable, p, hospital);
				prostheticsInHospital.add(newprosthetic);

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prostheticsInHospital;
	}
	
	
}


