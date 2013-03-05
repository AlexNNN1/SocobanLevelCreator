package org.service;

import java.util.HashSet;
import java.util.Set;
import org.model.ArrayElement;
import org.model.ElementType;
import org.model.LevelS;

public class LevelSourceParcer {

	public LevelDestinationList parce(SourceLevelsList source) {
		LevelDestinationList result = new LevelDestinationList();
		for (LevelS ls : source.getLevels()) {
			ArrayElement[][] elements = new ArrayElement[ls.getHeight()][ls
					.getWidth()];
			for (int i = 0; i < ls.getRows().size(); i++) { // i = это Y или
															// rows
				String row = ls.getRows().get(i);
				for (int j = 0; j < row.length(); j++) { // j это х или cols
					elements[i][j] = new ArrayElement();
					elements[i][j].setType(ElementType.Empty);
					elements[i][j].setX(j);
					elements[i][j].setY(i);
					String value = Character.toString(row.charAt(j));
					if (value.equals("#")) {
						elements[i][j].setType(ElementType.Wall);
					} else if (value.equals(" ")  /* || value.equals("@")|| value.equals("$") || value.equals(".")*/) 
						elements[i][j].setType(ElementType.Unknown);
					else if (value.equals("$"))
						elements[i][j].setType(ElementType.Box);
					else if (value.equals("."))
						elements[i][j].setType(ElementType.Dock);
					else if (value.equals("*"))
						elements[i][j].setType(ElementType.BoxOnDock);
					else if (value.equals("@"))
						elements[i][j].setType(ElementType.Worker);
					else if (value.equals("+"))
						elements[i][j].setType(ElementType.WorkerOnDock);
				}
			}
			markNullsAsUnknow(elements);
			clearBorders(elements);
			setBorders(elements);
			displaySchematicArray(elements);
		}

		return result;
	}
	
	private void clearBorders(ArrayElement[][] array) {
		Set<ArrayElement> empties = new HashSet<ArrayElement>();
		checkTopLine(empties, array);
		checkBottomLine(empties, array);
		
		checkLeftCol(empties, array);
		checkRightCol(empties, array);
		markEmpties(empties);
		
	}
	
	private void markNullsAsUnknow(ArrayElement[][] array) {
		for (int i = 0; i < array.length; i++)
			for (int j = 0; j < array[i].length; j++) {
				if (array[i][j] == null) {
					array[i][j] = new ArrayElement();
					array[i][j].setType(ElementType.Unknown);
					array[i][j].setX(j);
					array[i][j].setY(i);
				}
			}
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
	
	private void checkRightCol(Set<ArrayElement> empties, ArrayElement[][] array) {
		for (int i = 0; i < array.length; i++) {
			ArrayElement item = array[i][array[0].length - 1]; 
			if (item != null && item.getType() == ElementType.Unknown)
				checkElementForEmpties(empties, item, array, -1, 1);
		}
	}

	private void checkElementForEmpties(Set<ArrayElement> empties,
			ArrayElement item, ArrayElement[][] array, int indentX, int indentY) {
		if (!empties.contains(item)) {
			empties.add(item);
			// ArrayElement nearX, ArrayElement nearY,
			ArrayElement nearXleft = getNearItem(item.getX() - 1, item.getY(), array);
			ArrayElement nearYtop = getNearItem(item.getX(), item.getY() - 1, array);
			ArrayElement nearXright = getNearItem(item.getX() + 1, item.getY(), array);
			ArrayElement nearYbottom = getNearItem(item.getX(), item.getY() + 1, array);
			if (nearXleft != null && nearXleft.getType() == ElementType.Unknown)
				checkElementForEmpties(empties, nearXleft, array, indentX, indentY);
			if (nearYtop != null && nearYtop.getType() == ElementType.Unknown)
				checkElementForEmpties(empties, nearYtop, array, indentX,indentY);
			if (nearXright != null	&& nearXright.getType() == ElementType.Unknown)
				checkElementForEmpties(empties, nearXright, array, indentX, indentY);
			if (nearYbottom != null && nearYbottom.getType() == ElementType.Unknown)
				checkElementForEmpties(empties, nearYbottom, array, indentX, indentY);
		}
	}

	private ArrayElement getNearItem(int indentX, int indentY, ArrayElement[][] array) {
		ArrayElement result = null;
		if (indentY < array.length && indentX < array[0].length && indentY >= 0 && indentX >= 0)
			result = array[indentY][indentX];
		return result;
	}
	
	private void setBorders(ArrayElement[][] array) {
		for (int i = 0; i < array.length; i++)
			for (int j = 0; j < array[i].length; j++) {
				ArrayElement item = array[i][j];
				if (item.getType() == ElementType.Wall) {
					ArrayElement left = getNearItem(item.getX() - 1,
							item.getY(), array);
					ArrayElement right = getNearItem(item.getX() + 1,
							item.getY(), array);
					ArrayElement top = getNearItem(item.getX(),
							item.getY() - 1, array);
					ArrayElement bottom = getNearItem(item.getX(),
							item.getY() + 1, array);
					
					
					ArrayElement topleft = getNearItem(item.getX() - 1,
							item.getY() - 1, array);
					ArrayElement topright = getNearItem(item.getX() + 1,
							item.getY() - 1, array);
					ArrayElement bottomleft = getNearItem(item.getX() - 1,
							item.getY() + 1, array);
					ArrayElement bottomRight = getNearItem(item.getX() + 1,
							item.getY() + 1, array);
					
					if (isItemEmpty(left) || isItemEmpty(right) ||
							isItemEmpty(top) || isItemEmpty(bottom) ||
							isItemEmpty(topleft) || isItemEmpty(topright) ||
							isItemEmpty(bottomleft) || isItemEmpty(bottomRight)) 
						item.setType(ElementType.Border);
				}
			}
	}
	
	private Boolean isItemEmpty(ArrayElement item) {
		Boolean result = false;
		if (item != null) {
			if (item.getType() == ElementType.Marked)
				result = true;
		} else
			result = true;
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
					System.out.print(" ");
				else if (item.getType() == ElementType.Empty)
					System.out.print("E");
				else if (item.getType() == ElementType.Border)
					System.out.print("B");
				else if (item.getType() == ElementType.Unknown)
					System.out.print(".");
				else if (item.getType() == ElementType.Marked)
					System.out.print(" ");
				else if (item.getType() == ElementType.Worker)
					System.out.print("R");
				else if (item.getType() == ElementType.WorkerOnDock)
					System.out.print("U");
				else if (item.getType() == ElementType.Box)
					System.out.print("O");
				else if (item.getType() == ElementType.BoxOnDock)
					System.out.print("Q");
				else if (item.getType() == ElementType.Dock)
					System.out.print("D");
				

			}
			System.out.println("");
		}
		System.out.println("");
		System.out.println("");
	}

}
