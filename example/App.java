package com.example;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * JavaFX App
 */
public class App extends Application {

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage window) throws Exception {
        // LocalDateTime date = LocalDateTime.now();
        // Date date2 = new Date();
        // Date date3 = new Date(date2.getTime()+(1000*60*60*24));
        // date.plusDays(1);
        // System.out.println(date2);
        // Calendar cal = Calendar.getInstance();
        // cal.setTime(date3);

        // System.out.println(cal.get(Calendar.DAY_OF_MONTH));
        Hospital hospital=new Hospital("Amir",1382);

        DataCenter dataCenter=new DataCenter();
        dataCenter.loadAll(hospital);
        DatePicker dp = new DatePicker();
        
        hospital.menu(window);
//         BorderPane borderPane = new BorderPane();
//         HBox middleBox = new HBox();
//         VBox middleWest = new VBox();
//         VBox middleeast = new VBox();
//         middleBox.getChildren().addAll(middleWest,middleeast);
//         borderPane.setCenter(middleBox);
//         TextField textField = new TextField();
//         textField.setPromptText("fewfwe");
//         Button button1 = new Button("pls click me");
//         RadioButton rb = new RadioButton();
//         button1.setOnAction(e -> {button1.setStyle("-fx-background-color : white");
//             System.out.println(hospital.getVisits().get(0).getRandomID());
//             button1.setText(textField.getText());
//             textField.clear();
//             System.out.println(hospital.getVisits().get(0).getDoctor().getFirstName());
//             System.out.println(hospital.getDoctors().get(0).getPatients(hospital).get(0).getFirstName());
//             hospital.getPatients().get(0).menu(hospital,window);
// //new Patient(STYLESHEET_CASPIAN, STYLESHEET_CASPIAN, STYLESHEET_CASPIAN, STYLESHEET_MODENA, 0, STYLESHEET_CASPIAN).changeInfo(null, primaryStage);
// //                 new Doctor(0, 0,"amir" , STYLESHEET_CASPIAN, STYLESHEET_CASPIAN, STYLESHEET_CASPIAN, 0, 0, false, false, STYLESHEET_MODENA, STYLESHEET_CASPIAN).menu(null, primaryStage);
//                 //    hospital.getPatients().get(0).searchDoctor(hospital,window);
//         });
//         dp.setOnAction(e -> System.out.println(dp.getValue().getDayOfMonth()));
//         middleWest.getChildren().addAll(button1,dp,rb);
//         middleeast.getChildren().addAll(textField);
//         middleWest.setAlignment(Pos.CENTER);
//         middleeast.setAlignment(Pos.CENTER);
//         middleBox.setAlignment(Pos.CENTER);
//         Scene scene = new Scene(borderPane,400,500);
//         scene.getStylesheets().addAll(getClass().getResource("menu.css").toExternalForm());
//         window.setScene(scene);
//         window.show();
    }
}