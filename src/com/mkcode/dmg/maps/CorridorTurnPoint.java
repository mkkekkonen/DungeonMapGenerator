package com.mkcode.dmg.maps;

import com.mkcode.dmg.util.Coords;

public class CorridorTurnPoint {

	private Coords coords;
	
	public CorridorTurnPoint(int x, int y) {
		coords = new Coords(x, y);
	}
	
	public CorridorTurnPoint(Coords coords) {
		this.coords = coords;
	}

	public Coords getCoords() {
		return coords;
	}

	public void setCoords(Coords coords) {
		this.coords = coords;
	}
	
	public int getX() {
		return coords.getX();
	}
	
	public void setX(int x) {
		coords.setX(x);
	}
	
	public int getY() {
		return coords.getY();
	}
	
	public void setY(int y) {
		coords.setY(y);
	}
}
