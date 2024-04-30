package com.example.rpggameproject.Controllers.FightControllers;

import com.example.rpggameproject.TankGameProcess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;

public class tankfightController implements TankGameProcess {
    @FXML
    private ImageView enemy_img;
    @FXML
    private ImageView player_img;
    @FXML
    private ImageView bullet;
    @FXML
    private ImageView bullet1;
    @FXML
    private ImageView bullet2;
    @FXML
    private ImageView bullet3;
    @FXML
    private ImageView bullet4;
    @FXML
    private ImageView bullet5;
    @FXML
    private ImageView bullet6;
    @FXML
    private ImageView bullet7;
    @FXML
    private ImageView bullet8;
    @FXML
    private ImageView bullet9;
    @FXML
    private ImageView bullet10;
    @FXML
    private ImageView shield;
    @FXML
    private ImageView white_flag;
    @FXML
    private Button attack_btn;
    @FXML
    private Button heal_btn;
    @FXML
    private Button useSpam_btn;
    @FXML
    private Button spamAttack_btn;
    @FXML
    private Button back_btn;
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
    @FXML
    private Label outOfBullets_label;
    @FXML
    private Rectangle outOfBullets_rect;
    static int bulletCount = 1;


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
                    if(isTankDead()){
                        return;
                    }
                    delay(3.5, "bringBackAttackButton");
                    delay(3.5, "bringBackHealButton");
                    delay(3.5, "bringBackUseSpamButton");

                    break;
                case "runEnemyHeal":
                    runEnemyHeal();
                    delay(1,"doEnemyHeal");
                    delay(1,"updateEnemyHpBar");
                    delay(2,"getRidOfEnemyHeal");
                    delay(2,"bringBackAttackButton");
                    delay(2,"bringBackHealButton");
                    delay(2, "bringBackUseSpamButton");
                    break;

                case "getRidOfBulletImage":
                    bullet.setOpacity(0);
                    break;
                case "getRidOfBullet1":
                    bullet1.setOpacity(0);
                    break;
                case "getRidOfBullet2":
                    bullet2.setOpacity(0);
                    break;
                case "getRidOfBullet3":
                    bullet3.setOpacity(0);
                    break;
                case "getRidOfBullet4":
                    bullet4.setOpacity(0);
                    break;
                case "getRidOfBullet5":
                    bullet5.setOpacity(0);
                    break;
                case "getRidOfBullet6":
                    bullet6.setOpacity(0);
                    break;
                case "getRidOfBullet7":
                    bullet7.setOpacity(0);
                    break;
                case "getRidOfBullet8":
                    bullet8.setOpacity(0);
                    break;
                case "getRidOfBullet9":
                    bullet9.setOpacity(0);
                    break;
                case "getRidOfBullet10":
                    bullet10.setOpacity(0);
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
                    enemy_img.setRotationAxis(Rotate.Y_AXIS);
                    enemy_img.setRotate(0);
                    break;
                case "getRidOfSheild":
                    shield.setOpacity(0);
                    break;

