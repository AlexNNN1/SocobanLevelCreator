package org.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public class Main {

	public void convert() {
		try {
			// File file = new File("E:\\Install\\levelssource.txt");
			write(read());
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private LevelDestinationList read() throws JAXBException {
		InputStream res = this.getClass().getResourceAsStream("/source.txt");
		JAXBContext context = JAXBContext.newInstance(SourceLevelsList.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		SourceLevelsList list = (SourceLevelsList) unmarshaller.unmarshal(res);
		System.out.println(list.getLevels().size());
		LevelSourceParcer parcer = new LevelSourceParcer();
		return parcer.parce(list);
	}

	private void write(LevelDestinationList source) throws JAXBException,
			FileNotFoundException {
		JAXBContext context = JAXBContext
				.newInstance(LevelDestinationList.class);
		Marshaller marshaller = context.createMarshaller();
	    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); 
		marshaller.marshal(source, new FileOutputStream("d:\\results.xml"));
	}

	public static void main(String[] args) {
		Main m = new Main();
		m.convert();
	}

}
