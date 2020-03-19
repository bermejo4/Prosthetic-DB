package pojos;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
public class Doctor implements Serializable{

	private static final long serialVersionUID = 3556611086750634776L;
	
	private Integer id;
	private String name;
	private String lastname;
	private Float telephone;
	private Boolean gender;
	private String department;
	private Integer hospital_id;
	private List<Patient> patients;
	
	public Doctor() {
		super();
		this.patients= new ArrayList<Patient>();
		
	}

	public Doctor(String name, String lastname, Float telephone, Boolean gender, String department,
			Integer hospital_id) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.telephone = telephone;
		this.gender = gender;
		this.department = department;
		this.hospital_id = hospital_id;
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
		Doctor other = (Doctor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", lastname=" + lastname + ", telephone=" + telephone
				+ ", gender=" + gender + ", department=" + department + ", hospital_id=" + hospital_id + "]";
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

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Float getTelephone() {
		return telephone;
	}

	public void setTelephone(Float telephone) {
		this.telephone = telephone;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getHospital_id() {
		return hospital_id;
	}

	public void setHospital_id(Integer hospital_id) {
		this.hospital_id = hospital_id;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

// create additional method to add to a list

	
// create additional method to remove from a list




	


}
