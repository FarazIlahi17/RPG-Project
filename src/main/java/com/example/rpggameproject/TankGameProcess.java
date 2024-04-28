package com.example.rpggameproject;

import com.example.rpggameproject.Characters.Assassin;
import com.example.rpggameproject.Characters.Enemy;
import com.example.rpggameproject.Characters.Tank;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public interface TankGameProcess {
                                            //method to switch scene based on given parameter
    default void switchScene(ActionEvent event, String new_scene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(new_scene + ".fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1600, 800);
        stage.setScene(scene);
        stage.show();
    }

    Enemy enemy = new Enemy();

    Tank tank = new Tank();
    default boolean isEnemyDead(){              //all methods for enemy functions
        return enemy.getHp() < 1;
    }
    default boolean enemyisAttacking(){
        return enemy.getisAttacking();
    }
    default double setEnemyhpBar(){
        return 1 - (enemy.getHp() / (double)enemy.getMax_hp());
    }
    default void runEnemyTurn(){
        if((int)(Math.random() * 11) <= 7){
            tank.takeDamage(enemy.getBasic_attack());
            enemy.setAttacking(true);
        }
        else {
            enemy.setheal();
            enemy.setAttacking(false);
        }
    }


    default void basicAttack(){
        enemy.takeDamage(tank.getBasic_attack());
        tank.updateBar();
    }
    default void heal(){
        tank.setheal();
        tank.updateBar();
        if(tank.getHp() > tank.getMax_hp()){
            tank.resetHp();
        }
    }
    default double sethpBar(){
        return 1 - (tank.getHp() / (double)tank.getMax_hp());
    }
    default boolean isTankDead(){
        return tank.getHp() < 1;
    }


}