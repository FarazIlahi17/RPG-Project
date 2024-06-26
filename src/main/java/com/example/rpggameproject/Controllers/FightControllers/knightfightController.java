package com.example.rpggameproject.Controllers.FightControllers;

import com.example.rpggameproject.KnightGameProcess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

import java.io.IOException;
import java.util.concurrent.Executors;

public class knightfightController implements KnightGameProcess {

    @FXML
    private ImageView enemy_img;
    @FXML
    private ImageView player_img;
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
    private ProgressBar chargebar;
    @FXML
    private ProgressBar enemyhpBar;
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

            switch (fcn) {
                case "runEnemyAttack":
                    runEnemyDamage();
                    animate("enemyGoForwardAnimation");
                    animate("enemyAttackAnimationp1");
                    animate("enemyAttackAnimationp2");
                    animate("enemyGoBackAnimation");
                    delay(3,"putBackEnemyHpBar");
                    delay(3,"unRotateEnemy");
                    if(isKnightDead()){
                        return;
                    }
                    delay(3.5, "bringBackAttackButton");
                    delay(3.5, "bringBackHealButton");
                    if(getIsCharged()){
                        delay(3.5, "bringBackSpecialButton");
                    }
                    break;
                case "runEnemyHeal":
                    runEnemyHeal();
                    delay(1,"doEnemyHeal");
                    delay(1,"updateEnemyHpBar");
                    delay(2,"getRidOfEnemyHeal");
                    delay(2,"bringBackAttackButton");
                    delay(2,"bringBackHealButton");
                    if(getIsCharged()){
                        delay(2, "bringBackSpecialButton");
                    }
                    break;


                case "unRotatePlayer":
                    player_img.setRotate(0);
                    break;
                case "updateChargeBar":
                    chargebar.setProgress(setChargeBar());
                    break;
                case "putBackHpBar":
                    hpBar.setOpacity(1);
                    break;
                case "putBackChargeBar":
                    chargebar.setOpacity(1);
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
                    break;
                case "bringBackSpecialButton":
                    special_btn.setLayoutX(625);
                    special_btn.setOpacity(1);
                    break;
                case "bringBackHealButton":
                    heal_btn.setLayoutX(900);
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
                    player_img.setRotationAxis(Rotate.Z_AXIS);
                    player_img.setRotate(270);
                    player_img.setLayoutX(50);
                    player_img.setLayoutY(470);
                    hpBar.setOpacity(0);
                    chargebar.setOpacity(0);
                    break;
            }
        });
    }
    public void animate(String fcn){
        Executors.newSingleThreadExecutor().execute(() -> {
            switch (fcn){
                case "knightGoForwardAnimation":
                    try {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    hpBar.setOpacity(0);
                    chargebar.setOpacity(0);
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
                case "knightAttackAnimationp1":
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
                case "knightAttackAnimationp2":
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
                case "knightGoBackAnimation":
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    enemy_img.setRotationAxis(Rotate.Y_AXIS);
                    player_img.setRotate(180);
                    for(int i = 900; i >= 100; i-=10){
                        player_img.setLayoutX(i);
                        try {
                            Thread.sleep(5);
                        }
                        catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        delay(.5,"putBackHpBar");
                        delay(.5,"putBackChargeBar");
                        delay(.5,"unRotatePlayer");
                        delay(.5, "updateChargeBar");
                    }
                    break;
                case "knightSpecialAttackAnimationp1":
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    player_img.setFitHeight(500);
                    player_img.setFitWidth(500);
                    player_img.setLayoutY(300);
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
                case "knightSpecialAttackAnimationp2":
                    try {
                        Thread.sleep(2250);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    player_img.setFitHeight(320);
                    player_img.setFitWidth(360);
                    player_img.setLayoutY(440);
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
                case "knightSpecialAttackAnimationp3":
                    try {
                        Thread.sleep(2750);
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
                        delay(.5,"putBackHpBar");
                        delay(.5,"putBackChargeBar");
                        delay(.5,"unRotatePlayer");
                        delay(.5, "updateChargeBar");
                    }
                    break;
                case "knightSpecialAttackEnemyKnockBackAnimationp1":
                    try {
                        Thread.sleep(1750);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    enemy_img.setRotationAxis(Rotate.Z_AXIS);
                    enemy_img.setRotate(45);
                    for(int i = 1100; i <= 1300; i+=10){
                        enemy_img.setLayoutX(i);
                        try {
                            Thread.sleep(5);
                        }
                        catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case "knightSpecialAttackEnemyKnockBackAnimationp2":
                    try {
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    enemy_img.setRotationAxis(Rotate.Z_AXIS);
                    enemy_img.setRotate(0);
                    for(int i = 1300; i >= 1100; i-=10){
                        enemy_img.setLayoutX(i);
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
                    enemy_img.setRotationAxis(Rotate.Y_AXIS);
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
            if(isKnightDead()){
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
        basicAttack();
        animate("knightGoForwardAnimation");
        animate("knightAttackAnimationp1");
        animate("knightAttackAnimationp2");
        animate("knightGoBackAnimation");
        if (isEnemyDead()) {
            delay(4,"endGame");
            delay(3,"killEnemy");
            return;
        }
        enemyChoice(2.2);
        isPlayerDead(3.8);
    }

    public void onHealButtonClicked(){
        attack_btn.setLayoutX(10000);
        heal_btn.setLayoutX(10000);
        special_btn.setLayoutX(10000);
        heal();
        hpBar.setProgress(sethpBar());
        chargebar.setProgress(setChargeBar());
        playerHeal_label.setOpacity(1);
        delay(1, "fixHeal");
        enemyChoice(.75);
        isPlayerDead(2.6);
    }

    public void onSpecialButtonClicked(){
        attack_btn.setLayoutX(10000);
        heal_btn.setLayoutX(10000);
        special_btn.setLayoutX(10000);
        knightSpecialAttack();
        animate("knightGoForwardAnimation");
        animate("knightSpecialAttackAnimationp1");
        animate("knightSpecialAttackAnimationp2");
        animate("knightSpecialAttackAnimationp3");
        animate("knightSpecialAttackEnemyKnockBackAnimationp1");
        animate("knightSpecialAttackEnemyKnockBackAnimationp2");

        if (isEnemyDead()) {
            delay(5,"endGame");
            delay(4,"killEnemy");
            return;
        }
        enemyChoice(3.7);
        isPlayerDead(5.5);
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






