package com.example.rpggameproject.Controllers;

import com.example.rpggameproject.ControlEndGame;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class loseEndScreenController implements ControlEndGame {
    @FXML
    private Button endGame_btn;
    @FXML
    private Button seeStats_btn;
    @FXML
    private Label win_label;
    public void setEndGame_btn() {
        System.exit(0);
    }
    public void setSeeStats_btn() {
        win_label.setText(getStatsLose());
    }



}