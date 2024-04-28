module com.example.rpggameproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.rpggameproject to javafx.fxml;
    exports com.example.rpggameproject;
    exports com.example.rpggameproject.Controllers;
    opens com.example.rpggameproject.Controllers to javafx.fxml;
    exports com.example.rpggameproject.Characters;
    opens com.example.rpggameproject.Characters to javafx.fxml;
    exports com.example.rpggameproject.Controllers.FightControllers;
    opens com.example.rpggameproject.Controllers.FightControllers to javafx.fxml;
}