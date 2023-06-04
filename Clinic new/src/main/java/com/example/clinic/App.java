package com.example.clinic;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        HBox middleBox = new HBox();
        VBox middleWest = new VBox();
        VBox middleeast = new VBox();
        middleBox.getChildren().addAll(middleWest,middleeast);
        borderPane.setCenter(middleBox);
        TextField textField = new TextField();
        textField.setPromptText("fewfwe");
        Button button1 = new Button("pls click me");
        button1.setOnAction(e -> {button1.setStyle("-fx-background-color : white");
            button1.setText(textField.getText());
            textField.clear();
//new Patient(STYLESHEET_CASPIAN, STYLESHEET_CASPIAN, STYLESHEET_CASPIAN, STYLESHEET_MODENA, 0, STYLESHEET_CASPIAN).changeInfo(null, primaryStage);
                 new Doctor(0, 0,"amir" , STYLESHEET_CASPIAN, STYLESHEET_CASPIAN, STYLESHEET_CASPIAN, 0, 0, false, false, STYLESHEET_MODENA, STYLESHEET_CASPIAN).menu(null, primaryStage);
        });
        middleWest.getChildren().addAll(button1);
        middleeast.getChildren().addAll(textField);
        middleWest.setAlignment(Pos.CENTER);
        middleeast.setAlignment(Pos.CENTER);
        middleBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(borderPane,400,500);
        scene.getStylesheets().addAll(getClass().getResource("menu.css").toExternalForm());
        primaryStage.setScene(scene);   
        primaryStage.show();
    }
}