package com.ac1dtest.snakegame.controllers;

import com.ac1dtest.snakegame.model.GameModel;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class KeyboardListener implements EventHandler<KeyEvent> {
	private final GameModel model;
	private final GameController gc;

	public KeyboardListener(GameModel model, GameController gc) {
		this.gc = gc;
		this.model = model;
	}

	@Override
	public void handle(KeyEvent keyEvent) {
		switch (keyEvent.getCode()) {
			case LEFT:
			case A:
				model.onKeyPress(KeyCode.LEFT);
				break;
			case RIGHT:
			case D:
				model.onKeyPress(KeyCode.RIGHT);
				break;
			case UP:
			case W:
				model.onKeyPress(KeyCode.UP);
				break;
			case DOWN:
			case S:
				model.onKeyPress(KeyCode.DOWN);
				break;
			case SPACE:
				gc.restart();
				break;
		}
	}
}
