package com.ac1dtest.snakegame.model;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class Snake extends GameObject {
	private static final String HEAD_SIGN = "\uD83D\uDC7E";
	private static final String DEAD_SIGN = "\u274C";
	private static final String BODY_SIGN = "\u26AB";

	private final int borderWidth, borderHeight;

	public boolean isAlive;
	private Direction direction = Direction.LEFT;

	private final List<GameObject> snakeParts = new ArrayList<>();

	public Snake(int x, int y, int borderWidth, int borderHeight) {
		super(x, y);
		this.borderHeight = borderHeight;
		this.borderWidth = borderWidth;

		snakeParts.add(new GameObject(x, y));
		snakeParts.add(new GameObject(x + 1, y));
		snakeParts.add(new GameObject(x + 2, y));

		isAlive = true;
	}

	public void setDirection(Direction newDirection) {
		if ((this.direction == Direction.LEFT || this.direction == Direction.RIGHT)
				&& snakeParts.get(0).x == snakeParts.get(1).x) {
			return;
		}
		if ((this.direction == Direction.UP || this.direction == Direction.DOWN)
				&& snakeParts.get(0).y == snakeParts.get(1).y) {
			return;
		}

		if (!direction.isRotate(newDirection))
			direction = newDirection;
	}

	public int getLength() {
		return snakeParts.size();
	}

	public void move(Mouse apple) {
		GameObject newHead = createNewHead();

		if (checkCollision(newHead) ||
				newHead.x < 0 || newHead.x >= borderWidth ||
				newHead.y < 0 || newHead.y >= borderHeight) {
			isAlive = false;
			return;
		}

		snakeParts.add(0, newHead);

		if (newHead.x == apple.x && newHead.y == apple.y)
			apple.isAlive = false;
		else
			removeTail();
	}

	public GameObject createNewHead() {
		GameObject currentHead = snakeParts.get(0);
		GameObject newHead = new GameObject(0, 0);

		switch (direction) {
			case UP:
				newHead.x = currentHead.x;
				newHead.y = currentHead.y - 1;
				break;
			case DOWN:
				newHead.x = currentHead.x;
				newHead.y = currentHead.y + 1;
				break;
			case LEFT:
				newHead.x = currentHead.x - 1;
				newHead.y = currentHead.y;
				break;
			case RIGHT:
				newHead.x = currentHead.x + 1;
				newHead.y = currentHead.y;
				break;
		}

		return newHead;
	}

	public void removeTail() {
		snakeParts.remove(snakeParts.size() - 1);
	}

	public boolean checkCollision(GameObject cell) {
		for (GameObject item: snakeParts)
			if (item.x == cell.x && item.y == cell.y)
				return true;

		return false;
	}

	public void dump(ArrayList<GameObject> arr) {
		GameObject head = snakeParts.get(0);
		head.content = isAlive ? HEAD_SIGN : DEAD_SIGN;
		head.color = isAlive ? Color.BLACK : Color.WHITE;

		GameObject bodypart;
		for (int i = 1; i < snakeParts.size(); i++) {
			bodypart = snakeParts.get(i);
			bodypart.content = BODY_SIGN;
			bodypart.color = isAlive ? Color.BLACK : Color.WHITE;
		}

		arr.addAll(snakeParts);
	}
}