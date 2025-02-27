package com.example.mynetworkapp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class login {
    private Scene scene;

    public login(Stage primaryStage) {
        // Title Label
        Label titleLabel = new Label("Login");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        titleLabel.setTextFill(Color.DARKSLATEGRAY);

        // Username Field
        Label nameLabel = new Label("Username:");
        nameLabel.setFont(Font.font("Arial", 14));
        TextField textFieldName = new TextField();
        textFieldName.setPromptText("Enter username");
        textFieldName.setFont(Font.font("Arial", 14));
        textFieldName.setMinHeight(30);
        textFieldName.setStyle("-fx-border-color: #A9A9A9; -fx-border-radius: 5px; -fx-padding: 5px;");

        // Password Field
        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Arial", 14));
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");
        passwordField.setFont(Font.font("Arial", 14));
        passwordField.setMinHeight(30);
        passwordField.setStyle("-fx-border-color: #A9A9A9; -fx-border-radius: 5px; -fx-padding: 5px;");

        // Login Button
        Button loginButton = new Button("Login");
        loginButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        loginButton.setTextFill(Color.WHITE);
        loginButton.setStyle("-fx-background-color: #4CAF50; -fx-border-radius: 5px; -fx-padding: 5px 10px;");
        loginButton.setMinWidth(100);

        // Signup Link
        Label labelSignup = new Label("Dont have account ?signup");
        labelSignup.setTextFill(Color.BLUE);
        labelSignup.setFont(Font.font("Arial", 12));
        labelSignup.setOnMouseClicked(e -> new Signup(primaryStage));

        // Creating Horizontal Box Layouts (HBox)
        HBox nameBox = new HBox(10, nameLabel, textFieldName);
        nameBox.setAlignment(Pos.CENTER);

        HBox passwordBox = new HBox(10, passwordLabel, passwordField);
        passwordBox.setAlignment(Pos.CENTER);

        HBox buttonBox = new HBox(loginButton);
        buttonBox.setAlignment(Pos.CENTER);

        HBox signupBox = new HBox(labelSignup);
        signupBox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(40, titleLabel, nameBox, passwordBox, buttonBox, signupBox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(30));
        vbox.setStyle("-fx-background-color: #F5F5F5; -fx-border-radius: 10px;");


        scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(600);
        primaryStage.show();
    }
}
