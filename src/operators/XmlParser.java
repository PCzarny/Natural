package operators;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import xml_models.CesAna;

public class XmlParser {
	public static CesAna parse(){
		CesAna cesAna = null;
		try {
			FileManager.removeSecondLine();
			File file = new File("TaKIPI/out.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(CesAna.class);
			
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			cesAna = (CesAna) jaxbUnmarshaller.unmarshal(file);
		  } catch (JAXBException e) {
			e.printStackTrace();
		  }
		return cesAna;
	}
}
