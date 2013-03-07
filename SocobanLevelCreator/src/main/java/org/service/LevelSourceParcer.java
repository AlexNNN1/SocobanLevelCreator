package org.service;

import java.util.HashSet;
import java.util.Set;
import org.model.ArrayElement;
import org.model.ElementType;
import org.model.LevelS;

public class LevelSourceParcer {
	
	private ArrayElement[][] tempElements;
	Set<ArrayElement> empties = new HashSet<ArrayElement>();

	public LevelDestinationList parce(SourceLevelsList source) {
		LevelDestinationList result = new LevelDestinationList();
		for (LevelS ls : source.getLevels()) {
			createEmptyArray(ls);
			parceLevelClassicSchema(ls);
			postProcessingArray();
		}
		return result;
	}
	
	private void postProcessingArray() {
		markNullsAsUnknow();
		clearBorders();
		setBorders();
		convertUnknownsToTiles();
		displaySchematicArray();
	}
	
	private void parceLevelClassicSchema(LevelS ls) {
		for (int i = 0; i < ls.getRows().size(); i++) { // i = это Y или rows
			String row = ls.getRows().get(i);
			for (int j = 0; j < row.length(); j++) { // j это х или cols
				String value = Character.toString(row.charAt(j));
				if (value.equals("#")) 
					tempElements[i][j].setType(ElementType.Wall);
				 else if (value.equals(" "))
					 tempElements[i][j].setType(ElementType.Unknown);
				else if (value.equals("$"))
					tempElements[i][j].setType(ElementType.Box);
				else if (value.equals("."))
					tempElements[i][j].setType(ElementType.Dock);
				else if (value.equals("*"))
					tempElements[i][j].setType(ElementType.BoxOnDock);
				else if (value.equals("@"))
					tempElements[i][j].setType(ElementType.Worker);
				else if (value.equals("+"))
					tempElements[i][j].setType(ElementType.WorkerOnDock);
			}
		}
	}
	
	private void createEmptyArray(LevelS ls) {
		tempElements = new ArrayElement[ls.getHeight()][ls.getWidth()];
		for (int i = 0; i < tempElements.length; i++)
			for (int j = 0; j < tempElements[i].length; j++) {
				tempElements[i][j] = new ArrayElement();
				tempElements[i][j].setType(ElementType.Unknown);
				tempElements[i][j].setX(j);
				tempElements[i][j].setY(i);
			}
	}
	
	private void clearBorders() {
		empties.clear();
		checkTopLine();
		checkBottomLine();
		checkLeftCol();
		checkRightCol();
		markEmpties();
	}
	
	private void markNullsAsUnknow() {
		for (int i = 0; i < tempElements.length; i++)
			for (int j = 0; j < tempElements[i].length; j++) {
				if (tempElements[i][j] == null) {
					tempElements[i][j] = new ArrayElement();
					tempElements[i][j].setType(ElementType.Unknown);
					tempElements[i][j].setX(j);
					tempElements[i][j].setY(i);
				}
			}
	}

	private void markEmpties() {
		for (ArrayElement item : empties) {
			item.setType(ElementType.Marked);
		}
	}
	
	private void convertUnknownsToTiles() {
		for (int i = 0; i < tempElements.length; i++)
			for (int j = 0; j < tempElements[i].length; j++) {
				if (tempElements[i][j].getType() == ElementType.Unknown)
					tempElements[i][j].setType(ElementType.Tile);
			}
	}

	private void checkTopLine() {
		for (int i = 0; i < tempElements[0].length; i++) {
			ArrayElement item = tempElements[0][i]; 
			if (item != null && item.getType() == ElementType.Unknown)
				checkElementForEmpties(item);
		}
	}
	
	private void checkBottomLine() {
		for (int i = 0; i < tempElements[0].length; i++) {
			ArrayElement item = tempElements[tempElements.length - 1][i]; 
			if (item != null && item.getType() == ElementType.Unknown)
				checkElementForEmpties(item);
		}
	}
	
	private void checkLeftCol() {
		for (int i = 0; i < tempElements.length; i++) {
			ArrayElement item = tempElements[i][0]; 
			if (item != null && item.getType() == ElementType.Unknown)
				checkElementForEmpties(item);
		}
	}
	
	private void checkRightCol() {
		for (int i = 0; i < tempElements.length; i++) {
			ArrayElement item = tempElements[i][tempElements[0].length - 1]; 
			if (item != null && item.getType() == ElementType.Unknown)
				checkElementForEmpties(item);
		}
	}

	private void checkElementForEmpties(ArrayElement item) {
		if (!empties.contains(item)) {
			empties.add(item);
			ArrayElement left = getNearItem(item.getX() - 1, item.getY());
			ArrayElement top = getNearItem(item.getX(), item.getY() - 1);
			ArrayElement right = getNearItem(item.getX() + 1, item.getY());
			ArrayElement bottom = getNearItem(item.getX(), item.getY() + 1);
			if (left != null && left.getType() == ElementType.Unknown)
				checkElementForEmpties(left);
			if (top != null && top.getType() == ElementType.Unknown)
				checkElementForEmpties(top);
			if (right != null && right.getType() == ElementType.Unknown)
				checkElementForEmpties(right);
			if (bottom != null && bottom.getType() == ElementType.Unknown)
				checkElementForEmpties(bottom);
		}
	}

	private ArrayElement getNearItem(int indentX, int indentY) {
		ArrayElement result = null;
		if (indentY < tempElements.length && indentX <
				tempElements[0].length && indentY >= 0 && indentX >= 0)
			result = tempElements[indentY][indentX];
		return result;
	}
	
	private void setBorders() {
		for (int i = 0; i < tempElements.length; i++)
			for (int j = 0; j < tempElements[i].length; j++) {
				ArrayElement item = tempElements[i][j];
				if (item.getType() == ElementType.Wall) {
					ArrayElement left = getNearItem(item.getX() - 1, item.getY());
					ArrayElement right = getNearItem(item.getX() + 1, item.getY());
					ArrayElement top = getNearItem(item.getX(), item.getY() - 1);
					ArrayElement bottom = getNearItem(item.getX(), item.getY() + 1);
					
					ArrayElement topleft = getNearItem(item.getX() - 1, item.getY() - 1);
					ArrayElement topright = getNearItem(item.getX() + 1, item.getY() - 1);
					ArrayElement bottomleft = getNearItem(item.getX() - 1, item.getY() + 1);
					ArrayElement bottomRight = getNearItem(item.getX() + 1,	item.getY() + 1);
					
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
	
	private void displaySchematicArray() {
		for (int i = 0; i < tempElements.length; i++) {
			for (int j = 0; j < tempElements[i].length; j++) {
				ArrayElement item = tempElements[i][j];
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
