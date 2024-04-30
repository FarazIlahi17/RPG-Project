package com.example.rpggameproject;

import com.example.rpggameproject.Characters.Assassin;
import com.example.rpggameproject.Characters.Character;
import com.example.rpggameproject.Characters.Enemy;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public interface ControlEndGame {
                                            //method to switch scene based on given parameter
    default void switchScene(ActionEvent event, String new_scene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(new_scene + ".fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1600, 800);
        stage.setScene(scene);
        stage.show();
    }
    Assassin player = new Assassin();
    default void setName(String name){
        player.setName(name);
    }
    default String getStatsWin(){
        return (player.toString() + "winner!\nThe " + player.getId() + " was used to defeat the enemy.");
    }
    default String getStatsLose(){
        return (player.toString() + "Loser!\nThe " + player.getId() + " was used to succumb to the enemy.");
    }
    default void setPlayer(String i){
        player.setId(i);
    }


}