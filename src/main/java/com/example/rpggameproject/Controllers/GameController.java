package com.example.rpggameproject.Controllers;


import com.example.rpggameproject.AssassinGameProcess;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class GameController implements AssassinGameProcess {
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

    }
}




