package org.service;


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.model.LevelDestination;

@XmlRootElement(name="Levels")
public class LevelDestinationList {
	private List<LevelDestination> items = new ArrayList<LevelDestination>();

	@XmlElement(name="Level")
	public List<LevelDestination> getItems() {
		return items;
	}

	public void setItems(List<LevelDestination> items) {
		this.items = items;
	}
	

}
