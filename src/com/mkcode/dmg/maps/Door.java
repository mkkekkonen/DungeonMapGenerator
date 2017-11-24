package com.mkcode.dmg.maps;

import com.mkcode.dmg.util.Cardinal;

public class Door {

	private Room parent;
	private Cardinal side;
	
	public Door(Room parent, Cardinal side) {
		this.parent = parent;
		this.side = side;
	}

}
