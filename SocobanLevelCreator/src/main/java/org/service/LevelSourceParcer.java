package org.service;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.model.ArrayElement;
import org.model.ElementType;
import org.model.LevelS;

public class LevelSourceParcer {

	public LevelDestinationList parce(SourceLevelsList source) {
		LevelDestinationList result = new LevelDestinationList();
		for (LevelS ls : source.getLevels()) {
			ArrayElement[][] elements = new ArrayElement[ls.getHeight()][ls.getWidth()];
			for (int i = 0; i < ls.getRows().size(); i++) {  // i = это Y или rows
				String row = ls.getRows().get(i);
				for (int j = 0; j < row.length(); j++) { // j это х или cols
					elements[i][j] = new ArrayElement();
					elements[i][j].setType(ElementType.Empty);
					elements[i][j].setX(j);
					elements[i][j].setY(i);
					String value = Character.toString(row.charAt(j));
					if (value.equals("#")) {
						elements[i][j].setType(ElementType.Wall);
					} else if (value.equals(" ") || value.equals(".") ||
							value.equals("@") || value.equals("$")) {
						elements[i][j].setType(ElementType.Unknown);
					}
				}
			}
			clearBorders(elements);
			displaySchematicArray(elements);
		}

		return result;
	}
	
	private void clearBorders(ArrayElement[][] array) {
		Set<ArrayElement> empties = new HashSet<ArrayElement>();
		checkTopLine(empties, array);
		checkBottomLine(empties, array);
		
		checkLeftCol(empties, array);
		//checkRightCol(empties, array);
		
		markEmpties(empties);
	}
	
	private void markEmpties(Set<ArrayElement> empties) {
		for (ArrayElement item : empties) {
			item.setType(ElementType.Marked);
		}
	}

	private void checkTopLine(Set<ArrayElement> empties, ArrayElement[][] array) {
		for (int i = 0; i < array[0].length; i++) {
			ArrayElement item = array[0][i]; 
			if (item != null && item.getType() == ElementType.Unknown)
				checkElementForEmpties(empties, item, array, 1, 1);
		}
	}
	
	private void checkBottomLine(Set<ArrayElement> empties, ArrayElement[][] array) {
		for (int i = 0; i < array[0].length; i++) {
			ArrayElement item = array[array.length - 1][i]; 
			if (item != null && item.getType() == ElementType.Unknown)
				checkElementForEmpties(empties, item, array, 1, -1);
		}
	}
	
	private void checkLeftCol(Set<ArrayElement> empties, ArrayElement[][] array) {
		for (int i = 0; i < array.length; i++) {
			ArrayElement item = array[i][0]; 
			if (item != null && item.getType() == ElementType.Unknown)
				checkElementForEmpties(empties, item, array, 1, 1);
		}
	}
	
	//private void checkRightCol(Set<ArrayElement> empties, ArrayElement[][] array) {
	/*	for (int i = 0; i < array.length; i++) {
			ArrayElement item = array[i][array.length -1]; 
			if (item != null && item.getType() == ElementType.Unknown)
				checkElementForEmpties(empties, item, array, -1, 1);
		}*/
	//}

	private void checkElementForEmpties(Set<ArrayElement> empties,
			ArrayElement item, ArrayElement[][] array, int indentX, int indentY) {
			if (!empties.contains(item)) {
				empties.add(item);
				//ArrayElement nearX, ArrayElement nearY, 
				ArrayElement nearX = getNearItem(item.getX() + indentX, item.getY(), array);
				ArrayElement nearY = getNearItem(item.getX(), item.getY()  + indentY, array);	
				if (nearX != null && nearX.getType() == ElementType.Unknown) 
					checkElementForEmpties(empties, nearX, array, indentX, indentY);
				if (nearY != null && nearY.getType() == ElementType.Unknown) 
					checkElementForEmpties(empties, nearY, array, indentX, indentY);
			}
	}

	private ArrayElement getNearItem(int indentX, int indentY, ArrayElement[][] array) {
		ArrayElement result = null;
		if (indentY < array.length && indentX < array[0].length)
			result = array[indentY][indentX];
		return result;
	}
	
	private void displaySchematicArray(ArrayElement[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				ArrayElement item = array[i][j];
				if (item == null) 
					System.out.print("~");
				 else if (item.getType() == ElementType.Wall) 
					System.out.print("W");
				 else if (item.getType() == ElementType.Tile) 
					System.out.print("_");
				 else if (item.getType() == ElementType.Empty) 
						System.out.print("E");
				 else if (item.getType() == ElementType.Border) 
						System.out.print("B");
				 else if (item.getType() == ElementType.Unknown) 
						System.out.print(".");
				 else if (item.getType() == ElementType.Marked) 
						System.out.print("I");
				
			}
			System.out.println("");
		}
		System.out.println("");
		System.out.println("");
	}

}