                case "bringBackAttackButton":
                    attack_btn.setLayoutX(350);
                    break;
                case "bringBackHealButton":
                    heal_btn.setLayoutX(900);
                    break;
                case "bringBackUseSpamButton":
                    useSpam_btn.setLayoutX(630);
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
        });
    }
    public void animate(String fcn){
        Executors.newSingleThreadExecutor().execute(() -> {
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
                case "blockAnimationp1":
                    try {
                        Thread.sleep(1800);
                    }
                    catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    shield.setOpacity(1);
                    enemyhpBar.setOpacity(0);
                    for(int i = -300; i <= 400; i+=15){
                        shield.setLayoutY(i);
                        try {
                            Thread.sleep(5);
                        }
                        catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case "blockAnimationp2":
                    try {
                        Thread.sleep(2800);
                    }
                    catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    for(int i = 400; i >= -300; i-=5){
                        shield.setLayoutY(i);
                        try {
                            Thread.sleep(5);
                        }
                        catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    delay(.1,"getRidOfSheild");
                    delay(.5,"bringBackAttackButton");
                    delay(.5,"bringBackHealButton");
                    delay(.5, "bringBackUseSpamButton");


                    break;

                case "enemyBlockedAnimation1":
                    try {
                        Thread.sleep(1800);
                    }
                    catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    for(int i = 1100; i >= 700; i-=5){
                        enemy_img.setLayoutX(i);
                        try {
                            Thread.sleep(5);
                        }
                        catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case "enemyBlockedAnimation2":
                    try {
                        Thread.sleep(2800);
                    }
                    catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    enemy_img.setRotationAxis(Rotate.Y_AXIS);
                    enemy_img.setRotate(180);
                    for(int i = 700; i <= 1100; i+=5){
                        enemy_img.setLayoutX(i);
                        try {
                            Thread.sleep(5);
                        }
                        catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    delay(.2,"unRotateEnemy");
                    delay(.2,"putBackEnemyHpBar");
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
                    }
                    catch (InterruptedException e) {
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
    public void enemyChoice(double wait){
        if((int)(Math.random() * 11) <= 7){
            if(isBlocked()){
                animate("blockAnimationp1");
                animate("enemyBlockedAnimation1");
                animate("blockAnimationp2");
                animate("enemyBlockedAnimation2");
                return;
            }
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
            if(isTankDead()){
                delay(3,"endGame");
                delay(2,"killPlayer");
            }
        });
    }
    public void onAttackButtonClicked(){
        bullet.setOpacity(1);
        bullet.setLayoutX(350);
        attack_btn.setLayoutX(10000);
        heal_btn.setLayoutX(10000);
        useSpam_btn.setLayoutX(10000);

        basicAttack();
        animate("bulletAnimation");
        delay(1.1,"updateEnemyHpBar");
        delay(1.1,"getRidOfBulletImage");
        if(isEnemyDead()){
            delay(3,"endGame");
            delay(2,"killEnemy");
            return;
        }
        enemyChoice(1);
        isPlayerDead(3);

    }

    public void onHealButtonClicked(){
        attack_btn.setLayoutX(10000);
        heal_btn.setLayoutX(10000);
        useSpam_btn.setLayoutX(10000);

        heal();
        hpBar.setProgress(sethpBar());
        playerHeal_label.setOpacity(1);
        delay(1, "fixHeal");
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
        public void onUseSpamButtonClicked(){
            bulletCount = 1;
            attack_btn.setLayoutX(10000);
            heal_btn.setLayoutX(10000);
            useSpam_btn.setLayoutX(10000);
            back_btn.setLayoutX(250);
            spamAttack_btn.setLayoutX(630);
            ImageView[] spamBulletsList = new ImageView[]{bullet1,bullet2,bullet3,bullet4,bullet5,bullet6,bullet7,bullet8,bullet9,bullet10};
        }

        public void onBackButtonClicked(){
            back_btn.setLayoutX(10000);
            spamAttack_btn.setLayoutX(10000);
            attack_btn.setLayoutX(350);
            heal_btn.setLayoutX(900);
            useSpam_btn.setLayoutX(630);
            outOfBullets_rect.setLayoutX(10000);
            outOfBullets_label.setLayoutX(10000);
        }
        public void onSpamButtonClicked(){
            spamAttack();
            runSpamAnimation(bulletCount + "");
            bulletCount++;
            if(bulletCount == 10){
                spamAttack_btn.setLayoutX(10000);
                outOfBullets_label.setOpacity(1);
                outOfBullets_rect.setOpacity(1);
            }
        }
        public void runSpamAnimation(String bulletNo){
            Executors.newSingleThreadExecutor().execute(() -> {
                switch (bulletNo){
                    case "1":
                        bullet1.setOpacity(1);
                        for(int i = 350; i <= 1200; i+=5){
                            bullet1.setLayoutX(i);
                            try {
                                Thread.sleep(5);
                            }
                            catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            delay(1.3, "getRidOfBullet1");
                            delay(1.3, "updateEnemyHpBar");
                        }
                        break;
                    case "2":
                        bullet2.setOpacity(1);
                        for(int i = 350; i <= 1200; i+=5){
                            bullet2.setLayoutX(i);
                            try {
                                Thread.sleep(5);
                            }
                            catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            delay(1.3, "getRidOfBullet2");
                            delay(1.3, "updateEnemyHpBar");
                        }
                        break;
                    case "3":
                        bullet3.setOpacity(1);
                        for(int i = 350; i <= 1200; i+=5){
                            bullet3.setLayoutX(i);
                            try {
                                Thread.sleep(5);
                            }
                            catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            delay(1.3, "getRidOfBullet3");
                            delay(1.3, "updateEnemyHpBar");
                        }
                        break;
                    case "4":
                        bullet4.setOpacity(1);
                        for(int i = 350; i <= 1200; i+=5){
                            bullet4.setLayoutX(i);
                            try {
                                Thread.sleep(5);
                            }
                            catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            delay(1.3, "getRidOfBullet4");
                            delay(1.3, "updateEnemyHpBar");
                        }
                        break;
                    case "5":
                        bullet5.setOpacity(1);
                        for(int i = 350; i <= 1200; i+=5){
                            bullet5.setLayoutX(i);
                            try {
                                Thread.sleep(5);
                            }
                            catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            delay(1.3, "getRidOfBullet5");
                            delay(1.3, "updateEnemyHpBar");
                        }
                        break;
                    case "6":
                        bullet6.setOpacity(1);
                        for(int i = 350; i <= 1200; i+=5){
                            bullet6.setLayoutX(i);
                            try {
                                Thread.sleep(5);
                            }
                            catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            delay(1.3, "getRidOfBullet6");
                            delay(1.3, "updateEnemyHpBar");
                        }
                        break;
                    case "7":
                        bullet7.setOpacity(1);
                        for(int i = 350; i <= 1200; i+=5){
                            bullet7.setLayoutX(i);
                            try {
                                Thread.sleep(5);
                            }
                            catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            delay(1.3, "getRidOfBullet7");
                            delay(1.3, "updateEnemyHpBar");
                        }
                        break;
                    case "8":
                        bullet8.setOpacity(1);
                        for(int i = 350; i <= 1200; i+=5){
                            bullet8.setLayoutX(i);
                            try {
                                Thread.sleep(5);
                            }
                            catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            delay(1.3, "getRidOfBullet8");
                            delay(1.3, "updateEnemyHpBar");
                        }
                        break;
                    case "9":
                        bullet9.setOpacity(1);
                        for(int i = 350; i <= 1200; i+=5){
                            bullet9.setLayoutX(i);
                            try {
                                Thread.sleep(5);
                            }
                            catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            delay(1.3, "getRidOfBullet9");
                            delay(1.3, "updateEnemyHpBar");
                        }
                        break;
                    case "10":
                        bullet10.setOpacity(1);
                        for(int i = 350; i <= 1200; i+=5){
                            bullet10.setLayoutX(i);
                            try {
                                Thread.sleep(5);
                            }
                            catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            delay(1.3, "getRidOfBullet10");
                            delay(1.3, "updateEnemyHpBar");
                        }
                        break;
                }


            });
        }
}




