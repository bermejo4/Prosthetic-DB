package pojos.pojos4Xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import pojos.Biomedical_Eng;

@XmlRootElement(name = "Biomedicals")
@XmlAccessorType(XmlAccessType.FIELD)
public class BiomedicalsListing {

		@XmlElement(name = "Biomedical")
		private List<Biomedical_Eng> BiomedicalListWeb = null;

		public List<Biomedical_Eng> getBiomedicalListWeb() {
			return BiomedicalListWeb;
		}

		public void setBiomedicalListWeb(List<Biomedical_Eng> biomedicalListWeb) {
			BiomedicalListWeb = biomedicalListWeb;
		}

}