package org.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

//@XmlRootElement(name="Level")
public class LevelDestination {
	
	private int width;
	private int height;
	private int id;
	
	private List<BaseElement> borders = new ArrayList<BaseElement>();
	private List<BaseElement> tiles = new ArrayList<BaseElement>();
	private List<BaseElement> walls = new ArrayList<BaseElement>();
	private List<BaseElement> docks = new ArrayList<BaseElement>();
	private List<BaseElement> boxes = new ArrayList<BaseElement>();
	private List<BaseElement> worker = new ArrayList<BaseElement>();
	private List<DataElement> signs = new ArrayList<DataElement>();
	
	public void renumber(){
		renumberList(borders);
		renumberList(tiles);
		renumberList(walls);
		renumberList(docks);
		renumberList(boxes);
		renumberList(worker);
	}
	
	@XmlAttribute(name="width")
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	
	@XmlAttribute(name="height")
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	@XmlAttribute(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@XmlElementWrapper(name="borders")
	@XmlElement(name="item")
	public List<BaseElement> getBorders() {
		return borders;
	}
	public void setBorders(List<BaseElement> borders) {
		this.borders = borders;
	}
	
	@XmlElementWrapper(name="tiles")
	@XmlElement(name="item")
	public List<BaseElement> getTiles() {
		return tiles;
	}
	public void setTiles(List<BaseElement> tiles) {
		this.tiles = tiles;
	}
	
	@XmlElementWrapper(name="walls")
	@XmlElement(name="item")
	public List<BaseElement> getWalls() {
		return walls;
	}
	public void setWalls(List<BaseElement> walls) {
		this.walls = walls;
	}
	
	@XmlElementWrapper(name="docks")
	@XmlElement(name="item")
	public List<BaseElement> getDocks() {
		return docks;
	}
	public void setDocks(List<BaseElement> docks) {
		this.docks = docks;
	}
	
	@XmlElementWrapper(name="boxes")
	@XmlElement(name="item")
	public List<BaseElement> getBoxes() {
		return boxes;
	}
	public void setBoxes(List<BaseElement> boxes) {
		this.boxes = boxes;
	}
	
	@XmlElementWrapper(name="worker")
	@XmlElement(name="item")
	public List<BaseElement> getWorker() {
		return worker;
	}
	public void setWorker(List<BaseElement> worker) {
		this.worker = worker;
	}
	
	@XmlElementWrapper(name="signs")
	@XmlElement(name="item")
	public List<DataElement> getSigns() {
		return signs;
	}

	public void setSigns(List<DataElement> signs) {
		this.signs = signs;
	}

	private void renumberList(List<BaseElement> list){
		int index = 1;
		for (BaseElement element : list) {
			element.setId(index);
			index++;
		}
	}
	
	
	
}
