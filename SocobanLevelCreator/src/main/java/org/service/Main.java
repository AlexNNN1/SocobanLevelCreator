package org.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.model.LevelDestination;


public class Main {


	public void convert()  {
		try {
			//File file = new File("E:\\Install\\levelssource.txt");
		//	URL file = File.class.getResource("source.txt");
			InputStream res = this.getClass().getResourceAsStream("/source.txt");
			JAXBContext context = JAXBContext.newInstance(SourceLevelsList.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			SourceLevelsList list = (SourceLevelsList)unmarshaller.unmarshal(res);
		
			System.out.println(list.getLevels().size());
			
			LevelSourceParcer parcer = new LevelSourceParcer();
			LevelDestinationList result = parcer.parce(list);
			
			LevelDestinationList test = new LevelDestinationList();
			LevelDestination it = new LevelDestination();
			test.getItems().add(it);
			
			JAXBContext context2 = JAXBContext.newInstance(LevelDestinationList.class);
			Marshaller marshaller = context2.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(test, new FileOutputStream("d:\\results.xml"));
			
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Main m = new Main();
		m.convert();
	}

}
