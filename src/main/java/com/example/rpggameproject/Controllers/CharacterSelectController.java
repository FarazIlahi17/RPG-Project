package com.example.rpggameproject.Controllers;

import com.example.rpggameproject.Information;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class CharacterSelectController implements Information {
    @FXML
    ImageView knight;
    @FXML
    ImageView tank;
    @FXML
    ImageView assassin;
    @FXML
    ImageView mage;

    Image knight_img = new Image("knight.png");


    public void switchToGameScene(ActionEvent event) throws IOException {
        Information.super.switchScene(event, "Game");
    }
    public void uploadAssets(ActionEvent event) throws IOException{
        knight.setImage(knight_img);
    }

}



