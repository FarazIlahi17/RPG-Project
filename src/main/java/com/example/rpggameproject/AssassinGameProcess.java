package com.example.rpggameproject;

import com.example.rpggameproject.Characters.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public interface AssassinGameProcess {
                                            //method to switch scene based on given parameter
    default void switchScene(ActionEvent event, String new_scene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(new_scene + ".fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1600, 800);
        stage.setScene(scene);
        stage.show();
    }

    Enemy enemy = new Enemy();
    Assassin assassin = new Assassin();


    default boolean isEnemyDead(){              //all methods for enemy functions
        return enemy.getHp() < 1;
    }

    default double setEnemyhpBar(){
        return 1 - (enemy.getHp() / (double)enemy.getMax_hp());
    }
    default void runEnemyHeal(){
        enemy.setheal();
    }

    default String getEnemyHp(){
        return enemy.toString();
    }
    default void runEnemyDamage(){
        assassin.takeDamage(enemy.getBasic_attack());
    }


    default void basicAttack(){
        if((int)(Math.random() * 101) <= assassin.getCrit_chance()){
            enemy.takeDamage((int)(assassin.getBasic_attack() * 5));
            assassin.resetBar();                                                 //all methods for assassin functions
            assassin.setisCrit(true);
        }
        else {
            enemy.takeDamage(assassin.getBasic_attack());
            assassin.updateBar();
        }
    }
    default void resetisCrit(){
        assassin.setisCrit(false);
    }
    default boolean isCrit(){
        return assassin.getisCrit();
    }
    default void heal(){
        assassin.setheal();
        assassin.updateBar();
        if(assassin.getHp() > assassin.getMax_hp()){
            assassin.resetHp();
        }
    }
    default double sethpBar(){
        return 1 - (assassin.getHp() / (double)assassin.getMax_hp());
    }
    default boolean isAssassinDead(){
        return assassin.getHp() < 1;
    }


}