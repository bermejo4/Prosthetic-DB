package pojos;

import java.io.Serializable;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "hospital")
@XmlType(propOrder = {"address","telephone", "doctors"}) //elements will appear like this on the XML
public class Hospital implements Serializable{

	private static final long serialVersionUID = 4942876712733558307L;
	
	@XmlTransient
	private Integer id;
	@XmlAttribute 
	private String name;
	@XmlElement
	private String address;
	@XmlTransient
	private Patient patient;
	@XmlElement
	private String telephone;
	@XmlElement(name = "doctors")
	private List<Doctor> doctors;
	//WILL CREATE A DOCTORS ELEMENT AND INSIDE THE NAME OF THE DOCTORS WORKING ON THAT HOSPITAL
	
	
	public Hospital() {
		super();
		this.doctors = new ArrayList<Doctor>();
	}
	
	public Hospital(Integer id) {
		super();
		this.id = id;
	}

	public Hospital(Integer id, String name, List<Doctor> doctors) {
		super();
		this.id = id;
		this.name = name;
		this.doctors = doctors;
	}



	public Hospital(int id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.doctors = new ArrayList<Doctor>();
	}
	
	public Hospital(Integer id, String name, String address, String telephone) {
		super();
		this.id=id;
		this.name = name;
		this.address = address;
		this.telephone=telephone;
		this.doctors = new ArrayList<Doctor>();
	}
	
	

	public Hospital(String name, String address, String telephone) {
		super();
		this.name = name;
		this.address = address;
		this.telephone = telephone;
	}
	
	
	public Hospital(Integer id,String name, String address, String telephone, Patient pat) {
		super();
		this.id=id;
		this.name = name;
		this.address = address;
		this.telephone = telephone;
		this.patient=pat;
	}
	

	public Hospital(Integer id, String name, String address, Patient patient, String telephone,
			List<Doctor> doctors) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.patient = patient;
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
		return "Hospital [id= " + id + ", name=" + name + ", address=" + address + ", patient_id=" + patient.getId() + ", telephone="
				+ telephone + "]";
	}
	
	public String toStringXML() {
		return "Hospital [id= " + id + ", name=" + name + ", address=" + address + ", telephone="
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}
	
	

	
	
	
	
	
	

}
