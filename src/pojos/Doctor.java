package pojos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import java.util.ArrayList;

@Entity
@Table(name = "doctor")
public class Doctor implements Serializable{

	private static final long serialVersionUID = 3556611086750634776L;
	
	@Id
	@GeneratedValue(generator="doctor")
	@TableGenerator(name="doctor", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq", pkColumnValue="doctor")
	private Integer id;
	private String name;
	private String lastname;
	private String telephone;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hospital_id")
	private Hospital hospital_id;
	private User doctorUser;
	
	
	public Doctor() {
		super();
		
	}
	
	public Doctor(String name, String lastname, String telephone, Integer hospital_id) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.telephone = telephone;
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
				+ ", hospital_id=" + hospital_id + "]";
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

	public Integer getHospital_id() {
		return hospital_id;
	}

	public void setHospital_id(Integer hospital_id) {
		this.hospital_id = hospital_id;
	}

	public User getDoctorUser() {
		return doctorUser;
	}

	public void setDoctorUser(User doctorUser) {
		this.doctorUser = doctorUser;
	}	


}
