package com.ac1dtest.snakegame.model;

import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Mouse extends GameObject {
	private static final String MOUSE_SIGN = "\uD83D\uDC2D";
	public boolean isAlive;

	public Mouse(int x, int y) {
		super(x, y);

		content = MOUSE_SIGN;
		color = Color.DEEPPINK;
		isAlive = true;
	}

	public void dump(ArrayList<GameObject> arr) {
		arr.add(this);
	}
}