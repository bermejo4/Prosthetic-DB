package db.classes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import pojos.Biomedical_Eng;
import pojos.Doctor;
import pojos.Hospital;
import pojos.Patient;
import pojos.Prosthetic;

public class BEManager implements db.inteface.BEManagerInterface {
	// shift control f para arreglar

	private Connection c;

	// we create a constructor
	public BEManager(Connection c) {
		this.c = c;
	}

	@Override
	public void register(Biomedical_Eng BE) {
		// TODO Auto-generated method stub

	}

	@Override
	public void login(Biomedical_Eng BE) {
		// TODO Auto-generated method stub

	}

	public void delete(Prosthetic pros) {
		try {
			String sql = "DELETE FROM prosthetic WHERE prosthetic_id = ?";
			PreparedStatement del = c.prepareStatement(sql);
			del.setInt(1, pros.getId());
			del.executeUpdate();
			del.close();
					
			
		}catch(Exception e){
			e.printStackTrace();
				
		}
		
		
	}
	@Override
	public void upadate(Prosthetic pros) {
		// we create a prepared statement for our connection
		try {
			String sql = "UPDATE prosthetic SET  material = ?, type = ?, dimension = ?, failures = ? ,price = ?, available = ?"
					+ "WHERE prosthetic_id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, pros.getMaterial());
			prep.setString(2, pros.getType());
			prep.setString(3, pros.getDimensions());
			prep.setString(4, pros.getFailures());
			prep.setFloat(5, pros.getPrice());
			prep.setBoolean(6, pros.getAvailable());
			prep.setInt(7, pros.getId());
			prep.executeUpdate();
			prep.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void insert(Prosthetic pros) {
		try {

			String sql = "INSERT INTO prosthetic ( type,material, dimension, failures, price, available) "
					+ "VALUES (?,?,?,?,?,?);";
			PreparedStatement prep = c.prepareStatement(sql);

			prep.setString(1, pros.getType());
			prep.setString(2, pros.getMaterial());
			prep.setString(3, pros.getDimensions());
			prep.setString(4, pros.getFailures());
			prep.setFloat(5, pros.getPrice());
			prep.setBoolean(6, pros.getAvailable());

			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Prosthetic getProsthetic(int prostheticID) {
		Prosthetic newProsthetic = null;

		try {
			//String sql = "SELECT * FROM prosthetic WHERE prosthetic_id = ?";

			 String sql = "SELECT * FROM prosthetic AS p JOIN Biomed_Pros AS bp ON p.prosthetic_id = bp.prosID"
			 + " JOIN biomedical_engineer AS be ON bp.beID = be.be_id WHERE p.prosthetic_id = ?";
			 
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, prostheticID);
			ResultSet rs = prep.executeQuery(); // we only get one answer
			List<Biomedical_Eng> biomedsLists = new ArrayList<Biomedical_Eng>();

			boolean prosCreated = false;
			while (rs.next()) {

				if (!prosCreated) {
//when doing joins we use numbers instead of column names 
					String pros_type = rs.getString(2);
					String material = rs.getString(3);
					String dimensions = rs.getString(4);
					String failures = rs.getString(6);
					float price = rs.getFloat(7);
					boolean available = rs.getBoolean(8);
					
					newProsthetic = new Prosthetic(prostheticID, pros_type, material, price, dimensions, failures, 	available);
					prosCreated = true;
				}

				
				  int beID = rs.getInt(13); 
				  String biomedName = rs.getString(14);
				  
				  Biomedical_Eng newbiomed = new Biomedical_Eng(beID,biomedName);
				   biomedsLists.add(newbiomed);
				 

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return newProsthetic;
	}

	public void design(int prosthetic_id, int be_id) {

		try {
			String sql = "INSERT INTO Biomed_Pros (prosthetic_id ,be_id) " + "VALUES (?,?);";
			PreparedStatement prep = c.prepareStatement(sql);

			prep.setInt(1, prosthetic_id);
			prep.setInt(2, be_id);

			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<Prosthetic> showProsthetic() {
		// Created empty list of prosthetics
		List<Prosthetic> prosList = new ArrayList<Prosthetic>();
		try {
			String sql = "SELECT * FROM prosthetic ";
			PreparedStatement find1 = c.prepareStatement(sql);
			ResultSet rs = find1.executeQuery();
			while (rs.next()) {
				int prosthetic_id = rs.getInt("prosthetic_id");
				String material = rs.getString("material");
				String type = rs.getString("type");
				String dimension = rs.getString("dimension");
				int numOF = rs.getInt("number_of_failures");
				String failures = rs.getString("failures");
				float price = rs.getFloat("price");
				boolean available = rs.getBoolean("available");
				int patient_id = rs.getInt("patient_id");
				int hospital_id = rs.getInt("hospital_id");
				Patient patient = new Patient(patient_id);
				Hospital hospital = new Hospital(hospital_id);
				Prosthetic newprosthetic = new Prosthetic(prosthetic_id, type, material, price, dimension, failures,
						numOF, available, patient, hospital);
				prosList.add(newprosthetic);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return prosList;

	}

	public void addBiomedbyRegister(Biomedical_Eng biomed) {
		// Insert the provided patient pat
		try {
			String sql = "INSERT INTO doctor (name, lastname,  telephone)" + " VALUES (?,?,?);";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, biomed.getName());
			prep.setString(2, biomed.getLastname());
			prep.setString(3, biomed.getTelephone());
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
//hacer delete de jpa
