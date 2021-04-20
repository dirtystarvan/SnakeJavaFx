package com.ac1dtest.snakegame.controllers;

import com.ac1dtest.snakegame.model.GameModel;
import com.ac1dtest.snakegame.view.Graphics;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        int width = 15;
        int height = 15;

        GameModel snakeGame = new GameModel(width, height);

        Graphics view = new Graphics(width, height);

        GameController gc = new GameController(view, snakeGame);

        Scene mainScene = view.getScene();
        mainScene.setOnKeyPressed(new KeyboardListener(snakeGame, gc));

        primaryStage.setScene(mainScene);
        primaryStage.setResizable(false);

        primaryStage.show();

        gc.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}