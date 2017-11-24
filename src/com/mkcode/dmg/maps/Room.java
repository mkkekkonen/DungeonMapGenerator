package com.mkcode.dmg.maps;

import java.util.ArrayList;
import java.util.List;

import com.mkcode.dmg.util.Coords;

public class Room {

	private Coords topLeft;
	private int w, h;
	private List<Door> doors;
	
	public Room(int topLeftX, int topLeftY, int w, int h) {
		topLeft = new Coords(topLeftX, topLeftY);
		this.w = w;
		this.h = h;
		doors = new ArrayList<>();
	}
	
	public boolean overlapsWithRoom(Room otherRoom) {
		return !(otherRoom.getLeft() > this.getRight()
				|| otherRoom.getRight() < this.getLeft()
				|| otherRoom.getTop() > this.getBottom()
				|| otherRoom.getBottom() < this.getTop());
	}

	@Override
	public String toString() {
		return "Room ~ top: " + getTop() + " right: " + getRight() 
			+ " bottom: " + getBottom() + " left: " + getLeft()
			+ " width: " + w + " height: " + h;
	}
	
	public boolean isWall(Coords coords) { /*** TESTAA!!! ***/
		if(
				// x is either left or right, y is between top and bottom 
				((coords.getX() == topLeft.getX() || coords.getX() == topLeft.getX() + w) 
					&& coords.getY() >= topLeft.getY() && coords.getY() < topLeft.getY() + h + 1)
				||
				// y is either top or bottom, x is between left and right
				((coords.getY() == topLeft.getY() || coords.getY() == topLeft.getY() + h)
					&& coords.getX() >= topLeft.getX() && coords.getX() < topLeft.getX() + w)
				)
			return true;
		return false;
	}
	
	public Coords getTopLeft() {
		return topLeft;
	}

	public void setTopLeft(Coords topLeft) {
		this.topLeft = topLeft;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public List<Door> getDoors() {
		return doors;
	}

	public void setDoors(List<Door> doors) {
		this.doors = doors;
	}
	
	public int getTop() {
		return topLeft.getY();
	}
	
	public void setTop(int top) {
		topLeft.setY(top);
	}
	
	public int getLeft() {
		return topLeft.getX();
	}
	
	public void setLeft(int left) {
		topLeft.setX(left);
	}

	public int getRight() {
		return topLeft.getX() + w;
	}
	
	public int getBottom() {
		return topLeft.getY() + h;
	}
}
