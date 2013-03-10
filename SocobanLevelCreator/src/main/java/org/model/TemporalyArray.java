package org.model;

public class TemporalyArray implements Comparable<TemporalyArray>  {
	private ArrayElement[][] item;
	
	public ArrayElement[][] getItem() {
		return item;
	}

	public void setItem(ArrayElement[][] item) {
		this.item = item;
	}

	public int getIndex() {
		if (item.length > 0)
			return item.length + item[0].length;
		else
			return 0;
	}
	
	public int getWidth() {
		return item.length;
	}

	public int getHeight() {
		return item.length > 0 ? item[0].length : 0;
	}
	
	public int compareTo(TemporalyArray o) {
		if (this.item == null || o == null)
		return 0;
		else if (this.getIndex() > o.getIndex())
			return 1;
		else if (this.getIndex() < o.getIndex())
			return -1;
		else
			return 0;
	}

}
