package com.mkcode.dmg;

import java.util.List;

import com.mkcode.dmg.maps.Door;
import com.mkcode.dmg.maps.Map;
import com.mkcode.dmg.maps.Room;
import com.mkcode.dmg.util.AStarSquare;
import com.mkcode.dmg.util.Coords;

public class Exporter {
	
	private static final boolean DEBUG = false;
	
	public Exporter() {
		
	}
	
	public void exportToConsole(List<Map> maps) {
		System.out.println(exportToText(maps));
	}
	
	public String exportToText(List<Map> maps) {
		
		StringBuilder mapsStringBuilder = new StringBuilder();
		
		for(int i = 0; i < maps.size(); i++) {
			mapsStringBuilder.append("Map " + (i + 1) + "\n\n");
			if(DEBUG) {
//				for(Room room : maps.get(i).getRooms())
//					mapsStringBuilder.append(room.toString() + "\n");
//				mapsStringBuilder.append("\n");
				for(Door door : maps.get(i).getDoors())
					mapsStringBuilder.append(door.toString() + "\n");
			}
			mapsStringBuilder.append("\n");
			mapsStringBuilder.append(exportMapToText(maps.get(i)));
			mapsStringBuilder.append("\n");
		}
		
		return mapsStringBuilder.toString();
	}
	
	public String exportMapToText(Map map) {
		
		StringBuilder mapStringBuilder = new StringBuilder();
		
		for(int y = 0; y < map.getH(); y++) {
			for(int x = 0; x < map.getW(); x++) {
				Coords coords = new Coords(x, y); 
				if(map.isDoor(coords))
					mapStringBuilder.append("+");
				else if(map.isCorridor(coords))
					mapStringBuilder.append(".");
				else if(map.isWall(coords) || map.isCorridorWall(coords))
					mapStringBuilder.append("#");
				else
					mapStringBuilder.append(".");
			}
			mapStringBuilder.append("\n");
		}
		
		return mapStringBuilder.toString();
	}
	
	public String debugExportMapToText(Map map, List<AStarSquare> closedList) {
		
		StringBuilder mapStringBuilder = new StringBuilder();
		
		for(int y = 0; y < map.getH(); y++) {
			for(int x = 0; x < map.getW(); x++) {
				Coords coords = new Coords(x, y); 
				if(map.isDoor(coords))
					mapStringBuilder.append("+");
				else if(map.isDebugCorridor(coords))
					mapStringBuilder.append("$");
				else if(map.isWall(coords))
					mapStringBuilder.append("#");
				else if(closedListContains(closedList, coords))
					mapStringBuilder.append("X");
				else
					mapStringBuilder.append(".");
			}
			mapStringBuilder.append("\n");
		}
		
		return mapStringBuilder.toString();
	}
	
	private boolean closedListContains(List<AStarSquare> closedList, Coords coords) {
		
		for(AStarSquare square : closedList) {
			if(square.getCoords().equalz(coords))
				return true;
		}
		
		return false;
	}
}
