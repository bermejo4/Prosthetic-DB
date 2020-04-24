package db.classes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import pojos.Biomedical_Eng;
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

	@Override
	public void upadate(Prosthetic pros) {
		// we create a prepared statement fot our connection
		try {
			String sql = "UPDATE prosthetic SET  material = ?, type = ?, dimension = ?, failures = ? ,price = ?"
					+ "WHERE prosthetic_id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, pros.getMaterial());
			prep.setString(2, pros.getType());
			prep.setString(3, pros.getDimensions());
			prep.setString(4, pros.getFailures());
			prep.setFloat(5, pros.getPrice());
			prep.setInt(6, pros.getId());
			prep.executeUpdate();
			prep.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void insert(Prosthetic pros) {
		try {
			String sql = "INSERT INTO prosthetic ( material, type , dimension, price) "
		+ "VALUES (?,?,?,?,?);";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, pros.getMaterial());
			prep.setString(2, pros.getType());
			prep.setString(3, pros.getDimensions());
			// prep.setString(4, pros.getFailures());
			// prep.setFloat(3, pros.getNumberFailures());
			prep.setFloat(4, pros.getPrice());

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
			String sql = "SELECT * FROM prosthetic WHERE prosthetic_id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1,prostheticID );
			ResultSet rs = prep.executeQuery(); //we only get one answer
			rs.next();
			int id = rs.getInt("id");
			String material = rs.getString("material");
			String pros_type = rs.getString("type");
			String dimensions = rs.getString("dimension");
			float price = rs.getFloat("price");
			String failures = rs.getString("failures");
			newProsthetic = new Prosthetic (id, pros_type, material,  price,  dimensions,failures);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return newProsthetic;
	}

	// search prosthetic por type? y el doctor no busca la prosthetic? y el patient?
	//falta la parte de poder modificar date of fitting
	@Override
	public List<Prosthetic> searchBytype(String type) {
		List<Prosthetic> listProsthetics = new ArrayList<Prosthetic>();
		try {
			String sql = "SELECT * FROM prosthetic WHERE type LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);

			prep.setString(1, "%" + type + "%");
			ResultSet rs = prep.executeQuery(); // gives the result for the query
			// For each result...
			while (rs.next()) {
				int id = rs.getInt("id");
				String material = rs.getString("material");
				String pros_type = rs.getString("type");
				String dimensions = rs.getString("dimension");
				float price = rs.getFloat("price");

				// Create a new prosthetic for the list
				Prosthetic newPros = new Prosthetic(id, pros_type, material, price, dimensions);
				// Add it to the list
				listProsthetics.add(newPros);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listProsthetics;

	}


}
