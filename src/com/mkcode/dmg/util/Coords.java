package com.mkcode.dmg.util;

public class Coords {

	private int x, y;
	
	public Coords() {
		x = 0;
		y = 0;
	}
	
	public Coords(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean equalz(Coords coords) {
		return x == coords.getX() && y == coords.getY();
	}

	public Coords vectorAdd(Coords coords) {
		return new Coords(x + coords.getX(), y + coords.getY());
	}
	
	public Coords vectorSub(Coords coords) {
		return new Coords(x - coords.getX(), y - coords.getY());
	}
	
	public int getDistance(Coords coords) {
		Coords distanceXY = coords.vectorSub(this);
		return Math.abs(distanceXY.getX()) + Math.abs(distanceXY.getY());
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
