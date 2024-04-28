package com.example.rpggameproject.Controllers.FightControllers;


import com.example.rpggameproject.AssassinGameProcess;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class assassinfightController implements AssassinGameProcess {
    public ImageView enemy_img;
    public ImageView player_img;
    public Button attack_btn;
    public Button heal_btn;
    public ProgressBar hpBar;
    public ProgressBar enemyhpBar;
    public Label crit_label;
    public Label playerHeal_label;
    public Label enemyHeal_label;


    public void delay(long seconds, String fcn){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(seconds * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                switch(fcn) {
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
    public void doEnemyAttack(){
        runEnemyTurn();
        if(enemyisAttacking()) {
            delay(2, "doEnemyAttack");
            delay(3, "setEnemyImage");
        }
        else {
            delay(2,"doEnemyHeal");
            delay(3,"fixEnemyHeal");
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
        doEnemyAttack();
        if(isEnemyDead()){
            switchScene(event,"winEndScreen");
        }
        if(isAssassinDead()){
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
        if(isAssassinDead()){
            switchScene(event,"loseEndScreen");
        }
    }
}




