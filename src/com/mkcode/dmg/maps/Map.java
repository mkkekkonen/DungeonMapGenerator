package com.mkcode.dmg.maps;

import java.util.ArrayList;
import java.util.List;

import com.mkcode.dmg.util.Coords;

public class Map {

	private List<Room> rooms;
	private List<Door> doors;
	private List<Corridor> corridors;

	private int w, h;
	
	public Map() {
		rooms = new ArrayList<>();
		doors = new ArrayList<>();
		corridors = new ArrayList<>();
	}

	public Map(int w, int h, List<Room> rooms, List<Corridor> corridors) {
		this();
		this.w = w;
		this.h = h;
		this.rooms = rooms;
		this.corridors = corridors;
	}
	
	public boolean isWall(Coords coords) {
		for(Room room : rooms) {
			if(room.isWall(coords))
				return true;
		}
		return false;
	}
	
	public boolean isDoor(Coords coords) {
		for(Door door : doors) {
			if(door.getCoordsOnMap().equalz(coords))
				return true;
		}
		return false;
	}
	
	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
	
	public List<Door> getDoors() {
		return doors;
	}

	public void setDoors(List<Door> doors) {
		this.doors = doors;
	}

	public List<Corridor> getCorridors() {
		return corridors;
	}

	public void setCorridors(List<Corridor> corridors) {
		this.corridors = corridors;
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
}
