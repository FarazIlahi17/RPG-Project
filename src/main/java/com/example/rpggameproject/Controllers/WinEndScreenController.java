package com.example.rpggameproject.Controllers;

import com.example.rpggameproject.GameProcess;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class WinEndScreenController implements GameProcess {

    public Button endGame_btn;




    public void setEndGame_btn(ActionEvent event) throws IOException {
        System.exit(0);
    }


}