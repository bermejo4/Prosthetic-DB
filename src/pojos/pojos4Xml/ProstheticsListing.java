package pojos.pojos4Xml;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import pojos.Prosthetic;
 
@XmlRootElement(name = "prosthetics")
@XmlAccessorType (XmlAccessType.FIELD)
public class ProstheticsListing {
    @XmlElement(name = "prosthetic")
    private List<Prosthetic> prosListWeb=null;

	public List<Prosthetic> getProsListWeb() {
		return prosListWeb;
	}

	public void setProsListWeb(List<Prosthetic> prosListWeb) {
		this.prosListWeb = prosListWeb;
	}
 
    
}
