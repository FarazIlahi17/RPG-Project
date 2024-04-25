package com.example.rpggameproject.Controllers;

import com.example.rpggameproject.Information;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    @FXML
    Button knight_btn;
    @FXML
    Button tank_btn;
    @FXML
    Button mage_btn;
    @FXML
    Button assassin_btn;





    public void switchToGameScene(ActionEvent event) throws IOException {
        Information.super.switchScene(event, "Game");
    }

}



