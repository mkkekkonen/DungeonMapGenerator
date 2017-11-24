package com.mkcode.dmg;

import java.util.List;

import com.mkcode.dmg.maps.Map;
import com.mkcode.dmg.maps.Room;
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
				for(Room room : maps.get(i).getRooms())
					mapsStringBuilder.append(room.toString() + "\n");
			}
			mapsStringBuilder.append("\n");
			mapsStringBuilder.append(exportMapToText(maps.get(i)));
			mapsStringBuilder.append("\n");
		}
		
		return mapsStringBuilder.toString();
	}
	
	private String exportMapToText(Map map) {
		
		StringBuilder mapStringBuilder = new StringBuilder();
		
		for(int y = 0; y < map.getH(); y++) {
			for(int x = 0; x < map.getW(); x++) {
				if(map.isWall(new Coords(x, y)))
					mapStringBuilder.append("#");
				else
					mapStringBuilder.append(".");
			}
			mapStringBuilder.append("\n");
		}
		
		return mapStringBuilder.toString();
	}
}
