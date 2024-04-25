package com.example.rpggameproject.Controllers;


import com.example.rpggameproject.GameProcess;
import com.example.rpggameproject.Information;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class GameController implements Information, GameProcess {
    public ImageView enemy_img;

    public ImageView player_img;
    public Button attack_btn;
    public Button heal_btn;
    public Button special_btn;

    public void setPlayer_img(ImageView player_img) {
        this.player_img = player_img;
    }

    public ImageView getPlayer_img() {
        return player_img;
    }

    public void onAttackButtonClicked(){
        GameProcess.super.setPlayer("Assassin");
    }
}




