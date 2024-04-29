package com.example.rpggameproject;

import com.example.rpggameproject.Characters.Assassin;
import com.example.rpggameproject.Characters.Enemy;
import com.example.rpggameproject.Characters.Knight;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public interface KnightGameProcess {
                                            //method to switch scene based on given parameter
    default void switchScene(ActionEvent event, String new_scene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(new_scene + ".fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1600, 800);
        stage.setScene(scene);
        stage.show();
    }

    Enemy enemy = new Enemy();
    Knight knight = new Knight();


    default boolean isEnemyDead(){              //all methods for enemy functions
        return enemy.getHp() < 1;
    }

    default double setEnemyhpBar(){
        return 1 - (enemy.getHp() / (double)enemy.getMax_hp());
    }
    default void runEnemyHeal(){
        enemy.setheal();
    }
    default void runEnemyDamage(){
        knight.takeDamage(enemy.getBasic_attack());
    }

    default void basicAttack(){
        enemy.takeDamage(knight.getBasic_attack());
        knight.updateBar();
    }
    default void knightSpecialAttack(){
        enemy.takeDamage(knight.getSpecial_attack());
        knight.resetBar();
    }
    default void heal(){
        knight.setheal();
        knight.updateBar();
        if(knight.getHp() > knight.getMax_hp()){
            knight.resetHp();
        }
    }


    default double sethpBar(){
        return 1 - (knight.getHp() / (double)knight.getMax_hp());
    }
    default double setChargeBar(){
        return (knight.getCharge_bar() / 100.0);
    }
    default boolean getIsCharged(){
        return knight.getIsCharged();
    }

    default boolean isKnightDead(){
        return knight.getHp() < 1;
    }


}