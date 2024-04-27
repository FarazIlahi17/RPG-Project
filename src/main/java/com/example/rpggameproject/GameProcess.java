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

    default boolean isPlayerDead(){
        return assassin.getHp() < 1;
    }
    default boolean isEnemyDead(){
        return enemy.getHp() < 1;
    }

    default void assassinBasicAttack(){
        if((int)(Math.random() * 101) <= assassin.getCrit_chance()){
            enemy.takeDamage((int)(assassin.getBasic_attack() * 1.5));
            assassin.resetBar();
            assassin.setisCrit(true);
        }
        else {
            enemy.takeDamage(assassin.getBasic_attack());
            assassin.updateBar();
        }

        System.out.println("Enemy hp is: " + enemy.getHp());
    }
    default void resetisCrit(){
        assassin.setisCrit(false);
    }

    default boolean isCrit(){
        return assassin.getisCrit();
    }

    default void heal(){
        assassin.setheal();
        assassin.resetBar();
        System.out.println("you healed an now ur hp is: " + assassin.getHp());

    }
    default double setEnemyhpBar(){
        return enemy.getHp() / (double)enemy.getMax_hp();
    }
    default double sethpBar(){
        return assassin.getHp() / (double)assassin.getMax_hp();
    }

    default void runEnemyTurn(){
        if((int)(Math.random() * 11) <= 7){
            assassin.takeDamage(enemy.getBasic_attack());
            enemy.setAttacking(true);

        }
        else {
            enemy.setheal();
            enemy.setAttacking(false);

        }
        if(enemyisAttacking()){
            System.out.println("Enemy attacked and now playerhp is: " + assassin.getHp());

        }
        else {
            System.out.println("Enemy healed and hp is: " + enemy.getHp());

        }
    }
    default boolean enemyisAttacking(){
        return enemy.getisAttacking();
    }


}