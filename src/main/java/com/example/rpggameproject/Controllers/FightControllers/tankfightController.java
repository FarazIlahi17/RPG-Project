package com.example.rpggameproject.Controllers.FightControllers;

import com.example.rpggameproject.TankGameProcess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

import java.io.IOException;
import java.util.concurrent.Executors;

public class tankfightController implements TankGameProcess {
    @FXML
    private ImageView enemy_img;
    @FXML
    private ImageView player_img;
    @FXML
    private ImageView bullet;
    @FXML
    private ImageView shield;
    @FXML
    private ImageView white_flag;
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
    private Label playerHeal_label;
    @FXML
    private Label enemyHeal_label;


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
                        if(isTankDead()){
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

                    case "getRidOfBulletImage":
                        bullet.setOpacity(0);
                        break;

                    case "updateEnemyHpBar":
                        enemyhpBar.setProgress(setEnemyhpBar());
                        break;
                    case "doEnemyHeal":
                        enemyHeal_label.setOpacity(1);
                        break;
                    case "fixHeal":
                        playerHeal_label.setOpacity(0);
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
                    case "resetBlock":
                        enemy_img.setLayoutX(1100);
                        shield.setOpacity(0);
                        attack_btn.setLayoutX(350);
                        heal_btn.setLayoutX(900);
                        enemyhpBar.setOpacity(1);
                        break;
                    case "block":
                        shield.setOpacity(1);
                        enemy_img.setLayoutX(700);
                        enemyhpBar.setOpacity(0);
                        break;

                    case "bringBackAttackButton":
                        attack_btn.setLayoutX(350);
                        break;
                    case "bringBackHealButton":
                        heal_btn.setLayoutX(900);
                        break;

                    case "endGame":
                        attack_btn.setLayoutX(10000);
                        heal_btn.setLayoutX(10000);
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
                    case "runAway":
                        for(int i = 200; i >= -300; i-=5){
                            white_flag.setLayoutX(i);
                            player_img.setLayoutX(i-150);
                            try {
                                Thread.sleep(5);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        break;
                    case "killPlayer":
                        player_img.setRotate(180);
                        white_flag.setOpacity(1);
                        white_flag.setLayoutX(200);
                        player_img.setLayoutX(50);
                        hpBar.setOpacity(0);
                        delay(1,"runAway");
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + fcn);
                }
            }
        });
    }
    public void animate(String fcn){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                switch (fcn){
                    case "bulletAnimation":
                        for(int i = 350; i <= 1200; i+=5){
                            bullet.setLayoutX(i);
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
            }

        });
    }
    public void enemyChoice(){
        if((int)(Math.random() * 11) <= 7){
            if(isBlocked()){
                delay(1.2,"block");
                delay(2.2,"resetBlock");
                return;
            }
            runEnemyDamage();
            delay(1.2,"runEnemyAttack");
        }
        else {
            runEnemyHeal();
            delay(1.2,"runEnemyHeal");
        }
    }
    public void onAttackButtonClicked(){

        bullet.setOpacity(1);
        bullet.setLayoutX(350);
        attack_btn.setLayoutX(10000);
        heal_btn.setLayoutX(10000);
        basicAttack();
        animate("bulletAnimation");
        delay(1.1,"updateEnemyHpBar");
        delay(1.1,"getRidOfBulletImage");
        if(isEnemyDead()){
            delay(3,"endGame");
            delay(2,"killEnemy");
            return;
        }
        enemyChoice();
        if(isTankDead()){
            delay(6,"endGame");
            delay(5,"killPlayer");
        }

    }

    public void onHealButtonClicked(){
        attack_btn.setLayoutX(10000);
        heal_btn.setLayoutX(10000);
        heal();
        hpBar.setProgress(sethpBar());
        playerHeal_label.setOpacity(1);
        delay(1, "fixHeal");
        enemyChoice();
        if (isTankDead()) {
            delay(5.5, "endGame");
            delay(4.5, "killPlayer");
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




