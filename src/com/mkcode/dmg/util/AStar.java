package com.mkcode.dmg.util;

import java.util.ArrayList;
import java.util.List;

import com.mkcode.dmg.Exporter;
import com.mkcode.dmg.maps.Map;

/**
 * See https://www.raywenderlich.com/4946/introduction-to-a-pathfinding
 */
public class AStar {

	private List<AStarSquare> openList;
	private List<AStarSquare> closedList;
	
	private Exporter debugExporter;
	
	private static final Coords[] squareOffsetsToCheck = new Coords[] {
		new Coords(0, 1),
		new Coords(1, 0),
		new Coords(0, -1),
		new Coords(-1, 0)
	};
	
	public AStar() {
		openList = new ArrayList<>();
		closedList = new ArrayList<>();
		debugExporter = new Exporter();
	}
	
	public List<Coords> findPath(Coords start, Coords end, Map map) {
		
		openList = new ArrayList<>();
		closedList = new ArrayList<>();
		
		List<Coords> path = new ArrayList<>(); // each square on the generated path
		
		AStarSquare currentSquare = new AStarSquare(start, null); // current square around which possible squares are evaluated - start point
		closedList.add(currentSquare); // add start point to closed list
		
		createUpdateOpenSquares(currentSquare, start, end, map); // create open squares for first iteration
		calculateScores(start, end, map); // calculate scores for first iteration
		
		int loopGuard = 0;
		
		// loop until break
		while(true) {
			if(openList.size() == 0) {
				break;
			}
			currentSquare = getLowestOpenSquare(); // get the square with the lowest score
			if(isAdjacentToDoor(currentSquare.getCoords(), end) /*|| currentSquare.getCoords().equalz(end) || loopGuard >= 1000*/) // end point reached or no possible next squares
				break;															// - exclude last square (door)
			openList.remove(currentSquare);
			closedList.add(currentSquare);
			createUpdateOpenSquares(currentSquare, start, end, map); // create and/or update squares next to the current square
			calculateScores(start, end, map);
			map.setDebugCorridor(formulatePath(currentSquare));
			loopGuard++;
		}
		
		path = formulatePath(currentSquare);
		
		return path;
	}
	
	private void createUpdateOpenSquares(AStarSquare currentSquare, Coords start, Coords end, Map map) {
		
		for(Coords squareOffsetToCheck : squareOffsetsToCheck) {
			Coords coordsToCheck = currentSquare.getCoords().vectorAdd(squareOffsetToCheck); 
			if(map.isFloor(coordsToCheck) 
					&& !map.isInsideRoom(coordsToCheck)
					&& isWithinMap(map, coordsToCheck)
					&& !isClosed(coordsToCheck)) {
				AStarSquare openSquare = getOpen(coordsToCheck); 
				if(openSquare == null)
					openList.add(new AStarSquare(coordsToCheck, currentSquare));
				else // is open
					openSquare.setPrevious(currentSquare);
			}
		}
	}
	
	private boolean isClosed(Coords coords) {
		for(AStarSquare closed : closedList) {
			if(closed.getCoords().equalz(coords))
				return true;
		}
		return false;
	}
	
	private AStarSquare getOpen(Coords coords) {
		for(AStarSquare open : openList) {
			if(open.getCoords().equalz(coords))
				return open;
		}
		return null;
	}
	
	private boolean isWithinMap(Map map, Coords coords) {
		if(coords.getX() < 0 
				|| coords.getY() < 0 
				|| coords.getX() >= map.getW()
				|| coords.getY() >= map.getH())
			return false;
		return true;
	}
	
	private boolean isAdjacentToDoor(Coords coords, Coords end) {
		for(Coords squareOffset : squareOffsetsToCheck) {
			Coords offsetSquare = coords.vectorAdd(squareOffset);
			if(offsetSquare.equalz(end))
				return true;
		}
		return false;
	}
	
	private void calculateScores(Coords start, Coords end, Map map) {
		for(AStarSquare square : openList) {
			square.calculateScores(map, start, end);
		}
	}
	
	private AStarSquare getLowestOpenSquare() {
		
		AStarSquare lowestScore = null;
		
		for(AStarSquare square : openList) {
			// if lowestScore not set or if square.f is lower than lowestScore.f, set square to lowestScore
			if(lowestScore == null || lowestScore.getF() > square.getF())
				lowestScore = square;
		}
		
		return lowestScore;
	}
	
	// exclude first square (door)
	private List<Coords> formulatePath(AStarSquare currentSquare) {
		List<Coords> path = new ArrayList<>();
		while(currentSquare.getPrevious() != null) {
			path.add(currentSquare.getCoords());
			currentSquare = currentSquare.getPrevious();
		}
		return path;
	}
}
