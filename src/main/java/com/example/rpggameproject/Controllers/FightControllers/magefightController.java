package com.example.rpggameproject.Controllers.FightControllers;


import com.example.rpggameproject.MageGameProcess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

import java.io.IOException;
import java.util.concurrent.Executors;

public class magefightController implements MageGameProcess {
    @FXML
    private ImageView enemy_img;
    @FXML
    private ImageView player_img;
    @FXML
    private ImageView fireball;
    @FXML
    private ImageView laser;
    @FXML
    private Button attack_btn;
    @FXML
    private Button heal_btn;
    @FXML
    private Button special_btn;
    @FXML
    private Button endGame_btn;
    @FXML
    private ProgressBar hpBar;
    @FXML
    private ProgressBar manaBar;
    @FXML
    private ProgressBar enemyhpBar;
    @FXML
    private Label special_label;
    @FXML
    private Label playerHeal_label;
    @FXML
    private Label enemyHeal_label;


    public void delay(double fraction, String fcn) {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                long milles = (long)(fraction * 1000);
                Thread.sleep(milles);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            switch (fcn) {
                case "runEnemyAttack" :
                    runEnemyDamage();
                    animate("enemyGoForwardAnimation");
                    animate("enemyAttackAnimationp1");
                    animate("enemyAttackAnimationp2");
                    animate("enemyGoBackAnimation");
                    delay(3,"putBackEnemyHpBar");
                    delay(3,"unRotateEnemy");
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
                    break;
                case "runEnemyHeal":
                    runEnemyHeal();
                    delay(1,"doEnemyHeal");
                    delay(1,"updateEnemyHpBar");
                    delay(2,"getRidOfEnemyHeal");
                    delay(2,"bringBackAttackButton");
                    delay(2,"bringBackHealButton");
                    if (canSpecialAttack()){
                        delay(2, "bringBackSpecialButton");
                        delay(2, "bringBackAttackButton");
                    }
                    else if (canBasicAttack()){
                        delay(2, "bringBackAttackButton");
                    }
                    break;

                case "laserAnimation":
                    laser.setOpacity(0);
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
                    enemy_img.setRotationAxis(Rotate.Z_AXIS);
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
        });
    }
    public void animate(String fcn){
        Executors.newSingleThreadExecutor().execute(() -> {
            switch (fcn){
                case "fireBallAnimation" :
                    for(int i = 300; i <= 1200; i+=5){
                        fireball.setLayoutX(i);
                        try {
                            Thread.sleep(5);
                        }
                        catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
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
        });
    }
    public void isPlayerDead (double waitTime){
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                Thread.sleep((long)(waitTime * 1000));
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(isMageDead()){
                delay(3,"endGame");
                delay(2,"killPlayer");
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
    public void onAttackButtonClicked(){
        attack_btn.setLayoutX(10000);
        heal_btn.setLayoutX(10000);
        special_btn.setLayoutX(10000);
        fireball.setOpacity(1);
        fireball.setLayoutX(400);
        basicAttack();
        delay(1.1,"updateEnemyHpBar");
        manaBar.setProgress(setManaBar());
        animate("fireBallAnimation");
        delay(1.1,"setFireballImg");
        if (isEnemyDead()) {
            delay(3,"endGame");
            delay(2,"killEnemy");
            return;
        }
        enemyChoice(1.5);
        isPlayerDead(3);
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
        enemyChoice(1.5);
        isPlayerDead(3);
    }

    public void onSpecialButtonClicked(){
        attack_btn.setLayoutX(10000);
        heal_btn.setLayoutX(10000);
        special_btn.setLayoutX(10000);
        laser.setOpacity(1);
        mageSpecialAttack();
        delay(1.1,"updateEnemyHpBar");
        manaBar.setProgress(setManaBar());
        delay(1,"laserAnimation");
        if (isEnemyDead()) {
            delay(3,"endGame");
            delay(2,"killEnemy");
            return;
        }
        enemyChoice(1.5);
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




