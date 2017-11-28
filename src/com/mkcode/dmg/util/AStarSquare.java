package com.mkcode.dmg.util;

import java.util.List;

import com.mkcode.dmg.maps.Map;

	/**
	 * See https://www.raywenderlich.com/4946/introduction-to-a-pathfinding
	 */
	public class AStarSquare {
	
		private Coords coords;
		private AStarSquare previous;
		
		private int g, h;
		private boolean calculated;
		
		public AStarSquare() {
			g = h = 0;
			calculated = false;
		}
		
		public AStarSquare(Coords coords) {
			this();
			this.coords = coords;
			previous = null;
		}
		
		public AStarSquare(Coords coords, AStarSquare previous) {
			this();
			this.coords = coords;
			this.previous = previous;
		}
	
		public void calculateScores(Map map, Coords start, Coords destination) {
			g = previous.getG() + 1; // g = distance from start point
			h = destination.getDistance(coords); // h = estimated (=shortest) distance from the current location to the destination
			calculated = true;
		}
	
	public int getF() {
		return g + h;
	}
	
	public Coords getCoords() {
		return coords;
	}

	public void setCoords(Coords coords) {
		this.coords = coords;
	}

	public AStarSquare getPrevious() {
		return previous;
	}

	public void setPrevious(AStarSquare previous) {
		this.previous = previous;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public boolean isCalculated() {
		return calculated;
	}

	public void setCalculated(boolean calculated) {
		this.calculated = calculated;
	}
	
}
