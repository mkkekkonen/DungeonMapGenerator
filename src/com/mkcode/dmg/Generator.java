package com.mkcode.dmg;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mkcode.dmg.maps.Map;
import com.mkcode.dmg.maps.Room;

public class Generator {
	
	private static final int
		MIN_ROOMS = 1,
		MAX_ROOMS = 5,
		MIN_DIM = 3, // dim = min and max room dimensions
		MAX_DIM = 10;
	
	private Random random;
	
	public Generator() {
		random = new Random(System.currentTimeMillis());
	}

	public List<Map> generateMaps(int mapWidth, int mapHeight, int floors) {
		
		List<Map> mapList = new ArrayList<>();
		
		for(int i = 0; i < floors; i++) {
			Map map = new Map(mapWidth, mapHeight, generateRooms(mapWidth, mapHeight), null);
			mapList.add(map);
		}
		
		return mapList;
	}
	
	public List<Room> generateRooms(int mapWidth, int mapHeight) {
		
		List<Room> roomList = new ArrayList<>();
		int nRooms = random.nextInt(5) + 1;
		
		for(int i = 0; i < nRooms; i++) {
			
			Room room = null;
			
			do {
				
				int w = 0, h = 0, x = 0, y = 0;
				
				w = getRandomDim(random);
				h = getRandomDim(random);
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
	
	private int getRandomDim(Random random) {
		return random.nextInt(MAX_DIM - MIN_DIM + 1) + MIN_DIM;
	}
}
