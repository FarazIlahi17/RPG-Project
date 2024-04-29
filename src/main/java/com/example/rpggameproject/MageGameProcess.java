package com.example.rpggameproject;

import com.example.rpggameproject.Characters.Enemy;
import com.example.rpggameproject.Characters.Knight;
import com.example.rpggameproject.Characters.Mage;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public interface MageGameProcess {
                                            //method to switch scene based on given parameter
    default void switchScene(ActionEvent event, String new_scene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(new_scene + ".fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1600, 800);
        stage.setScene(scene);
        stage.show();
    }

    Enemy enemy = new Enemy();
    Mage mage = new Mage();


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
        mage.takeDamage(enemy.getBasic_attack());
    }

    default void basicAttack(){
        enemy.takeDamage(mage.getBasic_attack());
        mage.updateBar();
    }
    default void mageSpecialAttack(){
        enemy.takeDamage(mage.getSpecial_attack());
        mage.updateBar();
        mage.updateBar();
        mage.updateBar();
        mage.updateBar();
    }
    default boolean canBasicAttack(){
        return (mage.getMana() >= 15);
    }
    default boolean canSpecialAttack(){
        return (mage.getMana() >= 60);
    }
    default void heal(){
        mage.setheal();
        mage.manaHealed();
        if(mage.getHp() > mage.getMax_hp()){
            mage.resetHp();
        }
        if(mage.getMana() > mage.getMax_mana()){
            mage.resetBar();
        }
    }


    default double sethpBar(){
        return 1 - (mage.getHp() / (double)mage.getMax_hp());
    }
    default double setManaBar(){
        return (mage.getMana() / 120.0);
    }


    default boolean isMageDead(){
        return mage.getHp() < 1;
    }


}