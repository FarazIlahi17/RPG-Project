package com.example.rpggameproject;

import com.example.rpggameproject.Characters.*;
import com.example.rpggameproject.Characters.Character;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public interface GameProcess {

    Enemy enemy = new Enemy();
    Knight knight = new Knight();
    Tank tank = new Tank();
    Mage mage = new Mage();
    Assassin assassin = new Assassin();

    String player = null;




    public default void setPlayer(String name){
        assassin.setHp(enemy.getBasic_attack());
        System.out.println(assassin.getHp());

    }




}