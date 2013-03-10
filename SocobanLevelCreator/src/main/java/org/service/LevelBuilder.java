package org.service;

import java.util.List;
import java.util.Random;

import org.model.ArrayElement;
import org.model.BaseElement;
import org.model.DataElement;
import org.model.ElementType;
import org.model.LevelDestination;
import org.model.TemporalyArray;

public class LevelBuilder {
	
	public LevelDestinationList build(List<TemporalyArray> list) {
		LevelDestinationList result = new LevelDestinationList();
		processList(list, result);
		return  result;
	}
	
	private void processList(List<TemporalyArray> list, LevelDestinationList result) {
		int index = 1;
		for (TemporalyArray item : list) {
			LevelDestination level = new LevelDestination();
			level.setId(index);
			level.setWidth(item.getWidth());
			level.setHeight(item.getHeight());
			result.getItems().add(level);
			processArray(item.getItem(), level);
			level.renumber();
			index++;
			break; // Только 1 уровень
		}
	}
	
	private void processArray(ArrayElement[][] array, LevelDestination level) {
		for (int i = 0; i < array.length; i++)
			for (int j = 0; j < array[i].length; j++) {
				handleByType(array[i][j], level);
			}
	}
	
	private void handleByType(ArrayElement source, LevelDestination level) {
		handleBorders(source, level);
		handleWalls(source, level);
		handleTiles(source, level);
		handleBoxes(source, level);
		handleDocks(source, level);
		handleWorker(source, level);
	}
	
	private void baseAddItem(ArrayElement source, List<BaseElement> list){
		BaseElement item = new BaseElement();
		item.setCol(source.getX());
		item.setRow(source.getY());
		list.add(item);
	}
	
	private void handleBorders(ArrayElement source, LevelDestination level) {
		if (source.getType() == ElementType.Border)
			baseAddItem(source, level.getBorders());
	}
	
	private void handleWalls(ArrayElement source, LevelDestination level) {
		if (source.getType() == ElementType.Wall)
			baseAddItem(source, level.getWalls());
	}
	
	private void handleTiles(ArrayElement source, LevelDestination level) {
		if (source.getType() == ElementType.Tile ||
				source.getType() == ElementType.Box ||
				source.getType() == ElementType.BoxOnDock ||
				source.getType() == ElementType.Worker ||
				source.getType() == ElementType.WorkerOnDock ||
				source.getType() == ElementType.Dock ||
				source.getType() == ElementType.Wall) {
			baseAddItem(source, level.getTiles());
			if (source.getType() == ElementType.Tile) {
				generateSign(source, level);
			}
		}
			
	}
	
	private void generateSign(ArrayElement source, LevelDestination level) {
		Random rand = new Random();
		int random = rand.nextInt(100);
		if (random > 90) {
			DataElement item = new DataElement();
			item.setCol(source.getX());
			item.setRow(source.getY());
			item.setData(random - 90);
			level.getSigns().add(item);
		}
	}
	
	private void handleBoxes(ArrayElement source, LevelDestination level) {
		if (source.getType() == ElementType.Box ||
				source.getType() == ElementType.BoxOnDock)
			baseAddItem(source, level.getBoxes());
	}
	
	private void handleDocks(ArrayElement source, LevelDestination level) {
		if (source.getType() == ElementType.Dock ||
				source.getType() == ElementType.BoxOnDock ||
				source.getType() == ElementType.WorkerOnDock)
			baseAddItem(source, level.getDocks());
	}
	
	private void handleWorker(ArrayElement source, LevelDestination level) {
		if (source.getType() == ElementType.Worker ||
				source.getType() == ElementType.WorkerOnDock)
			baseAddItem(source, level.getWorker());
	}
	
}








