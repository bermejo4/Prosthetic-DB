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
	private String telephone;
	private List<Doctor> doctors;
	
	public Hospital() {
		super();
		this.doctors = new ArrayList<Doctor>();
	}
	
	

	public Hospital(Integer id, String name, List<Doctor> doctors) {
		super();
		this.id = id;
		this.name = name;
		this.doctors = doctors;
	}



	public Hospital(String name, String addres) {
		super();
		this.name = name;
		this.addres = addres;
		this.doctors = new ArrayList<Doctor>();
	}
	

	public Hospital(Integer id, String name, String addres, Integer patient_id, String telephone,
			List<Doctor> doctors) {
		super();
		this.id = id;
		this.name = name;
		this.addres = addres;
		this.patient_id = patient_id;
		this.telephone = telephone;
		this.doctors = doctors;
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
	

	@Override
	public String toString() {
		return "Hospital [name=" + name + ", addres=" + addres + ", patient_id=" + patient_id + ", telephone="
				+ telephone + "]";
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

	
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
