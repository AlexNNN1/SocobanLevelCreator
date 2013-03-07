package org.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Level")
public class LevelDestination {
	
	private List<BaseElement> borders = new ArrayList<BaseElement>();
	
	@XmlElement(name="items")
	public List<BaseElement> getBorders() {
		return borders;
	}
	public void setBorders(List<BaseElement> borders) {
		this.borders = borders;
	}
	
	
}
