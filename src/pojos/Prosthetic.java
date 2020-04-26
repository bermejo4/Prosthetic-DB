package pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Prosthetic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2842189245403761539L;

	private Integer id;
	private String type;
	private String material;
	private Float price;
	private String dimensions;
	private String failures;
	private Integer numberFailures;
	private boolean available;
	private Integer patient_id;
	private Integer hospital_id;
	private List<Biomedical_Eng> biomeds; //for the many to many relationship

	


	
	public Prosthetic(Integer id, String type) {
		super();
		this.id = id;
		this.type = type;
		this.biomeds = new ArrayList<Biomedical_Eng>();
		
		
	}

	
	
	// Constructor with all variables
	public Prosthetic(Integer id, String type, String material, Float price, String dimensions, String failures,
			Integer numberFailures, boolean available, Integer patient_id, Integer hospital_id,
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
		this.patient_id = patient_id;
		this.hospital_id = hospital_id;
		this.biomeds = biomeds;
	} 
	
	

	public Prosthetic(Integer id, String type, String material, Float price, String dimensions, String failures,
			Integer numberFailures, boolean available, Integer patient_id, Integer hospital_id) {
		super();
		this.id = id;
		this.type = type;
		this.material = material;
		this.price = price;
		this.dimensions = dimensions;
		this.failures = failures;
		this.numberFailures = numberFailures;
		this.available = available;
		this.patient_id = patient_id;
		this.hospital_id = hospital_id;
		this.biomeds = new ArrayList<Biomedical_Eng>();
	}



	public Prosthetic(Integer id, String type, String material, Float price, String dimensions, String failures, boolean available ) {
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
	



	public Prosthetic( String type, String material, Float price, String dimensions, String failures, boolean available ) {
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


	public String getisAvailable() {
		String state;
		if(available == true) {
		return state = "Available";
		
		} else {
			return state = "NO longer available";
		}
	}

	public boolean getAvailable() {
		return available;
	}


	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Integer getPatient_id() {
		return patient_id;
	}

	public Integer getHospital_id() {
		return hospital_id;
	}

	public void setPatient_id(Integer patient_id) {
		this.patient_id = patient_id;
	}

	public void setHospital_id(Integer hospital_id) {
		this.hospital_id = hospital_id;
	}



	public List<Biomedical_Eng> getBiomeds() {
		return biomeds;
	}



	public void setBiomeds(List<Biomedical_Eng> biomeds) {
		this.biomeds = biomeds;
	}
	

}
