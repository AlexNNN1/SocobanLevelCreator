package org.service;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


public class Main {


	
	public static void main(String[] args) {
		try {
			File file = new File("E:\\Install\\levelssource.txt");
			JAXBContext context = JAXBContext.newInstance(SourceLevelsList.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			SourceLevelsList list = (SourceLevelsList)unmarshaller.unmarshal(file);
		
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

}
