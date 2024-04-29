package com.example.rpggameproject.Controllers.FightControllers;


import com.example.rpggameproject.AssassinGameProcess;
import com.example.rpggameproject.KnightGameProcess;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.concurrent.Executors;

public class knightfightController implements KnightGameProcess {
    public ImageView enemy_img;
    public ImageView player_img;
    public Button attack_btn;
    public Button heal_btn;
    public Button special_btn;

    public Button endGame_btn;
    public ProgressBar hpBar;
    public ProgressBar chargebar;
    public ProgressBar enemyhpBar;
    public Label special_label;
    public Label playerHeal_label;
    public Label enemyHeal_label;


    public void delay(long seconds, String fcn) {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(seconds * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                switch (fcn) {
                    case "setPlayerImage":
                        player_img.setLayoutX(100);
                        hpBar.setOpacity(1);
                        chargebar.setOpacity(1);
                        chargebar.setProgress(setChargeBar());
                        break;
                    case "fixSpecial_Label":
                        special_label.setOpacity(0);
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
                        break;
                    case "bringBackSpecialButton":
                        special_btn.setLayoutX(625);
                        special_btn.setOpacity(1);
                        break;
                    case "bringBackHealButton":
                        heal_btn.setLayoutX(900);
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
                        player_img.setLayoutY(470);
                        hpBar.setOpacity(0);
                        chargebar.setOpacity(0);
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
            delay(2, "doEnemyAttack");
            delay(3, "setEnemyImage");
            if(isKnightDead()){
                return;
            }
            if (getIsCharged()) {
                delay(3, "bringBackSpecialButton");
            }
            delay(3, "bringBackAttackButton");
            delay(3, "bringBackHealButton");
        }
        else {
            delay(2, "doEnemyHeal");
            delay(3, "fixEnemyHeal");
            if (getIsCharged()) {
                delay(3, "bringBackSpecialButton");
            }
            delay(3, "bringBackAttackButton");
            delay(3, "bringBackHealButton");
        }
    }

    public void onAttackButtonClicked(ActionEvent event) throws IOException {
        attack_btn.setLayoutX(10000);
        heal_btn.setLayoutX(10000);
        special_btn.setLayoutX(10000);
        player_img.setLayoutX(1000);
        hpBar.setOpacity(0);
        chargebar.setOpacity(0);
        basicAttack();
        enemyhpBar.setProgress(setEnemyhpBar());
        delay(1, "setPlayerImage");
        doEnemyAttack();
        if (isEnemyDead()) {
            delay(2,"endGame");
            delay(2,"killEnemy");
        }
        if (isKnightDead()) {
            delay(3, "endGame");
            delay(3, "killPlayer");

        }
    }

    public void onHealButtonClicked(ActionEvent event) throws IOException {
        attack_btn.setLayoutX(10000);
        heal_btn.setLayoutX(10000);
        special_btn.setLayoutX(10000);
        heal();
        hpBar.setProgress(sethpBar());
        chargebar.setProgress(setChargeBar());
        playerHeal_label.setOpacity(1);
        delay(1, "fixHeal");
        doEnemyAttack();
        if (isKnightDead()) {
            delay(3, "endGame");
            delay(3, "killPlayer");

        }
    }

    public void onSpecialButtonClicked(ActionEvent event) throws IOException {
        attack_btn.setLayoutX(10000);
        heal_btn.setLayoutX(10000);
        player_img.setLayoutX(1000);
        hpBar.setOpacity(0);
        chargebar.setOpacity(0);
        special_btn.setOpacity(0);
        knightSpecialAttack();
        special_label.setOpacity(1);
        delay(1,"fixSpecial_Label");
        enemyhpBar.setProgress(setEnemyhpBar());
        delay(1, "setPlayerImage");
        doEnemyAttack();
        if (isEnemyDead()) {
            delay(2,"endGame");
            delay(2,"killEnemy");
        }
        if (isKnightDead()) {
            delay(3, "endGame");
            delay(3, "killPlayer");

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






