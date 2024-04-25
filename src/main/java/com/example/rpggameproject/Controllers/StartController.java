package com.example.rpggameproject.Controllers;

import com.example.rpggameproject.HelloApplication;
import com.example.rpggameproject.Information;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.io.IOException;

public class StartController implements Information {

    private Stage stage;


    @FXML
    private Label welcomeText;



    public void switchToCharSelectScene(ActionEvent event) throws IOException {
        Information.super.switchScene(event, "CharacterSelect");
    }


}