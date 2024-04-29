package com.example.rpggameproject.Controllers;

import com.example.rpggameproject.AssassinGameProcess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.io.IOException;



public class CharacterSelectController implements AssassinGameProcess {


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
        switchScene(event, "knightfight");
    }

    public void createTank(ActionEvent event) throws IOException {
        switchScene(event, "tankfight");
    }

    public void createMage(ActionEvent event) throws IOException {
        switchScene(event, "magefight");
    }
    public void createAssassin(ActionEvent event) throws IOException {
       switchScene(event, "assassinfight");
    }

}



