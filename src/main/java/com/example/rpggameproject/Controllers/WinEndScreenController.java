package com.example.rpggameproject.Controllers;

import com.example.rpggameproject.AssassinGameProcess;
import com.example.rpggameproject.ControlEndGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class WinEndScreenController implements ControlEndGame {

    @FXML
    private Button endGame_btn;
    @FXML
    private Label win_label;
    public void setEndGame_btn() {
//        win_label.setText(getName());
        System.exit(0);
    }


}