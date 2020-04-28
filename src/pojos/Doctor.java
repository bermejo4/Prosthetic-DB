package pojos;

import java.io.Serializable;
//import java.util.List;



//import java.util.ArrayList;


public class Doctor implements Serializable{

	private static final long serialVersionUID = 3556611086750634776L;
	
	private Integer id;
	private String name;
	private String lastname;
	private String telephone;
	private Hospital hospital;
	private User doctorUser;
	
	
	public Doctor() {
		super();
		
	}
	
	
	
	public Doctor(Integer id) {
		super();
		this.id = id;
	}



	public Doctor(String name, String lastname, String telephone, Hospital hospital) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.telephone = telephone;
		this.hospital = hospital;
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
				+ ", hospital_id=" + hospital.getId()+ "]";
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public User getDoctorUser() {
		return doctorUser;
	}

	public void setDoctorUser(User doctorUser) {
		this.doctorUser = doctorUser;
	}	


}
