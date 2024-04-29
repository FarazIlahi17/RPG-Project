package com.example.rpggameproject.Controllers;

import com.example.rpggameproject.AssassinGameProcess;
import com.example.rpggameproject.ControlEndGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;

public class StartController implements ControlEndGame {

    @FXML
    private TextField enterName_texField;



    public void switchToCharSelectScene(ActionEvent event) throws IOException {
        setName(enterName_texField.getText());
        switchScene(event, "CharacterSelect");

    }


}