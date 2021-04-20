package com.ac1dtest.snakegame.view;

import com.ac1dtest.snakegame.model.GameObject;
import javafx.scene.Scene;
import java.util.ArrayList;

public interface CommonView {
	void drawObject(GameObject object);

	void drawScene(ArrayList<GameObject> objects, int score);

	void drawEnding(boolean win);

	Scene getScene();
}