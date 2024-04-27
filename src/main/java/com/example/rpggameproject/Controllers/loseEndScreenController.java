package com.example.rpggameproject.Controllers;

import com.example.rpggameproject.GameProcess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class loseEndScreenController implements GameProcess {

    public Button endGame_btn;




    public void setEndGame_btn(ActionEvent event) throws IOException {
        System.exit(0);
    }


}