package com.mkcode.dmg.maps;

import java.util.ArrayList;
import java.util.List;

import com.mkcode.dmg.util.Coords;

public class Map {

	private List<Room> rooms;
	private List<Door> doors;
	private List<Corridor> corridors;

	private static final Coords[] corridorOffsetsToCheck = new Coords[] {
			new Coords(0, 1),
			new Coords(1, -1),
			new Coords(1, 0),
			new Coords(1, 1),
			new Coords(0, -1),
			new Coords(-1, 1),
			new Coords(-1, 0),
			new Coords(-1, -1)
		};
	
	private List<Coords> debugCorridor;

	private int index;
	private int w, h;
	
	public Map() {
		rooms = new ArrayList<>();
		doors = new ArrayList<>();
		corridors = new ArrayList<>();
	}

	public Map(int floorIndex, int w, int h, List<Room> rooms) {
		this();
		index = floorIndex;
		this.w = w;
		this.h = h;
		this.rooms = rooms;
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
	
	public boolean isInsideRoom(Coords coords) {
		for(Room room : rooms) {
			if(coords.getX() > room.getLeft() && coords.getX() < room.getRight()
					&& coords.getY() > room.getTop() && coords.getY() < room.getBottom())
				return true;
		}
		return false;
	}
	
	public boolean isFloor(Coords coords) {
		return !isWall(coords) && !isDoor(coords) && !isCorridor(coords);
	}
	
	public boolean isCorridor(Coords coords) {
		for(Corridor corridor : corridors) {
			for(Coords corrElementCoords : corridor.getElements()) {
				if(corrElementCoords.equalz(coords))
					return true;
			}
		}
		return false;
	}
	
	/**
	 * Loop over corridors' elements, add offsets to each element
	 * and check if the result is floor -> add wall there
	 * @param coords
	 * @return
	 */
	public boolean isCorridorWall(Coords coords) {
		for(Corridor corridor : corridors) {
			for(Coords corrElementCoords : corridor.getElements()) {
				for(Coords offset : corridorOffsetsToCheck) {
					Coords offsetCorrElement = corrElementCoords.vectorAdd(offset);
					if(coords.equalz(offsetCorrElement) && isFloor(offsetCorrElement))
						return true;
				}
			}
		}
		return false;
	}
	
	public boolean isDebugCorridor(Coords coords) {
		if(debugCorridor == null)
			return false;
		for(Coords debugCoords : debugCorridor) {
			if(debugCoords.equalz(coords))
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
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
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

	public List<Coords> getDebugCorridor() {
		return debugCorridor;
	}

	public void setDebugCorridor(List<Coords> debugCorridor) {
		this.debugCorridor = debugCorridor;
	}
}
