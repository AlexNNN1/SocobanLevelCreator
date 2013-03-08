package org.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="item")
public class BaseElement {
	
	private int id;
	private int col;
	private int row;

	
	@XmlAttribute(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@XmlAttribute(name="col")
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	
	@XmlAttribute(name="row")
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}	
}
