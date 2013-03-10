package org.model;

import javax.xml.bind.annotation.XmlAttribute;

public class DataElement extends BaseElement {
	
	private int data;
	
	@XmlAttribute(name="data")
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}	

}
