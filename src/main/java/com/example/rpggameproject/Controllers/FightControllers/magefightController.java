package com.example.rpggameproject.Controllers.FightControllers;


import com.example.rpggameproject.MageGameProcess;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.concurrent.Executors;

public class magefightController implements MageGameProcess {
    public ImageView enemy_img;
    public ImageView player_img;
    public ImageView fireball;
    public ImageView laser;
    public Button attack_btn;
    public Button heal_btn;
    public Button special_btn;
    public Button endGame_btn;
    public ProgressBar hpBar;
    public ProgressBar manaBar;
    public ProgressBar enemyhpBar;
    public Label special_label;
    public Label playerHeal_label;
    public Label enemyHeal_label;


    public void delay(double fraction, String fcn) {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                if(fcn.equals("fireBallAnimation")){
                    for(int i = 300; i <= 1200; i+=5){
                        fireball.setLayoutX(i);
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                else {
                    try {
                        long milles = (long)(fraction * 1000);
                        Thread.sleep(milles);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                switch (fcn) {
                    case "laserAnimation":
                        laser.setOpacity(0);
                        break;
                    case "setEnemyHpBar":
                        enemyhpBar.setProgress(setEnemyhpBar());
                        break;
                    case "setFireballImg":
                        fireball.setOpacity(0);
                        break;
                    case "fixSpecial_Label":
                        special_label.setOpacity(0);
                        break;
                    case "setSpecialButton":
                        special_btn.setOpacity(1);
                        break;
                    case "fixHeal":
                        playerHeal_label.setOpacity(0);
                        break;
                    case "doEnemyAttack":
                        hpBar.setProgress(sethpBar());
                        enemy_img.setLayoutX(300);
                        enemyhpBar.setOpacity(0);
                        break;
                    case "setEnemyImage":
                        enemy_img.setLayoutX(1100);
                        enemyhpBar.setOpacity(1);
                        break;
                    case "fixEnemyHeal":
                        enemyHeal_label.setOpacity(0);
                        break;
                    case "doEnemyHeal":
                        enemyHeal_label.setOpacity(1);
                        enemyhpBar.setProgress(setEnemyhpBar());
                        break;
                    case "bringBackAttackButton":
                        attack_btn.setLayoutX(350);
                        attack_btn.setOpacity(1);
                        break;
                    case "bringBackSpecialButton":
                        special_btn.setLayoutX(625);
                        special_btn.setOpacity(1);
                        break;
                    case "bringBackHealButton":
                        heal_btn.setLayoutX(900);
                        heal_btn.setOpacity(1);
                        break;
                    case "endGame":
                        attack_btn.setLayoutX(10000);
                        heal_btn.setLayoutX(10000);
                        special_btn.setLayoutX(10000);
                        endGame_btn.setLayoutX(575);
                        endGame_btn.setOpacity(1);
                        break;
                    case "killEnemy":
                        enemy_img.setRotate(90);
                        enemy_img.setLayoutX(1200);
                        enemy_img.setLayoutY(400);
                        enemyhpBar.setOpacity(0);
                        break;
                    case "killPlayer":
                        player_img.setRotate(270);
                        player_img.setLayoutX(50);
                        player_img.setLayoutY(450);
                        hpBar.setOpacity(0);
                        manaBar.setOpacity(0);
                        break;
                }
            }
        });
    }
    public void doEnemyAttack(){
        if(isEnemyDead()){
            return;
        }
        runEnemyTurn();
        if (enemyisAttacking()) {
            delay(3, "doEnemyAttack");
            delay(4, "setEnemyImage");
            if(isMageDead()){
                return;
            }
            if (canSpecialAttack()){
                delay(4, "bringBackSpecialButton");
                delay(4, "bringBackAttackButton");
            }
            else if (canBasicAttack()){
                delay(4, "bringBackAttackButton");
            }
            delay(4, "bringBackHealButton");

        }
        else {
            delay(3, "doEnemyHeal");
            delay(4, "fixEnemyHeal");
            if (canSpecialAttack()){
                delay(4, "bringBackSpecialButton");
                delay(4, "bringBackAttackButton");
            }
            else if (canBasicAttack()){
                delay(4, "bringBackAttackButton");
            }
            delay(4, "bringBackHealButton");
        }
    }

    public void onAttackButtonClicked(){
        attack_btn.setLayoutX(10000);
        heal_btn.setLayoutX(10000);
        special_btn.setLayoutX(10000);
        fireball.setOpacity(1);
        fireball.setLayoutX(400);
        basicAttack();
        manaBar.setProgress(setManaBar());
        delay(1.1, "fireBallAnimation");
        delay(1.1,"setEnemyHpBar");
        delay(1.1,"setFireballImg");
        doEnemyAttack();
        if (isEnemyDead()) {
            delay(2,"endGame");
            delay(2,"killEnemy");
        }
        if (isMageDead()) {
            delay(4,"endGame");
            delay(4,"killPlayer");
        }
    }

    public void onHealButtonClicked(){
        attack_btn.setLayoutX(10000);
        heal_btn.setLayoutX(10000);
        special_btn.setLayoutX(10000);
        heal();
        hpBar.setProgress(sethpBar());
        manaBar.setProgress(setManaBar());
        playerHeal_label.setOpacity(1);
        delay(1, "fixHeal");
        doEnemyAttack();
        if (isMageDead()) {
            delay(4,"endGame");
            delay(4,"killPlayer");
        }
    }

    public void onSpecialButtonClicked(){
        attack_btn.setLayoutX(10000);
        heal_btn.setLayoutX(10000);
        special_btn.setLayoutX(10000);
        laser.setOpacity(1);
        mageSpecialAttack();
        manaBar.setProgress(setManaBar());
        delay(1,"laserAnimation");
        delay(1,"setEnemyHpBar");
        doEnemyAttack();
        if (isEnemyDead()) {
            delay(2,"endGame");
            delay(2,"killEnemy");
                    }
        if (isMageDead()) {
            delay(4,"endGame");
            delay(4,"killPlayer");
        }
    }
    public void onEndGameButtonClicked(ActionEvent event) throws IOException {
        if(isEnemyDead()){
            switchScene(event, "winEndScreen");
        }
        else {
            switchScene(event, "loseEndScreen");
        }
    }

}




