package com.example.rpggameproject.Controllers;

import com.example.rpggameproject.Characters.Knight;
import com.example.rpggameproject.Characters.Tank;
import com.example.rpggameproject.Characters.Mage;
import com.example.rpggameproject.Characters.Assassin;
import com.example.rpggameproject.Characters.Enemy;
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






    public void createKnight(ActionEvent event) throws IOException {
        Information.super.switchScene(event, "Game");
        Knight player = new Knight();
        System.out.println("You made an knight");
    }

    public void createTank(ActionEvent event) throws IOException {
        Information.super.switchScene(event, "Game");
        Tank player = new Tank();
        System.out.println("You made an tank");
    }

    public void createMage(ActionEvent event) throws IOException {
        Information.super.switchScene(event, "Game");
        Mage player = new Mage();
        System.out.println("You made an mage");
    }
    public void createAssassin(ActionEvent event) throws IOException {
        Information.super.switchScene(event, "Game");
        Assassin player = new Assassin();
        System.out.println("You made an assassin");

    }

}



