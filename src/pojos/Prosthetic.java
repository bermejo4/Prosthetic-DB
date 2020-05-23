package pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD) // Here we indicates that the attributes need to be annotated.
@XmlRootElement(name = "prosthetic")
@XmlType(propOrder = { "material", "dimensions", "price", "failures", "available", "patient", "biomeds" }) // Order in which the
																								// elements appear
public class Prosthetic implements Serializable {

	// <element attribute="value">text or other elements</element>
	/**
	 * 
	 */
	private static final long serialVersionUID = -2842189245403761539L;
	@XmlTransient   // If we put that we indicate xml to ignore the line of the id. In some cases we
					// prefer to use @XmlAttribute (but it is when we dont want to insert more in
					// our db)
	private Integer id;
	@XmlAttribute
	private String type;
	@XmlElement
	private String material;
	@XmlElement
	private Float price;
	@XmlElement
	private String dimensions;
	@XmlElement
	private String failures;
	@XmlTransient
	private Integer numberFailures;
	@XmlElement // How does xml recognize boolean? You tell as only Integer, Float and text
	private boolean available;
	@XmlElement
	private Patient patient;
	@XmlTransient
	private Hospital hospital;
	// The annotation that I have done will show the biomeds like that:
	// <prosthetic>
	// <biomeds>
	// <biomed></biomed>
	// <biomed></<biomed>
	// <biomeds>
	// </prosthetic>
	@XmlElement(name = "biomed")
	@XmlElementWrapper(name = "biomeds")
	private List<Biomedical_Eng> biomeds; // for the many to many relationship




	// Constructor with all variables
	public Prosthetic(Integer id, String type, String material, Float price, String dimensions, String failures,
			Integer numberFailures, boolean available, Patient patient, Hospital hospital,
			List<Biomedical_Eng> biomeds) {
		super();
		this.id = id;
		this.type = type;
		this.material = material;
		this.price = price;
		this.dimensions = dimensions;
		this.failures = failures;
		this.numberFailures = numberFailures;
		this.available = available;
		this.patient = patient;
		this.hospital = hospital;
		this.biomeds = biomeds;
	}

	public Prosthetic(Integer id, String type, String material, Float price, String dimensions, String failures,
			Integer numberFailures, boolean available, Patient patient, Hospital hospital) {
		super();
		this.id = id;
		this.type = type;
		this.material = material;
		this.price = price;
		this.dimensions = dimensions;
		this.failures = failures;
		this.numberFailures = numberFailures;
		this.available = available;
		this.patient = patient;
		this.hospital = hospital;
		this.biomeds = new ArrayList<Biomedical_Eng>();
	}

	public Prosthetic(Integer id, String type, String material, Float price, String dimensions, String failures,
			boolean available) {
		super();
		this.id = id;
		this.type = type;
		this.material = material;
		this.price = price;
		this.dimensions = dimensions;
		this.failures = failures;
		this.available = available;
		this.biomeds = new ArrayList<Biomedical_Eng>();

	}
	public Prosthetic(Integer id, String type, String material, Float price, String dimensions, String failures,
			 boolean available, Patient patient) {
		super();
		this.id = id;
		this.type = type;
		this.material = material;
		this.price = price;
		this.dimensions = dimensions;
		this.failures = failures;
		this.available = available;
		this.patient = patient;
		this.biomeds = new ArrayList<Biomedical_Eng>();
	}

	public Prosthetic(String type, String material, Float price, String dimensions, String failures,
			boolean available) {
		super();
		this.type = type;
		this.material = material;
		this.price = price;
		this.dimensions = dimensions;
		this.failures = failures;
		this.available = available;
		this.biomeds = new ArrayList<Biomedical_Eng>();
	}

//created another constructor to insert prosthetic but without id, failures etc
	public Prosthetic(String type, String material, Float price, String dimensions) {
		super();
		this.type = type;
		this.material = material;
		this.price = price;
		this.dimensions = dimensions;
		this.biomeds = new ArrayList<Biomedical_Eng>();
	}

	// Empty constructor
	public Prosthetic() {
		super();
		biomeds = new ArrayList<Biomedical_Eng>();
	}

	// Getters and Setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getDimensions() {
		return dimensions;
	}

	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}

	public String getFailures() {
		return failures;
	}

	public void setFailures(String failures) {
		this.failures = failures;
	}

	public Integer getNumberFailures() {
		return numberFailures;
	}

	public void setNumberFailures(Integer numberFailures) {
		this.numberFailures = numberFailures;
	}

	@Override
	public String toString() {
		return "Prosthetic [id=" + id + ", type=" + type + ", material=" + material + ", price=" + price
				+ ", dimensions=" + dimensions + ", failures=" + failures + ", numberFailures=" + numberFailures
				+ ", available=" + available + ", hospital_id=" + hospital.getId() + "]";
	}

	public String toStringProstheticXML() {
		return "Prosthetic [id=" + id + ", type=" + type + ", material=" + material + ", price=" + price
				+ ", dimensions=" + dimensions + ", failures=" + failures + ", numberFailures=" + numberFailures
				+ ", available=" + available + "]";
	}

	// HashCode and Equals methods only with the Id
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
		Prosthetic other = (Prosthetic) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public boolean getAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Patient getPatient() {
		return patient;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public List<Biomedical_Eng> getBiomeds() {
		return biomeds;
	}

	public void setBiomeds(List<Biomedical_Eng> biomeds) {
		this.biomeds = biomeds;
	}

}
