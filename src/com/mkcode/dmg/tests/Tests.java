package com.mkcode.dmg.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.mkcode.dmg.maps.Map;
import com.mkcode.dmg.maps.Room;
import com.mkcode.dmg.util.Coords;

@RunWith(Parameterized.class)
public class Tests {

	private Map map;
	
	public Tests(Map map) {
		this.map = map;
	}
	
	@Parameterized.Parameters
	public static Collection map() {
		return Arrays.asList(new Map[] { createTestMap() });
	}
	
	@Test
	public void testNW() {
		assertEquals(true, map.isWall(new Coords(2, 2)));
	}
	
	@Test
	public void testN() {
		assertEquals(true, map.isWall(new Coords(3, 2)));
	}
	
	@Test
	public void testNE() {
		assertEquals(true, map.isWall(new Coords(5, 2)));
	}
	
	@Test
	public void testE() {
		assertEquals(true, map.isWall(new Coords(5, 3)));
	}
	
	@Test
	public void testSE() {
		assertEquals(true, map.isWall(new Coords(5, 5)));
	}
	
	@Test
	public void testS() {
		assertEquals(true, map.isWall(new Coords(3, 5)));
	}
	
	@Test
	public void testSW() {
		assertEquals(true, map.isWall(new Coords(2, 5)));
	}
	
	@Test
	public void testW() {
		assertEquals(true, map.isWall(new Coords(2, 3)));
	}
	
	@Test
	public void testCenter() {
		assertEquals(false, map.isWall(new Coords(3, 3)));
	}
	
	private static Map createTestMap() {
		return new Map(8, 8, createTestRoom(), null);
	}
	
	private static List<Room> createTestRoom() {
		List<Room> roomList = new ArrayList<>();
		Room room = new Room(2, 2, 4, 4);
		roomList.add(room);
		return roomList;
	}
}
