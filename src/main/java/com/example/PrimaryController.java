package com.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane);
    }
}
