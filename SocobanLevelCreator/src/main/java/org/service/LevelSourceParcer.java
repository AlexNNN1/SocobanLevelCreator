package org.service;

import java.util.Arrays;

import org.model.ArrayElement;
import org.model.ElementType;
import org.model.LevelS;

public class LevelSourceParcer {

	public LevelDestinationList parce(SourceLevelsList source) {
		LevelDestinationList result = new LevelDestinationList();

		for (LevelS ls : source.getLevels()) {
			ArrayElement[][] elements = new ArrayElement[ls.getWidth()][ls
					.getHeight()];
			for (int i = 0; i < ls.getRows().size(); i++) {
				String row = ls.getRows().get(i);
				for (int j = 0; j < row.length(); j++) {
					elements[j][i] = new ArrayElement();
					elements[j][i].setType(ElementType.Empty);
					String value = Character.toString(row.charAt(j));
					if (value.equals("#")) {
						elements[j][i].setType(ElementType.Wall);
					} else if (value.equals(" ")) {
						elements[j][i].setType(ElementType.Tile);
					}
				}
			}
			displaySchematicArray(elements);
		}

		return result;
	}

	private void displaySchematicArray(ArrayElement[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				ArrayElement item = array[i][j];
				if (item == null) 
					System.out.print("N");
				 else if (item.getType() == ElementType.Wall) 
					System.out.print("W");
				 else if (item.getType() == ElementType.Tile) 
					System.out.print("_");
				 else if (item.getType() == ElementType.Empty) 
						System.out.print("E");
				 else if (item.getType() == ElementType.Border) 
						System.out.print("B");
			}
			System.out.println("");
		}
		System.out.println("");
		System.out.println("");
	}

}
