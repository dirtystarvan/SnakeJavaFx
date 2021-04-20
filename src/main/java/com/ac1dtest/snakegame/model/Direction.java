package com.ac1dtest.snakegame.model;

public enum Direction {
	UP((byte)1), RIGHT((byte)2), DOWN((byte)-1), LEFT((byte)-2);

	private final byte code;

	Direction(byte code) {
		this.code = code;
	}

	public boolean isRotate(Direction newDirection) {
		return this.code + newDirection.code == 0;
	}
}