package pojos.pojos4Xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import pojos.Hospital;

@XmlRootElement(name = "hospitals")
@XmlAccessorType (XmlAccessType.FIELD)
public class HospitalsListing {
	
	@XmlElement(name = "hospital")
	private List<Hospital> hosptialListWeb=null;

	public List<Hospital> getHosptialListWeb() {
		return hosptialListWeb;
	}

	public void setHosptialListWeb(List<Hospital> hosptialListWeb) {
		this.hosptialListWeb = hosptialListWeb;
	}
	
}
