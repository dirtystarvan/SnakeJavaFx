package com.ac1dtest.snakegame.controllers;

import com.ac1dtest.snakegame.model.GameModel;
import com.ac1dtest.snakegame.view.CommonView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


public class GameController {
	private final CommonView view;
	private final GameModel model;
	private Timeline timeline;
	private boolean newGame;
	private double duration;

	public GameController(CommonView view, GameModel model) {
		this.view = view;
		this.model = model;
		newGame = true;
		duration = 300;
	}

	public void start() {
		timeline = new Timeline(new KeyFrame(Duration.millis(duration), e -> play()));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	public void play() {
		if (newGame) {
			model.createGame();
			newGame = false;
		}

		if (!model.isGameOver()) {
			model.onTurn();
			view.drawScene(model.getCurrentState(), model.getScore());
			adjustSpeed(model.getCurrentDelay());
		} else {
			view.drawEnding(model.winner());
			newGame = false;
		}
	}

	private void adjustSpeed(double duration) {
		if (duration != this.duration) {
			this.duration = duration;

			timeline.stop();
			timeline = new Timeline(new KeyFrame(Duration.millis(duration), e -> play()));
			timeline.setCycleCount(Animation.INDEFINITE);
			timeline.play();
		}
	}

	public void restart() {
		if (model.isGameOver())
			newGame = true;
	}

}