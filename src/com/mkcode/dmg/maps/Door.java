package com.mkcode.dmg.maps;

import com.mkcode.dmg.util.Cardinal;
import com.mkcode.dmg.util.Coords;

public class Door {

	private Room parent;
	private Cardinal side;
	
	private int distNW = 0;
	
	public Door(Room parent) {
		this.parent = parent;
		this.side = null;
	}
	
	public Door(Room parent, Cardinal side) {
		this.parent = parent;
		this.side = side;
	}

	public Coords getCoordsOnMap() {
		Coords coords = null;
		if(side == Cardinal.NORTH)
			coords = new Coords(parent.getLeft() + distNW, parent.getTop());
		else if(side == Cardinal.EAST)
			coords = new Coords(parent.getRight(), parent.getTop() + distNW);
		else if(side == Cardinal.SOUTH)
			coords = new Coords(parent.getLeft() + distNW, parent.getBottom());
		else if(side == Cardinal.WEST)
			coords = new Coords(parent.getLeft(), parent.getTop() + distNW);
		return coords;
	}
	
	@Override
	public String toString() {
		return "Door ~ side: " + side + " coords: " + getCoordsOnMap().toString(); 
	}
	
	public Room getParent() {
		return parent;
	}

	public void setParent(Room parent) {
		this.parent = parent;
	}

	public Cardinal getSide() {
		return side;
	}

	public void setSide(Cardinal side) {
		this.side = side;
	}

	public int getDistNW() {
		return distNW;
	}

	public void setDistNW(int distNW) {
		this.distNW = distNW;
	}
}
