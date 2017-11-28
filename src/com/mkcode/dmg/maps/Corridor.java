package com.mkcode.dmg.maps;

import java.util.ArrayList;
import java.util.List;

import com.mkcode.dmg.util.Coords;

public class Corridor {

	private Door start, end;
	private List<Coords> elements;
	
	public Corridor() {
		elements = new ArrayList<>();
	}

	public Corridor(Door start, Door end, List<Coords> elements) {
		this.start = start;
		this.end = end;
		this.elements = elements;
	}
	
	public Door getStart() {
		return start;
	}

	public void setStart(Door start) {
		this.start = start;
	}

	public Door getEnd() {
		return end;
	}

	public void setEnd(Door end) {
		this.end = end;
	}

	public List<Coords> getElements() {
		return elements;
	}

	public void setElements(List<Coords> elements) {
		this.elements = elements;
	}
}
