package com.example.rpggameproject.Controllers;


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
                try {
                    long milles = (long)(fraction * 1000);
                    Thread.sleep(milles);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                switch (fcn) {
                    case "fireBallAnimation1":
                        fireball.setLayoutX(700);
                        break;
                    case "fireBallAnimation2":
                        fireball.setLayoutX(900);
                        break;
                    case "fireBallAnimation3":
                        fireball.setLayoutX(1200);
                        break;
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
                        heal_btn.setLayoutX(900);
                        enemyhpBar.setOpacity(1);
                        break;
                    case "fixEnemyHeal":
                        enemyHeal_label.setOpacity(0);
                        heal_btn.setLayoutX(900);
                        break;
                    case "doEnemyHeal":
                        enemyHeal_label.setOpacity(1);
                        enemyhpBar.setProgress(setEnemyhpBar());
                        break;
                    case "setBasicAttackbutton":
                        attack_btn.setLayoutX(350);
                        break;
                    case "setSpecialAttackbutton":
                        attack_btn.setLayoutX(350);
                        special_btn.setLayoutX(625);
                        break;
                }
            }
        });
    }
    public void doEnemyAttack(){
        if (enemyisAttacking()) {
            delay(2, "doEnemyAttack");
            delay(3, "setEnemyImage");
            if (canSpecialAttack()){
                delay(3, "setSpecialAttackbutton");
            }
            else if (canBasicAttack()){
                delay(3, "setBasicAttackbutton");
            }
        }
        else {
            delay(2, "doEnemyHeal");
            delay(3, "fixEnemyHeal");
            if (canSpecialAttack()){
                delay(3, "setSpecialAttackbutton");
            }
            else if (canBasicAttack()){
                delay(3, "setBasicAttackbutton");
            }
        }
    }

    public void onAttackButtonClicked(ActionEvent event) throws IOException {
        attack_btn.setLayoutX(10000);
        heal_btn.setLayoutX(10000);
        special_btn.setLayoutX(10000);
        fireball.setOpacity(1);
        fireball.setLayoutX(400);
        basicAttack();
        manaBar.setProgress(setManaBar());
        delay(.2,"fireBallAnimation1");
        delay(.5,"fireBallAnimation2");
        delay(.9,"fireBallAnimation3");
        delay(1,"setEnemyHpBar");
        runEnemyTurn();
        delay(1,"setFireballImg");
        doEnemyAttack();
        if (isEnemyDead()) {
            switchScene(event, "winEndScreen");
        }
        if (isMageDead()) {
            switchScene(event, "loseEndScreen");
        }
    }

    public void onHealButtonClicked(ActionEvent event) throws IOException {
        attack_btn.setLayoutX(10000);
        heal_btn.setLayoutX(10000);
        special_btn.setLayoutX(10000);
        heal();
        hpBar.setProgress(sethpBar());
        manaBar.setProgress(setManaBar());
        playerHeal_label.setOpacity(1);
        delay(1, "fixHeal");
        runEnemyTurn();
        doEnemyAttack();
        if (isMageDead()) {
            switchScene(event, "loseEndScreen");
        }
    }

    public void onSpecialButtonClicked(ActionEvent event) throws IOException{
        attack_btn.setLayoutX(10000);
        heal_btn.setLayoutX(10000);
        special_btn.setLayoutX(10000);
        laser.setOpacity(1);
        mageSpecialAttack();
        manaBar.setProgress(setManaBar());
        delay(1,"laserAnimation");
        delay(1,"setEnemyHpBar");
        runEnemyTurn();
        doEnemyAttack();
        if (isEnemyDead()) {
            switchScene(event, "winEndScreen");
        }
        if (isMageDead()) {
            switchScene(event, "loseEndScreen");
        }
    }

}




