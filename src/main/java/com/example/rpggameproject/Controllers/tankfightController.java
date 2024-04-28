package com.example.rpggameproject.Controllers;


import com.example.rpggameproject.AssassinGameProcess;
import com.example.rpggameproject.TankGameProcess;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.concurrent.Executors;

public class tankfightController implements TankGameProcess {
    public ImageView enemy_img;
    public ImageView player_img;
    public ImageView bullet;
    public Button attack_btn;
    public Button heal_btn;
    public ProgressBar hpBar;
    public ProgressBar enemyhpBar;
    public Label playerHeal_label;
    public Label enemyHeal_label;


    public void delay(double fractions, String fcn){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    long milles = (long) (fractions * 1000);
                    Thread.sleep(milles);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                switch(fcn) {
                    case "bulletAnimation1":
                        bullet.setLayoutX(600);
                        break;
                    case "bulletAnimation2":
                        bullet.setLayoutX(900);
                        break;
                    case "bulletAnimation3":
                        bullet.setLayoutX(1200);
                        break;
                    case "setEnemyHpBar":
                        enemyhpBar.setProgress(setEnemyhpBar());
                        break;
                    case "setBulletImage":
                        bullet.setOpacity(0);
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
                        attack_btn.setLayoutX(350);
                        heal_btn.setLayoutX(900);
                        enemyhpBar.setOpacity(1);
                        break;
                    case "fixEnemyHeal":
                        enemyHeal_label.setOpacity(0);
                        attack_btn.setLayoutX(350);
                        heal_btn.setLayoutX(900);
                        break;
                    case "doEnemyHeal":
                        enemyHeal_label.setOpacity(1);
                        enemyhpBar.setProgress(setEnemyhpBar());
                        break;
                    case "doNothing":
                        break;
                }
            }
        });
    }

    public void onAttackButtonClicked(ActionEvent event) throws IOException {
        bullet.setOpacity(1);
        bullet.setLayoutX(400);
        attack_btn.setLayoutX(10000);
        heal_btn.setLayoutX(10000);
        basicAttack();
        delay(.2,"bulletAnimation1");
        delay(.5,"bulletAnimation2");
        delay(.9,"bulletAnimation3");
        delay(1,"setEnemyHpBar");
        runEnemyTurn();
        delay(1,"setBulletImage");
        if(enemyisAttacking()) {
            delay(2, "doEnemyAttack");
            delay(3, "setEnemyImage");
        }
        else {
            delay(2,"doEnemyHeal");
            delay(3,"fixEnemyHeal");
        }
        if(isTankDead()){
            switchScene(event,"loseEndScreen");
        }
        if(isEnemyDead()){
            switchScene(event,"winEndScreen");
        }
    }

    public void onHealButtonClicked(ActionEvent event) throws IOException {
        attack_btn.setLayoutX(10000);
        heal_btn.setLayoutX(10000);
        heal();
        hpBar.setProgress(sethpBar());
        playerHeal_label.setOpacity(1);
        delay(1,"fixHeal");
        runEnemyTurn();
        if(enemyisAttacking()) {
            delay(2, "doEnemyAttack");
            delay(3, "setEnemyImage");

        }
        else {
            delay(3,"doEnemyHeal");
            delay(4,"fixEnemyHeal");
        }
        if(isTankDead()){
            switchScene(event,"loseEndScreen");
        }
    }
}




