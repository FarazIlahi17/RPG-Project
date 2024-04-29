package com.example.rpggameproject.Controllers.FightControllers;


import com.example.rpggameproject.AssassinGameProcess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.concurrent.Executors;

import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
public class assassinfightController implements AssassinGameProcess {
    @FXML
    private ImageView enemy_img;
    @FXML
    private ImageView player_img;
    @FXML
    private Button attack_btn;
    @FXML
    private Button heal_btn;
    @FXML

    private Button endGame_btn;
    @FXML
    private ProgressBar hpBar;
    @FXML
    private ProgressBar enemyhpBar;
    @FXML
    private Label crit_label;
    @FXML
    private Label playerHeal_label;
    @FXML
    private Label enemyHeal_label;
    @FXML
    private Label enemyHp_label;
    @FXML
    private Rectangle enemyHp_rect;

    public void delay(double seconds, String fcn){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    long milles = (long) (seconds * 1000);
                    Thread.sleep(milles);
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                switch(fcn) {
                    case "runEnemyAttack":
                        animate("enemyGoForwardAnimation");
                        animate("enemyAttackAnimationp1");
                        animate("enemyAttackAnimationp2");
                        animate("enemyGoBackAnimation");
                        delay(3,"putBackEnemyHpBar");
                        delay(3,"unRotateEnemy");
                        if(isAssassinDead()){
                            return;
                        }
                        delay(3.5, "bringBackAttackButton");
                        delay(3.5, "bringBackHealButton");
                        break;
                    case "runEnemyHeal":
                        delay(1,"doEnemyHeal");
                        delay(1,"updateEnemyHpBar");
                        delay(2,"getRidOfEnemyHeal");
                        delay(2,"bringBackAttackButton");
                        delay(2,"bringBackHealButton");
                        break;
                    case "setPlayerImage":
                        player_img.setLayoutX(100);
                        hpBar.setOpacity(1);
                        break;
                    case "fixCritLabel":
                        crit_label.setOpacity(0);
                        break;
                    case "fixHeal":
                        playerHeal_label.setOpacity(0);
                        break;

                    case "updateEnemyHpBar":
                        enemyhpBar.setProgress(setEnemyhpBar());
//                        enemyHp_label.setText("too much");
                        break;
                    case "setEnemyImage":
                        enemy_img.setLayoutX(1100);
                        enemyhpBar.setOpacity(1);
                        break;

                    case "doEnemyHeal":
                        enemyHeal_label.setOpacity(1);
                        break;
                    case "getRidOfEnemyHeal":
                        enemyHeal_label.setOpacity(0);
                        break;
                    case "putBackEnemyHpBar":
                        enemyhpBar.setOpacity(1);
                        break;
                    case "unRotateEnemy":
                        enemy_img.setRotate(0);
                        break;

                    case "endGame":
                        attack_btn.setLayoutX(10000);
                        heal_btn.setLayoutX(10000);
                        endGame_btn.setLayoutX(575);
                        endGame_btn.setOpacity(1);
                        break;
                    case "bringBackAttackButton":
                        attack_btn.setLayoutX(350);
                        break;
                    case "bringBackHealButton":
                        heal_btn.setLayoutX(900);
                        break;
                    case "killEnemy":
                        enemy_img.setRotationAxis(Rotate.Z_AXIS);
                        enemy_img.setRotate(90);
                        enemy_img.setLayoutX(1200);
                        enemy_img.setLayoutY(400);
                        enemyhpBar.setOpacity(0);
                        break;
                    case "killPlayer":
                        player_img.setRotate(270);
                        player_img.setLayoutX(90);
                        player_img.setLayoutY(500);
                        hpBar.setOpacity(0);
                        break;
                }
            }
        });
    }
    public void animate(String fcn){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                switch (fcn){
                    case "enemyGoForwardAnimation":
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        enemyhpBar.setOpacity(0);
                        for(int i = 1100; i >= 300; i-=10){
                            enemy_img.setLayoutX(i);
                            try {
                                Thread.sleep(5);
                            }
                            catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        break;
                    case "enemyAttackAnimationp1":
                        try {
                            Thread.sleep(1750);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        hpBar.setProgress(sethpBar());
                        for(int i = 300; i >= 150; i-=10){
                            enemy_img.setLayoutX(i);
                            try {
                                Thread.sleep(5);
                            }
                            catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        break;
                    case "enemyAttackAnimationp2":
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        hpBar.setProgress(sethpBar());
                        for(int i = 150; i <= 300; i+=10){
                            enemy_img.setLayoutX(i);
                            try {
                                Thread.sleep(5);
                            }
                            catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        break;
                    case "enemyGoBackAnimation":
                        try {
                            Thread.sleep(2500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        enemy_img.setRotate(180);
                        hpBar.setProgress(sethpBar());
                        for(int i = 300; i <= 1100; i+=10){
                            enemy_img.setLayoutX(i);
                            try {
                                Thread.sleep(5);
                            }
                            catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        break;
                }
            }

        });
    }
    public void enemyChoice(){
        if((int)(Math.random() * 11) <= 7){
            runEnemyDamage();
            delay(1,"runEnemyAttack");
        }
        else {
            runEnemyHeal();
            delay(1.1,"runEnemyHeal");
        }
    }

    public void onAttackButtonClicked(ActionEvent event) throws IOException {
        attack_btn.setLayoutX(10000);
        heal_btn.setLayoutX(10000);
        player_img.setLayoutX(1000);
        hpBar.setOpacity(0);
        basicAttack();
        if(isCrit()){
            crit_label.setOpacity(1);
            delay(1,"fixCritLabel");
            resetisCrit();
        }
        enemyhpBar.setProgress(setEnemyhpBar());
        delay(1,"setPlayerImage");
        if(isEnemyDead()){
            delay(3,"endGame");
            delay(2,"killEnemy");
            return;
        }
        enemyChoice();
        if(isAssassinDead()){
            delay(5.2,"endGame");
            delay(4.2,"killPlayer");
        }
    }

    public void onHealButtonClicked(ActionEvent event) throws IOException {
        attack_btn.setLayoutX(10000);
        heal_btn.setLayoutX(10000);
        heal();
        hpBar.setProgress(sethpBar());
        playerHeal_label.setOpacity(1);
        delay(1,"fixHeal");
        delay(1,"doEnemyAttack");
        enemyChoice();
        if(isAssassinDead()){
            delay(5.4,"endGame");
            delay(4.2,"killPlayer");        }
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




