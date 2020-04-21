package db.classes;
import db.inteface.*;
import pojos.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientManager implements PatientManagerInterface {
	
	private Connection c;
	
	public PatientManager(Connection connection) {
		this.c=connection;
	}
	
	public void register(Patient patient) {
		
	}
	
	public void login(Patient patient) {
		
	}
	
	
	public void viewDate(float telephone) {
		try {
			String sql = "SELECT d.date_of_fitting FROM doctor AS d JOIN patient AS p ON d.doctor_id=p.doc_id; WHERE p.telephone LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, "%"+telephone+"%");
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				Date dateOf = rs.getDate("dof");
				System.out.println("Hello! Your next appointment is: " + dateOf);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Hospital> showHospitals() {
		// To show all Hospitals in our data base
		List<Hospital> hospitalList = new ArrayList<Hospital>();
		try {
			String sql = "SELECT * FROM hospital";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				Integer hosId = rs.getInt("id");
				String hosName = rs.getString("name");
				String hosLocat = rs.getString("location");
				String hosTele = rs.getString("telephone");
				
				Hospital newHospital = new Hospital(hosId, hosName, hosLocat, hosTele);
				hospitalList.add(newHospital);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return hospitalList;
	}

	//No esta bien hecho, lo tengo que cambiar!!
	public List<Hospital> selectHospital(String nom, String find) {
		List<Hospital> hospitalList = new ArrayList<Hospital>();
		try {
			String sql = "SELECT * FROM hospital WHERE "+nom+" LIKE ?";
			PreparedStatement find1 = c.prepareStatement(sql);
			find1.setString(1, "%"+find+"%");
			ResultSet rs = find1.executeQuery();
			while(rs.next()) {
				int hospital_id = rs.getInt("hospital_id");
				String name = rs.getString("name");
				String location = rs.getString("location");
				String telephone = rs.getString("telephone");
				Hospital newHospital = new Hospital(hospital_id, name, location, telephone);
				hospitalList.add(newHospital);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return hospitalList;
		
	}
}
