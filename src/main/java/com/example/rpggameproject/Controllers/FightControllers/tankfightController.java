package com.example.rpggameproject.Controllers.FightControllers;

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
    public ImageView shield;
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
                if(!fcn.equals("bulletAnimation")){
                    try {
                        long milles = (long) (fractions * 1000);
                        Thread.sleep(milles);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                else{
                    for(int i = 350; i <= 1200; i++){
                        bullet.setLayoutX(i);
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                switch(fcn) {
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
                    case "doNothing":
                        break;
                }
            }
        });
    }

    public void doEnemyAttack(){
        runEnemyTurn();
        if(enemyisAttacking()) {
            if(isBlocked()){
                delay(3,"block");
                delay(4,"resetBlock");
            }
            else {
                runEnemyDamage();
                delay(3, "doEnemyAttack");
                delay(4, "setEnemyImage");
            }

        }
        else {
            delay(3,"doEnemyHeal");
            delay(4,"fixEnemyHeal");
        }
    }

    public void onAttackButtonClicked(ActionEvent event) throws IOException {
        bullet.setOpacity(1);
        bullet.setLayoutX(350);
        attack_btn.setLayoutX(10000);
        heal_btn.setLayoutX(10000);
        basicAttack();
        delay(1,"bulletAnimation");
        delay(1.7,"setEnemyHpBar");
        delay(1.7,"setBulletImage");
        doEnemyAttack();
        if(isEnemyDead()){
            switchScene(event,"winEndScreen");
        }
        if(isTankDead()){
            switchScene(event,"loseEndScreen");
        }

    }

    public void onHealButtonClicked(ActionEvent event) throws IOException {
        attack_btn.setLayoutX(10000);
        heal_btn.setLayoutX(10000);
        heal();
        hpBar.setProgress(sethpBar());
        playerHeal_label.setOpacity(1);
        delay(1,"fixHeal");
        doEnemyAttack();
        if(isTankDead()){
            switchScene(event,"loseEndScreen");
        }
    }
}




