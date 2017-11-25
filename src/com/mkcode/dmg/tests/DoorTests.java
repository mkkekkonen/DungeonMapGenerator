package com.mkcode.dmg.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.mkcode.dmg.maps.Door;
import com.mkcode.dmg.maps.Map;
import com.mkcode.dmg.maps.Room;
import com.mkcode.dmg.util.Cardinal;
import com.mkcode.dmg.util.Coords;

@RunWith(Parameterized.class)
public class DoorTests {

	private Map map;
	private Room room;
	
	public DoorTests(Map map, Room room) {
		this.map = map;
		this.map.setRooms(Arrays.asList(new Room[] { room }));
		this.room = room;
	}
	
	@Parameterized.Parameters
	public static Collection mapAndRoom() {
		return Arrays.asList(new Object[][] { { createTestMap(), createTestRoom() } });
	}
	
	// DOOR CREATION TESTS
	
	@Test
	public void testCreateN() {
		Door door = new Door(room, Cardinal.NORTH);
		door.setDistNW(2);
		Coords coords = door.getCoordsOnMap();
		assertEquals(4, coords.getX());
		assertEquals(2, coords.getY());
	}
	
	// END DOOR CREATION TESTS
	
	// DOOR VALIDATION TESTS
	
	/*@Test
	public void testBorderN() {
		
	}
	
	@Test
	public void testBorderE() {
		
	}
	
	@Test
	public void testBorderS() {
		
	}
	
	@Test
	public void testBorderW() {
		
	}*/
	
	// END DOOR VALIDATION TESTS
	
	private static Map createTestMap() {
		return new Map(30, 30, null, null);
	}
	
	private static Room createTestRoom() {
		return new Room(2, 2, 5, 5);
	}
}
