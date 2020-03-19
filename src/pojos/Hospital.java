package pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Hospital implements Serializable{

	private static final long serialVersionUID = 4942876712733558307L;
	
	private Integer id;
	private String name;
	private String addres;
	private Integer patient_id;
	private List<Doctor> doctors;
	
	public Hospital() {
		super();
		this.doctors = new ArrayList<Doctor>();
	}

	public Hospital(String name, String addres) {
		super();
		this.name = name;
		this.addres = addres;
		this.doctors = new ArrayList<Doctor>();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hospital other = (Hospital) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddres() {
		return addres;
	}

	public void setAddres(String addres) {
		this.addres = addres;
	}

	public Integer getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(Integer patient_id) {
		this.patient_id = patient_id;
	}

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}
	
	// create additional method to add to list
	
	// create additional method to remove from list
	
	
	
	
	
	
	

}
