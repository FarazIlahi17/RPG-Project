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
    private ImageView slash;
    @FXML
    private ImageView background;
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




    public void delay(double seconds, String fcn){
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                long milles = (long) (seconds * 1000);
                Thread.sleep(milles);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            switch(fcn) {
                case "runEnemyAttack":
                    runEnemyDamage();
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
                    runEnemyHeal();
                    delay(1,"doEnemyHeal");
                    delay(1,"updateEnemyHpBar");
                    delay(2,"getRidOfEnemyHeal");
                    delay(2,"bringBackAttackButton");
                    delay(2,"bringBackHealButton");
                    break;

                case "unRotatePlayer":
                    player_img.setRotationAxis(Rotate.Y_AXIS);
                    player_img.setRotate(0);
                    break;
                case "putBackHpBar":
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
                    player_img.setRotationAxis(Rotate.Z_AXIS);
                    player_img.setRotate(270);
                    player_img.setLayoutX(90);
                    player_img.setLayoutY(500);
                    hpBar.setOpacity(0);
                    break;
            }
        });
    }
    public void animate(String fcn){
        Executors.newSingleThreadExecutor().execute(() -> {
            switch (fcn){
                case "assassinGoForwardAnimation":
                    try {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    hpBar.setOpacity(0);
                    for(int i = 100; i <= 900; i+=10){
                        player_img.setLayoutX(i);
                        try {
                            Thread.sleep(5);
                        }
                        catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case "assassinAttackAnimationp1":
                    try {
                        Thread.sleep(1250);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    for(int i = 900; i <= 1100; i+=10){
                        player_img.setLayoutX(i);
                        try {
                            Thread.sleep(5);
                        }
                        catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        enemyhpBar.setProgress(setEnemyhpBar());
                    }
                    break;
                case "assassinAttackAnimationp2":
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    for(int i = 1100; i >= 900; i-=10){
                        player_img.setLayoutX(i);
                        try {
                            Thread.sleep(5);
                        }
                        catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case "assassinGoBackAnimation":
                    try {
                        Thread.sleep(1750);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    player_img.setRotate(180);

                    for(int i = 900; i >= 100; i-=10){
                        player_img.setLayoutX(i);
                        try {
                            Thread.sleep(5);
                        }
                        catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    delay(.5,"putBackHpBar");
                    delay(.2,"unRotatePlayer");
                    break;
                case "assassinCritAttackp1":
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    for(double i = 1; i >= 0; i-=.01){
                        player_img.setOpacity(i);
                        background.setOpacity(i);
                        try {
                            Thread.sleep(1);
                        }
                        catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case "assassinCritAttackp2":
                    try {
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    slash.setOpacity(1);
                    enemyhpBar.setProgress(setEnemyhpBar());
                    break;
                case "assassinCritAttackp3":
                    try {
                        Thread.sleep(3500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    slash.setOpacity(0);
                    for(double i = 1; i <= 1; i+=.05){
                        player_img.setOpacity(i);
                        background.setOpacity(i);
                        try {
                            Thread.sleep(5);
                        }
                        catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case "assassinCritAttackp4":
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    player_img.setRotate(180);

                    for(int i = 900; i >= 100; i-=10){
                        player_img.setLayoutX(i);
                        try {
                            Thread.sleep(5);
                        }
                        catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    delay(.5,"putBackHpBar");
                    delay(.5,"unRotatePlayer");
                    break;


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
                    for(int i = 300; i >= 150; i-=10){
                        enemy_img.setLayoutX(i);
                        try {
                            Thread.sleep(5);
                        }
                        catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        hpBar.setProgress(sethpBar());
                    }
                    break;
                case "enemyAttackAnimationp2":
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
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
        });
    }
    public void enemyChoice(double wait){
        if((int)(Math.random() * 11) <= 7){
            delay(wait,"runEnemyAttack");
        }
        else {
            delay(wait,"runEnemyHeal");
        }
    }
    public void isPlayerDead (double waitTime){
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                Thread.sleep((long)(waitTime * 1000));
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(isAssassinDead()){
                delay(3,"endGame");
                delay(2,"killPlayer");
            }
        });
    }

    public void onAttackButtonClicked(){
        double waitTime_enemyTurn;
        double waitTime_enemyDead;
        attack_btn.setLayoutX(10000);
        heal_btn.setLayoutX(10000);
        hpBar.setOpacity(0);
        basicAttack();
        if(isCrit()){
            waitTime_enemyTurn = 4.5;
            waitTime_enemyDead = 6;
            resetisCrit();
            animate("assassinGoForwardAnimation");
            animate("assassinCritAttackp1");
            animate("assassinCritAttackp2");
            animate("assassinCritAttackp3");
            animate("assassinCritAttackp4");
        }
        else {
            waitTime_enemyTurn = 2.2;
            waitTime_enemyDead = 3.5;
            animate("assassinGoForwardAnimation");
            animate("assassinAttackAnimationp1");
            animate("assassinAttackAnimationp2");
            animate("assassinGoBackAnimation");
        }

        if(isEnemyDead()){
            delay(waitTime_enemyDead + 1,"endGame");
            delay(waitTime_enemyDead,"killEnemy");
            return;
        }
        enemyChoice(waitTime_enemyTurn);
        isPlayerDead(waitTime_enemyDead);
    }

    public void onHealButtonClicked(){
        attack_btn.setLayoutX(10000);
        heal_btn.setLayoutX(10000);
        heal();
        hpBar.setProgress(sethpBar());
        playerHeal_label.setOpacity(1);
        delay(1,"fixHeal");
        enemyChoice(1.2);
        isPlayerDead(3);
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




