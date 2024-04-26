package com.example.rpggameproject;

import com.example.rpggameproject.Characters.Assassin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("start.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1600, 800);
        stage.setScene(scene);
        stage.show();



    }

    public static void main(String[] args) {


        System.out.println((int)((1 - (50.0 / 100)) * 10));
        launch();

    }
}