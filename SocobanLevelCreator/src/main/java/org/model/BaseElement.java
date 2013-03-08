package org.model;

import javax.xml.bind.annotation.XmlAttribute;

//@XmlRootElement(name="item")
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
	
	@XmlAttribute(name="x")
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	
	@XmlAttribute(name="y")
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
}
