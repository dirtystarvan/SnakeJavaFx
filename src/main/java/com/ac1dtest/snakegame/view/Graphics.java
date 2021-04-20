package com.ac1dtest.snakegame.view;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import com.ac1dtest.snakegame.model.GameObject;

import java.util.ArrayList;

public class Graphics implements CommonView {
	private Scene scene;
	private Label score;
	GraphicsContext context;

	private final double windowWidth = 600;
	private final double windowHeight = 625;
	private final double scoreboardHeight = 25;
	private final double cellW;
	private final double cellH;

	private final int columns;
	private final int rows;


	public Graphics(int columns, int rows) {
		this.columns = columns;
		this.rows = rows;
		cellW = windowWidth / columns;
		cellH = (windowHeight - scoreboardHeight) / rows;

		init();
	}

	private void init() {
		Group root = new Group();
		Canvas canvas = new Canvas(windowWidth, windowHeight - scoreboardHeight);
		root.getChildren().add(canvas);

		context = canvas.getGraphicsContext2D();

		score = new Label("Score:");
		score.setFont(new Font(18));
		score.setTextFill(Color.BLACK);

		VBox container = new VBox();
		container.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE,
				new CornerRadii(1), new Insets(0.0))));
		container.getChildren().addAll(root, score);

		scene = new Scene(container, windowWidth, windowHeight);
	}

	public void drawBackground() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if ((i + j) % 2 != 0)
					context.setFill(Color.GREENYELLOW);
				else
					context.setFill(Color.GREEN);

				context.fillRect(i * cellW, j * cellH, cellW, cellH);
			}
		}
	}

	@Override
	public void drawObject(GameObject object) {
		//test
		context.setFont(new Font(30));
		context.setFill(object.color);
		context.fillText(object.content, object.x * cellW + 7, object.y * cellH + 31);
	}

	@Override
	public void drawScene(ArrayList<GameObject> objects, int score) {
		drawBackground();
		drawObjects(objects);
		drawScore(score);
	}

	public void drawScene() {
		drawBackground();
	}

	public void drawObjects(ArrayList<GameObject> arr) {
		context.setFont(new Font(26));

		for (GameObject item: arr) {
			context.setFill(item.color);
			context.fillText(item.content, item.x * cellW + 7, item.y * cellH + 31);
		}
	}

	@Override
	public void drawEnding(boolean win) {
		if (win) {
			score.setTextFill(Color.BLUE);
			context.setStroke(Color.BLUE);
			context.setFont(new Font("Berlin Sans FB", 72));
			context.strokeText("You are WINNER!", windowWidth / 2 - 270, (windowHeight - scoreboardHeight) / 2);
		} else {
			context.setFont(new Font("Berlin Sans FB", 72));
			context.setFontSmoothingType(FontSmoothingType.LCD);
			context.setFill(Color.RED);
			context.fillText("GAME OVER", windowWidth / 2 - 190, (windowHeight - scoreboardHeight) / 2);

			score.setText("YOU DIED (╮°-°)╮┳━━┳ .......... ( ╯°□°)╯ ┻━━┻");
			score.setTextFill(Color.RED);
		}
	}

	public void drawScore(int value) {
		score.setText("Score: " + value);
	}

	@Override
	public Scene getScene() {
		return scene;
	}
}