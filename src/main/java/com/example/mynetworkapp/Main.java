package com.example.mynetworkapp;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Main extends Application {
@Override
    public void start(Stage stage) throws Exception {
    welcome(stage);
}

public static void welcome (Stage primarystage) throws Exception {
    Stage wstage =new Stage();
    //welcome label
    Label welabel = new Label ("Welcome");
    welabel.setFont(Font.font("Arial",FontWeight.BOLD,37));

    //lay out
    StackPane stackPane =new StackPane(welabel);
    Scene ws = new Scene(stackPane);

    wstage.setScene(ws);
    wstage.show();
    wstage.setTitle("Welcome");
    wstage.setMinHeight(300);
    wstage.setMinWidth(500);

    //transition to main stage after 2 seconds
    PauseTransition pauseTransition =new PauseTransition(new Duration(2000));
    pauseTransition.setOnFinished(e->{
        wstage.close();
        mainStage(primarystage);
    });
    pauseTransition.play();
}

public static void mainStage(Stage stage){
    login login=new login(stage);
}

    public static void main(String[] args) {
        launch();
    }
}
