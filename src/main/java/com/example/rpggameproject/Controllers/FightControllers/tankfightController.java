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
    public ImageView white_flag;
    public Button attack_btn;
    public Button heal_btn;

    public Button endGame_btn;
    public ProgressBar hpBar;
    public ProgressBar enemyhpBar;
    public Label playerHeal_label;
    public Label enemyHeal_label;


    public void delay(double fractions, String fcn){
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
                        long milles = (long) (fractions * 1000);
                        Thread.sleep(milles);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
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
                        enemyhpBar.setOpacity(1);
                        break;
                    case "fixEnemyHeal":
                        enemyHeal_label.setOpacity(0);
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
                }
            }
        });
    }

    public void doEnemyAttack(){
        if(isEnemyDead()){
            return;
        }
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
                if(isTankDead()){
                    return;
                }
                delay(4, "bringBackAttackButton");
                delay(4, "bringBackHealButton");
            }
        }
        else {
            delay(3,"doEnemyHeal");
            delay(4,"fixEnemyHeal");
            delay(4, "bringBackAttackButton");
            delay(4, "bringBackHealButton");
        }
    }

    public void onAttackButtonClicked(){
        bullet.setOpacity(1);
        bullet.setLayoutX(350);
        attack_btn.setLayoutX(10000);
        heal_btn.setLayoutX(10000);
        basicAttack();
        delay(1.1,"bulletAnimation");
        delay(1.1,"setEnemyHpBar");
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




