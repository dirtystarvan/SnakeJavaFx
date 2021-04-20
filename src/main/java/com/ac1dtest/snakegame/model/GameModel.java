package com.ac1dtest.snakegame.model;

import javafx.scene.input.KeyCode;
import java.util.ArrayList;

public class GameModel {
	private static final int GOAL = 25;
	private final int WIDTH;
	private final int HEIGHT;

	private Snake snake;
	private Mouse mouse;

	private int initialDelay;
	private int currentDelay;
	private boolean gameOver;
	private boolean win;

	private int score;
	private final ArrayList<GameObject> currentState;

	public GameModel(int width, int height) {
		WIDTH = width;
		HEIGHT = height;

		currentState = new ArrayList<>(10);
	}

	public void createGame() {
		score = 0;
		win = false;
		gameOver = false;
		initialDelay = currentDelay = 350;
		snake = new Snake((WIDTH - 1) / 2, (HEIGHT - 1) / 2, WIDTH, HEIGHT);

		createNewApple();
		dumpState();
	}

	private void gameOver() {
		gameOver = true;
		win = false;
	}

	private void win() {
		gameOver = true;
		win = true;
	}

	public void onTurn() {
		snake.move(mouse);

		if (!mouse.isAlive) {
			score += 5;
			setTurnTimer();
			createNewApple();
		}

		if (!snake.isAlive)
			gameOver();

		if (snake.getLength() > GOAL)
			win();

		dumpState();
	}

	private void dumpState() {
		currentState.clear();
		snake.dump(currentState);
		mouse.dump(currentState);
	}

	public void onKeyPress(KeyCode key) {
		switch (key) {
			case LEFT:
				snake.setDirection(Direction.LEFT);
				break;
			case RIGHT:
				snake.setDirection(Direction.RIGHT);
				break;
			case UP:
				snake.setDirection(Direction.UP);
				break;
			case DOWN:
				snake.setDirection(Direction.DOWN);
				break;
			default:
				break;
		}
	}

	private void createNewApple() {
		while (snake.checkCollision(mouse = new Mouse(getRandomNumber(WIDTH), getRandomNumber(HEIGHT)))) {}
	}

	private int getRandomNumber(int limit) {
		return (int)(Math.random() * limit);
	}

	public ArrayList<GameObject> getCurrentState() {
		return currentState;
	}

	public int getScore() {
		return score;
	}

	private void setTurnTimer() {
		int level = snake.getLength();

		if (level < 17)
			currentDelay = initialDelay - (int)Math.round(12.5 * level);
		else
			currentDelay = 100;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public boolean winner() {
		return win;
	}

	public int getCurrentDelay() {
		return currentDelay;
	}
}