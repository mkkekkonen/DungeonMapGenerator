package com.mkcode.dmg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mkcode.dmg.maps.Map;

public class DungeonMapGenerator {

	public static void main(String[] args) {

		List<String> argsList = Arrays.asList(args);
		
		if(!argsList.contains("-w") 
				|| !argsList.contains("-h") 
				|| !argsList.contains("-f")) {
			System.out.println("Usage: java -jar DungeonMapGenerator.jar -w [width] -h [height] -f [floors] -[export option]");
			System.exit(1);
		}
		
		int width = 0, height = 0, floors = 0;
		
		for(int i = 0; i < args.length; i++) {
			if(args[i].equalsIgnoreCase("-w"))
				width = tryParseInt(args, i + 1, 30);
			else if(args[i].equalsIgnoreCase("-h"))
				height = tryParseInt(args, i + 1, 20);
			else if(args[i].equalsIgnoreCase("-f"))
				floors = tryParseInt(args, i + 1, 1);
		}
		
//		System.out.println("width: " + width + " height: " + height + " floors: " + floors);
		
		Generator mapGenerator = new Generator();
		List<Map> maps = mapGenerator.generateMaps(width, height, floors);
		
		Exporter mapExporter = new Exporter();
		
		if(argsList.contains("-c"))
			System.out.println(mapExporter.exportToText(maps));
		else
			System.out.println("No export option selected, quitting");
	}

	private static int tryParseInt(String[] args, int index, int deflt) {
		int res;
		if(index >= args.length) // index out of range
			res = deflt;
		try {
			res = Integer.parseInt(args[index], 10);
		} catch(NumberFormatException ex) {
			res = deflt;
		}
		return res;
	}
}
