package com.example.rpggameproject.Controllers;

import com.example.rpggameproject.AssassinGameProcess;
import com.example.rpggameproject.ControlEndGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.io.IOException;



public class CharacterSelectController implements ControlEndGame {


    @FXML
    private ImageView characterSelectBackground;
    @FXML
    private ImageView knight_img;
    @FXML
    private ImageView tank_img;
    @FXML
    private ImageView assassin_img;
    @FXML
    private ImageView mage_img;


    @FXML
    Button knight_btn;
    @FXML
    Button tank_btn;
    @FXML
    Button mage_btn;
    @FXML
    Button assassin_btn;






    public void createKnight(ActionEvent event) throws IOException {
        setPlayer("knight");
        switchScene(event, "knightfight");
    }

    public void createTank(ActionEvent event) throws IOException {
        setPlayer("tank");
        switchScene(event, "tankfight");
    }
    public void createMage(ActionEvent event) throws IOException {
        setPlayer("mage");
        switchScene(event, "magefight");
    }
    public void createAssassin(ActionEvent event) throws IOException {
        setPlayer("assassin");
       switchScene(event, "assassinfight");
    }

}



