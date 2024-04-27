package com.example.rpggameproject.Controllers;

import com.example.rpggameproject.AssassinGameProcess;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class WinEndScreenController implements AssassinGameProcess {

    public Button endGame_btn;




    public void setEndGame_btn(ActionEvent event) throws IOException {
        System.exit(0);
    }


}