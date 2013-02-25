package org.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.model.LevelS;

@XmlRootElement(name="LevelCollection")
public class SourceLevelsList {

	int maxHeight;
	int maxWidth;
	private List<LevelS> levels = new ArrayList<LevelS>();

	@XmlElementWrapper(name="Levels")
	@XmlElement(name="Level")
	public List<LevelS> getLevels() {
		return levels;
	}

	public void setLevels(List<LevelS> levels) {
		this.levels = levels;
	}

	@XmlAttribute(name="MaxHeight")
	public int getMaxHeight() {
		return maxHeight;
	}
	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}

	@XmlAttribute(name="MaxWidth")
	public int getMaxWidth() {
		return maxWidth;
	}
	public void setMaxWidth(int maxWidth) {
		this.maxWidth = maxWidth;
	}
}
