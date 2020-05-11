package pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD) //Here we indicates that the attributes need to be annotated.
@XmlRootElement(name="biomed")
@XmlType(propOrder = {"name", "lastname", "telephone"})
public class Biomedical_Eng implements Serializable {

	private static final long serialVersionUID = -6234822159013375318L;
	
	@XmlAttribute //pq no lo ponen como transient
	private Integer id;
	@XmlElement
	private String name;
	@XmlElement
	private String lastname;
	@XmlElement
	private String telephone;
	
	@XmlTransient
	private List<Prosthetic> prostheticsList;

	


	public Biomedical_Eng() {
		super ();
		this.prostheticsList =  new ArrayList<Prosthetic>();

	}
	
	
	
	public Biomedical_Eng(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.prostheticsList =  new ArrayList<Prosthetic>();
	}



	public Biomedical_Eng( String name, String lastname, String telephone) {
		super();
		
		this.name = name;
		this.lastname = lastname;
		this.telephone = telephone;
		this.prostheticsList =  new ArrayList<Prosthetic>();
					
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
				+ ", prostheticsList =" + prostheticsList + "]";
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
	
	public List<Prosthetic> getProstheticsList() {
		return prostheticsList;
	}

	public void setProstheticsList(List<Prosthetic> prostheticsList) {
		this.prostheticsList = prostheticsList;
	}
	
		
}

