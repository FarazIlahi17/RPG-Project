package com.example.rpggameproject.Controllers;

import com.example.rpggameproject.Characters.Assassin;
import com.example.rpggameproject.GameProcess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.io.IOException;



public class CharacterSelectController implements GameProcess {


    @FXML
    ImageView characterSelectBackground;
    @FXML
    ImageView knight_img;
    @FXML
    ImageView tank_img;
    @FXML
    ImageView assassin_img;
    @FXML
    ImageView mage_img;


    @FXML
    Button knight_btn;
    @FXML
    Button tank_btn;
    @FXML
    Button mage_btn;
    @FXML
    Button assassin_btn;






    public void createKnight(ActionEvent event) throws IOException {
        switchScene(event, "Game");

    }

    public void createTank(ActionEvent event) throws IOException {
        switchScene(event, "Game");


    }

    public void createMage(ActionEvent event) throws IOException {
        switchScene(event, "Game");


    }
    public void createAssassin(ActionEvent event) throws IOException {
       switchScene(event, "assassinfight");
       GameProcess.super.setAssassin();
    }

}



