package pojos;

import java.io.Serializable;

public class Biomedical_Eng implements Serializable {

	private static final long serialVersionUID = -6234822159013375318L;
	
	private Integer id;
	private String name;
	private String lastname;
	private String telephone;
	private Boolean gender;
	private String speciality;
	private String worklocation;
	
	public Biomedical_Eng() {
		super ();
		
	}
	
	public Biomedical_Eng( String name, String lastname, String telephone,
			Boolean gender, String specialty, String worklocation) {
		super();
		
		this.name = name;
		this.lastname = lastname;
		this.telephone = telephone;
		this.gender = gender;
		this.speciality = specialty;
		this.worklocation = worklocation;
			
		
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
		Biomedical_Eng other = (Biomedical_Eng) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Biomedical_Eng [id=" + id + ", name=" + name + ", lastname=" + lastname + ", telephone=" + telephone
				+ ", gender=" + gender + ", speciality=" + speciality + ", worklocation=" + worklocation + "]";
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

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getWorklocation() {
		return worklocation;
	}

	public void setWorklocation(String worklocation) {
		this.worklocation = worklocation;
	}
	
	
	
}

