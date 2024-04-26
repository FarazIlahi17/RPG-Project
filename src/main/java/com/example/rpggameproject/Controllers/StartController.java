package com.example.rpggameproject.Controllers;

import com.example.rpggameproject.GameProcess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.io.IOException;

public class StartController implements GameProcess {

    private Stage stage;


    @FXML
    private Label welcomeText;



    public void switchToCharSelectScene(ActionEvent event) throws IOException {
        switchScene(event, "CharacterSelect");
    }


}