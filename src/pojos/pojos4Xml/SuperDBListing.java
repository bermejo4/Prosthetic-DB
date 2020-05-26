package pojos.pojos4Xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DataBase")
@XmlAccessorType (XmlAccessType.FIELD)
public class SuperDBListing {
	
	@XmlElement
	HospitalsListing hospitals; 
	@XmlElement
	BiomedicalsListing biomedicals;
	@XmlElement
	ProstheticsListing prosthetics;
 
public SuperDBListing(HospitalsListing hospitals, BiomedicalsListing biomedicals, ProstheticsListing prosthetics) {
		super();
		this.hospitals = hospitals;
		this.biomedicals = biomedicals;
		this.prosthetics = prosthetics;
	}

public SuperDBListing() {
	super();
}

public BiomedicalsListing getBiomedicals() {
	return biomedicals;
}
public HospitalsListing getHospitals() {
	return hospitals;
}
public ProstheticsListing getProsthetics() {
	return prosthetics;
}
public void setBiomedicals(BiomedicalsListing biomedicals) {
	this.biomedicals = biomedicals;
}
public void setHospitals(HospitalsListing hospitals) {
	this.hospitals = hospitals;
}
public void setProsthetics(ProstheticsListing prosthetics) {
	this.prosthetics = prosthetics;
}
 
 
}
