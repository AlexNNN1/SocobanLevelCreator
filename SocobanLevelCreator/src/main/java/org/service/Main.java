package org.service;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


public class Main {


	public void convert() {
		try {
			//File file = new File("E:\\Install\\levelssource.txt");
		//	URL file = File.class.getResource("source.txt");
			InputStream res = this.getClass().getResourceAsStream("/source.txt");
			JAXBContext context = JAXBContext.newInstance(SourceLevelsList.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			SourceLevelsList list = (SourceLevelsList)unmarshaller.unmarshal(res);
		
			System.out.println(list.getLevels().size());
			
			LevelSourceParcer parcer = new LevelSourceParcer();
			/*LevelDestinationList result = */parcer.parce(list);
		//	LevelPrinter printer = new LevelPrinter();
			
			/*for (LevelDestination item : result.) {
				System.out.println(level.getHeight());
				for (String string : level.getRows()) {
					System.out.println(string);
				}
				
			}*/
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Main m = new Main();
		m.convert();
	}

}
