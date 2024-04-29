package com.example.rpggameproject.Controllers.FightControllers;

import com.example.rpggameproject.TankGameProcess;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

import java.io.IOException;
import java.util.concurrent.Executors;

public class tankfightController implements TankGameProcess {
    public ImageView enemy_img;
    public ImageView player_img;
    public ImageView bullet;
    public ImageView shield;
    public ImageView white_flag;
    public Button attack_btn;
    public Button heal_btn;

    public Button endGame_btn;
    public ProgressBar hpBar;
    public ProgressBar enemyhpBar;
    public Label playerHeal_label;
    public Label enemyHeal_label;


    public void delay(double seconds, String fcn){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                if(fcn.equals("bulletAnimation")){
                    for(int i = 350; i <= 1200; i+=5){
                        bullet.setLayoutX(i);
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                else {
                    try {
                        long milles = (long) (seconds * 1000);
                        Thread.sleep(milles);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                switch(fcn) {
                    case "doEnemyAttack":
                        runEnemyTurn();
                        if(enemyisAttacking()) {
                            if(isBlocked()){
                                delay(1,"block");
                                delay(2,"resetBlock");
                            }
                            else {
                                runEnemyDamage();
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
                            }
                        }
                        else {
                            delay(1,"doEnemyHeal");
                            delay(1,"updateEnemyHpBar");
                            delay(2,"fixEnemyHeal");
                            delay(2,"bringBackAttackButton");
                            delay(2,"bringBackHealButton");
                        }
                        break;

                    case "setBulletImage":
                        bullet.setOpacity(0);
                        break;

                    case "updateEnemyHpBar":
                        enemyhpBar.setProgress(setEnemyhpBar());
                        break;
                    case "doEnemyHeal":
                        enemyHeal_label.setOpacity(1);
                        System.out.println("Enemy hp after is: " + setEnemyhpBar());
                        break;
                    case "fixHeal":
                        playerHeal_label.setOpacity(0);
                        break;
                    case "fixEnemyHeal":
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

    public void doEnemyAttack(){
        if(isEnemyDead()){
            return;
        }
        delay(1.2,"doEnemyAttack");
    }

    public void onAttackButtonClicked(){

        bullet.setOpacity(1);
        bullet.setLayoutX(350);
        attack_btn.setLayoutX(10000);
        heal_btn.setLayoutX(10000);
        basicAttack();
        System.out.println("Enemy hp b4 is: " + setEnemyhpBar());
        animate("bulletAnimation");
        delay(1.1,"updateEnemyHpBar");
        delay(1.1,"setBulletImage");
        doEnemyAttack();
        if(isEnemyDead()){
            delay(2,"endGame");
            delay(2,"killEnemy");
        }
        if(isTankDead()){
            delay(4,"endGame");
            delay(4,"killPlayer");
        }

    }

    public void onHealButtonClicked(){
        attack_btn.setLayoutX(10000);
        heal_btn.setLayoutX(10000);
        heal();
        hpBar.setProgress(sethpBar());
        playerHeal_label.setOpacity(1);
        delay(1, "fixHeal");
        doEnemyAttack();
        if (isTankDead()) {
            delay(4, "endGame");
            delay(4, "killPlayer");
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




