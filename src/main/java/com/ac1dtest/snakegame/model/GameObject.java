package com.ac1dtest.snakegame.model;

import javafx.scene.paint.Paint;

public class GameObject {
	public int x;
	public int y;
	public String content;
	public Paint color;

	public GameObject(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public GameObject(int x, int y, String content, Paint color) {
		this.x = x;
		this.y = y;
		this.content = content;
		this.color = color;
	}
}