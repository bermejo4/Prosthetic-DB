package pojos;
import java.io.Serializable;
import javax.xml.bind.annotation.*;
import java.sql.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD) //Here we indicates that the attributes need to be annotated.
@XmlType(propOrder = {"name", "lastname","telephone","gender", "problem", "address"})
public class Patient implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8467155529721810193L;
	@XmlAttribute
	private Integer id;
	@XmlElement
	private String name;
	@XmlElement
	private String lastname;
	@XmlElement
	private String telephone;
	@XmlTransient
	private Date dob;
	@XmlTransient
	private Date dof;
	@XmlElement
	private String gender;
	@XmlElement
	private String problem;
	@XmlElement
	private String address;
	@XmlTransient
	private Doctor doctor;
	
	
	public Patient() {
		super();
	}


	public Patient(Integer id) {
		super();
		this.id = id;
	}


	public Patient(Integer id, String name, String lastname, String telephone, Date dob, Date dof, String gender, String problem,
			String addres, Doctor doctor) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.telephone = telephone;
		this.dob=dob;
		this.dof = dof;
		this.gender = gender;
		this.problem = problem;
		this.address = addres;
		this.doctor=doctor;
	}
	
	public Patient(String name, String lastname, String telephone, Date dob, Date dof, String gender, String problem,
			String address, Doctor doctor) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.telephone = telephone;
		this.dob = dob;
		this.dof = dof;
		this.gender = gender;
		this.problem = problem;
		this.address = address;
		this.doctor = doctor;
	}


	public Patient(Integer id, String name, String lastname, String telephone, Date dob, String gender, String problem,
			String address, Doctor doctor) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.telephone = telephone;
		this.dob = dob;
		this.gender = gender;
		this.problem = problem;
		this.address = address;
		this.doctor = doctor;
	}

	

	public Patient(String name, String lastname, String telephone) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.telephone = telephone;
	}
	public Patient(int id, String name, String lastname) {
		super();
		this.id=id;
		this.name = name;
		this.lastname = lastname;
	}

	public Patient(String name, String lastname, String telephone, Date dob, String gender, String problem,
			String address, Doctor doctor) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.telephone = telephone;
		this.dob = dob;
		this.gender = gender;
		this.problem = problem;
		this.address = address;
		this.doctor = doctor;
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
		Patient other = (Patient) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	public Patient(Integer id, String name, String lastname, String telephone) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.telephone = telephone;
	}


	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", lastname=" + lastname + ", telephone=" + telephone + ", dob="
				+ dob + ", dof=" + dof + ", gender=" + gender + ", problem=" + problem + ", addres=" + address
				+ ", doctor_id=" + doctor.getId() + "]";
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
	
	

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDof() {
		return dof;
	}


	public void setDof(Date dof) {
		this.dof = dof;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getProblem() {
		return problem;
	}


	public void setProblem(String problem) {
		this.problem = problem;
	}


	public String getAddres() {
		return address;
	}


	public void setAddres(String addres) {
		this.address = addres;
	}


	public String getAddress() {
		return address;
	}


	public Doctor getDoctor() {
		return doctor;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	
}
