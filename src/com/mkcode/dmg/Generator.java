package com.mkcode.dmg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.mkcode.dmg.maps.Corridor;
import com.mkcode.dmg.maps.Door;
import com.mkcode.dmg.maps.Map;
import com.mkcode.dmg.maps.Room;
import com.mkcode.dmg.util.AStar;
import com.mkcode.dmg.util.AStarSquare;
import com.mkcode.dmg.util.Cardinal;
import com.mkcode.dmg.util.Coords;

public class Generator {
	
	private static final int
		MIN_ROOMS = 1,
		MAX_ROOMS = 5,
		MIN_DIM = 3, // dim = min and max room dimensions
		MAX_DIM = 10;
	
	private AStar pathfinder;
	private Random random;
	
	private int mapWidth, mapHeight, floors;
	
	public Generator(int mapWidth, int mapHeight, int floors) {
		random = new Random(System.currentTimeMillis());
		pathfinder = new AStar();
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		this.floors = floors;
	}

	public List<Map> generateMaps() {
		
		List<Map> mapList = new ArrayList<>();
		
		for(int i = 0; i < floors; i++) {
			Map map = new Map(i + 1, mapWidth, mapHeight, generateRooms(mapWidth, mapHeight));
			generateDoors(map, map.getRooms());
			map.setCorridors(generateCorridors(map));
			mapList.add(map);
		}
		
		return mapList;
	}
	
	private List<Room> generateRooms(int mapWidth, int mapHeight) {
		
		List<Room> roomList = new ArrayList<>();
		int nRooms = random.nextInt(5) + 1;
		
		for(int i = 0; i < nRooms; i++) {
			
			Room room = null;
			
			do {
				
				int w = 0, h = 0, x = 0, y = 0;
				
				w = getRandomDim();
				h = getRandomDim();
				x = random.nextInt(mapWidth - w);
				y = random.nextInt(mapHeight - h);
				
				room = new Room(x, y, w, h);
				
			} while(roomsOverlap(room, roomList));
			
			roomList.add(room);
		}
		
		return roomList;
	}
	
	private boolean roomsOverlap(Room room, List<Room> rooms) {
		
		for(Room listRoom : rooms) {
			if(room.overlapsWithRoom(listRoom))
				return true;
		}
		
		return false;
	}
	
	private int getRandomDim() {
		return random.nextInt(MAX_DIM - MIN_DIM + 1) + MIN_DIM;
	}
	
	private void generateDoors(Map map, List<Room> roomList) {
		
		for(int i = 0; i < roomList.size(); i++) {
			
			Door door = new Door(roomList.get(i));
			
			do {
				door.setSide(getRandomCardinal());
				door.setDistNW(getRandomDistNW(roomList.get(i), door.getSide()));
			} while(!validateDoor(map, door));
			
			roomList.get(i).setDoors(Arrays.asList(new Door[] { door }));
			
			map.getDoors().add(door);
		}
	}
	
	private Cardinal getRandomCardinal() {
		int cardinalInt = random.nextInt(4);
		Cardinal cardinal;
		switch(cardinalInt) {
		case 1:
			cardinal = Cardinal.EAST;
			break;
		case 2:
			cardinal = Cardinal.SOUTH;
			break;
		case 3:
			cardinal = Cardinal.WEST;
		case 0:
		default:
			cardinal = Cardinal.NORTH;
			break;
		}
		return cardinal;
	}
	
	private int getRandomDistNW(Room room, Cardinal cardinal) {
		int distNW = 0;
		if(cardinal == Cardinal.NORTH || cardinal == Cardinal.SOUTH)
			distNW = random.nextInt(room.getW() - 2) + 1; // exclude corners
		else if(cardinal == Cardinal.EAST || cardinal == Cardinal.WEST)
			distNW = random.nextInt(room.getH() - 2) + 1; // exclude corners
		return distNW;
	}
	
	private boolean validateDoor(Map map, Door door) {
		
		Coords doorCoordsOnMap = door.getCoordsOnMap(); 
		
		if(door.getSide() == Cardinal.NORTH 
				&& (door.getParent().getTop() == 0 
					// check if adjacent to another room
					|| map.isWall(new Coords(doorCoordsOnMap.getX(), doorCoordsOnMap.getY() - 1))))
			return false;
		else if(door.getSide() == Cardinal.EAST 
				&& (door.getParent().getRight() == mapWidth - 1 
					// check if adjacent to another room
					|| map.isWall(new Coords(doorCoordsOnMap.getX() + 1, doorCoordsOnMap.getY()))))
			return false;
		else if(door.getSide() == Cardinal.SOUTH 
				&& (door.getParent().getBottom() == mapHeight - 1
					// check if adjacent to another room
					|| map.isWall(new Coords(doorCoordsOnMap.getX(), doorCoordsOnMap.getY() + 1))))
			return false;
		else if(door.getSide() == Cardinal.WEST 
				&& (door.getParent().getLeft() == 0
					// check if adjacent to another room
					|| map.isWall(new Coords(doorCoordsOnMap.getX() - 1, doorCoordsOnMap.getY()))))
			return false;
		
		return true;
	}
	
	private List<Corridor> generateCorridors(Map map) {
		List<Corridor> corridors = new ArrayList<>();
		List<Room> rooms = map.getRooms();
		if(map.getRooms().size() > 2) {
			for(int i = 0; i < rooms.size() - 2; i++)
				corridors.add(generateCorridor(map, rooms.get(i), rooms.get(i + 1)));
			
		}
		if(map.getRooms().size() >= 2)
			corridors.add(generateCorridor(map, rooms.get(rooms.size() - 1), rooms.get(0)));
		return corridors;
	}
	
	private Corridor generateCorridor(Map map, Room r1, Room r2) {
		Corridor corridor = new Corridor(
				r1.getDoors().get(0),
				r2.getDoors().get(0),
				pathfinder.findPath(
						r1.getDoors().get(0).getCoordsOnMap(), 
						r2.getDoors().get(0).getCoordsOnMap(), 
						map
				)
		);
		return corridor;
	}
	
	private void debugFindPath(Map map) {
		if(map.getRooms().size() == 1)
			return;
		map.setDebugCorridor(pathfinder.findPath(
				map.getRooms().get(0).getDoors().get(0).getCoordsOnMap(), 
				map.getRooms().get(1).getDoors().get(0).getCoordsOnMap(), 
				map
		)); 
	}
}
