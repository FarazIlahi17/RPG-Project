module com.example.rpg_game {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.rpg_game to javafx.fxml;
    exports com.example.rpg_game;
}