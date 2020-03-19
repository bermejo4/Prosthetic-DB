package pojos;

import java.io.Serializable;

public class Prosthetic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2842189245403761539L;

	private Integer id;
	private String type;
	private String material;
	private Float price;
	private Object dimensions;
	private Boolean failures;
	private Integer numberFailures;

	// Constructor with all variables
	public Prosthetic(Integer id, String type, String material, Float price, Object dimensions, Boolean failures,
			Integer numberFailures) {
		super();
		this.id = id;
		this.type = type;
		this.material = material;
		this.price = price;
		this.dimensions = dimensions;
		this.failures = failures;
		this.numberFailures = numberFailures;
	}

	// Empty constructor
	public Prosthetic() {
		super();
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

	public Object getDimensions() {
		return dimensions;
	}

	public void setDimensions(Object dimensions) {
		this.dimensions = dimensions;
	}

	public Boolean getFailures() {
		return failures;
	}

	public void setFailures(Boolean failures) {
		this.failures = failures;
	}

	public Integer getNumberFailures() {
		return numberFailures;
	}

	public void setNumberFailures(Integer numberFailures) {
		this.numberFailures = numberFailures;
	}

	// ToString with all variables
	@Override
	public String toString() {
		return "prosthetic [id=" + id + ", type=" + type + ", material=" + material + ", price=" + price
				+ ", dimensions=" + dimensions + ", failures=" + failures + ", numberFailures=" + numberFailures + "]";
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

}
