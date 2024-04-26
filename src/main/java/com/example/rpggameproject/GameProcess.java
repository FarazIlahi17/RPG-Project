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

    public default void switchScene(ActionEvent event, String new_scene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(new_scene + ".fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1600, 800);
        stage.setScene(scene);
        stage.show();
    }

    Enemy enemy = new Enemy();
    Knight knight = new Knight();
    Tank tank = new Tank();
    Mage mage = new Mage();
    Assassin assassin = new Assassin();

    Character p1 = null;


    default void setAssassin(){
        System.out.println("You made an assassin");
    }
    default void assassinBasicAttack(){
        if((int)(Math.random() * 101) <= assassin.getCrit_chance()){
            enemy.takeDamage((int)(assassin.getBasic_attack() * 1.5));
            assassin.resetBar();
        }
        else {
            enemy.takeDamage(assassin.getBasic_attack());
            assassin.updateBar();
        }

        assassin.setTurn();
        System.out.println(enemy.getHp());

    }

    default void heal(){
        assassin.setheal();
        System.out.println(assassin.getHp());
    }


    //create methods for attack & set ups for every role





}