package db.classes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.inteface.PatientManagerInterface;
import pojos.Biomedical_Eng;
import pojos.Doctor;
import pojos.Hospital;
import pojos.Patient;
import pojos.Prosthetic;

public class BEManager implements db.inteface.BEManagerInterface {
	

	private Connection c;

	// we create a constructor
	public BEManager(Connection c) {
		this.c = c;
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

			String sql = "INSERT INTO prosthetic (type,material, dimension, failures, price, available) "
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
		Prosthetic newProsthetic = new Prosthetic();

		try {

			 String sql = "SELECT * FROM prosthetic  WHERE prosthetic_id = ?";
			 
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, prostheticID);
			ResultSet rs = prep.executeQuery(); 

			while (rs.next()) {

					int id= rs.getInt(1);
					String pros_type = rs.getString(2);
					String material = rs.getString(3);
					String dimensions = rs.getString(4);
					//Integer number = rs.getInt(5);
					String failures = rs.getString(6);
					float price = rs.getFloat(7);
					boolean available = rs.getBoolean(8);
					
					newProsthetic = new Prosthetic(id, pros_type, material, price, dimensions, failures, available);
				 
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return newProsthetic;
	}
	
	public Prosthetic getProstheticWithPatient(int prostheticID) {
		Prosthetic newProsthetic = new Prosthetic();

		try {

			 String sql = "SELECT * FROM prosthetic WHERE prosthetic_id = ?";
			 
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, prostheticID);
			ResultSet rs = prep.executeQuery(); 

			while (rs.next()) {

					int id= rs.getInt(1);
					String pros_type = rs.getString(2);
					String material = rs.getString(3);
					String dimensions = rs.getString(4);
					//Integer number = rs.getInt(5);
					String failures = rs.getString(6);
					float price = rs.getFloat(7);
					boolean available = rs.getBoolean(8);
					int pat_id = rs.getInt(9);
					Patient pat = new Patient(pat_id);
					
					newProsthetic = new Prosthetic(id, pros_type, material, price, dimensions, failures, available, pat);
				 
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return newProsthetic;
	}
	
	@Override
	public Prosthetic getProstheticMM(int prostheticID) {
		Prosthetic newProsthetic = new Prosthetic();

		try {

			 String sql = "SELECT * FROM prosthetic AS p JOIN biomed_pros AS bp ON p.prosthetic_id = bp.prosID"
			 + " JOIN biomedical_engineer AS be ON bp.beID = be.be_id WHERE p.prosthetic_id = ?";
			 
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, prostheticID);
			ResultSet rs = prep.executeQuery(); // we only get one answer
			List<Biomedical_Eng> biomedsLists = new ArrayList<Biomedical_Eng>();

			boolean prosCreated = false;
			while (rs.next()) {

				if (!prosCreated) {//to get one prosthetic and not repeated ones
					//when doing joins we use numbers instead of column names 
					int prosID = rs.getInt(1);
					String pros_type = rs.getString(2);
					String material = rs.getString(3);
					String dimensions = rs.getString(4);
					String failures = rs.getString(6);
					float price = rs.getFloat(7);
					boolean available = rs.getBoolean(8);
					
					newProsthetic = new Prosthetic(prosID, pros_type, material, price, dimensions, failures, available);
					prosCreated = true;
				}

				
				  int beID = rs.getInt(13); 
				  String biomedName = rs.getString(14);
				  
				  Biomedical_Eng newbiomed = new Biomedical_Eng(beID,biomedName);
				  biomedsLists.add(newbiomed);
				 

			}
			
			newProsthetic.setBiomeds(biomedsLists);
		
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return newProsthetic;
	}
	
	public Biomedical_Eng searchSpecificBiomedByTelephone(String telephone) {
		Biomedical_Eng biomed=new Biomedical_Eng();
		try {
			String sql = "SELECT * FROM biomedical_engineer WHERE telephone LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1,telephone);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("be_id");
				String name=rs.getString("name");
				String lastname=rs.getString("lastname");
				String tele = rs.getString("telephone");
				biomed = new Biomedical_Eng(id,name,lastname,tele);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return biomed;
		
		
	}

	@Override
	public void design(int prosthetic_id, int be_id) {

		try {
			//si el id ya existe solo agregarle una prosthetic 
		
			String sql = "INSERT INTO biomed_pros (prosID , beID) " + "VALUES (?,?);";
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
			String sql = "INSERT INTO biomedical_engineer (name, lastname,  telephone)" + " VALUES (?,?,?);";
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
	
	
	public List<Biomedical_Eng> showBiomedics() {
		// Created empty list of biomedics
		List<Biomedical_Eng> biomedList = new ArrayList<Biomedical_Eng>();
		List<Prosthetic> prosListOfBiomed = new ArrayList<Prosthetic>();
		Biomedical_Eng newbiomed = new Biomedical_Eng();
		Prosthetic prosthetic = new Prosthetic();
		try {
			String sql = "SELECT * FROM biomedical_engineer ";
			PreparedStatement find1 = c.prepareStatement(sql);
			ResultSet rs = find1.executeQuery();
			while (rs.next()) {
				int be_id = rs.getInt("be_id");
				String name = rs.getString("name");
				String lastname = rs.getString("lastname");
				String telephone = rs.getString("telephone");
				
				newbiomed = new Biomedical_Eng(be_id,name, lastname,telephone);
				try {
					String sql_1 = "SELECT * FROM prosthetic AS p JOIN biomed_pros AS bp ON p.prosthetic_id = bp.prosID WHERE bp.beID LIKE ?";
					PreparedStatement prep = c.prepareStatement(sql_1);
					prep.setInt(1,be_id);
					ResultSet rss = prep.executeQuery();
					while (rss.next()) {
						
						int id= rss.getInt(1);
						String pros_type = rss.getString(2);
						String material = rss.getString(3);
						String dimensions = rss.getString(4);
						Integer number = rss.getInt(5);
						String failures = rss.getString(6);
						float price = rss.getFloat(7);
						boolean available = rss.getBoolean(8);
						int pat_id = rss.getInt(9);
						Patient pat = new Patient(pat_id);
						int hosp_id = rss.getInt(10);
						Hospital hospital= new Hospital(hosp_id);
						
						prosthetic=new Prosthetic(id, pros_type, material, price, dimensions, failures, number, available, pat, hospital);
						prosListOfBiomed.add(prosthetic);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				newbiomed.setProstheticsList(prosListOfBiomed);
				biomedList.add(newbiomed);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return biomedList;

	}
	


}

