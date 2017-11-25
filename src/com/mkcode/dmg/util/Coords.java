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
