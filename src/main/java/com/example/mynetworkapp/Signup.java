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

public class Signup {

    private Scene scene;

    public Signup(Stage Primarystage) {
        // Title label
        Label titleLabel = new Label("Signup");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        titleLabel.setTextFill(Color.DARKSLATEGRAY);

        // Username
        Label nameLabel = new Label("Username:");
        nameLabel.setFont(Font.font("Arial", 14));

        TextField textFieldName = new TextField();
        textFieldName.setPromptText("Enter username");
        textFieldName.setFont(Font.font("Arial", 14));
        textFieldName.setMinHeight(30);
        textFieldName.setStyle("-fx-border-color: #A9A9A9; -fx-border-radius: 5px; -fx-padding: 5px;");

        // Password
        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Arial", 14));

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");
        passwordField.setFont(Font.font("Arial", 14));
        passwordField.setMinHeight(30);
        passwordField.setStyle("-fx-border-color: #A9A9A9; -fx-border-radius: 5px; -fx-padding: 5px;");

        // Facebook Link
        Label facebookLabel = new Label("Facebook link\n(optional):");
        facebookLabel.setFont(Font.font("Arial", 14));

        TextField facebookField = new TextField();
        facebookField.setPromptText("Enter link to your account");
        facebookField.setFont(Font.font("Arial", 14));
        facebookField.setMinHeight(30);
        facebookField.setStyle("-fx-border-color: #A9A9A9; -fx-border-radius: 5px; -fx-padding: 5px;");

        // LinkedIn Link
        Label linkedinLabel = new Label("LinkedIn link:");
        linkedinLabel.setFont(Font.font("Arial", 14));

        TextField linkedinField = new TextField();
        linkedinField.setPromptText("Enter link to your account");
        linkedinField.setFont(Font.font("Arial", 14));
        linkedinField.setMinHeight(30);
        linkedinField.setStyle("-fx-border-color: #A9A9A9; -fx-border-radius: 5px; -fx-padding: 5px;");

        // Signup Button
        Button signupButton = new Button("Signup");
        signupButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        signupButton.setTextFill(Color.WHITE);
        signupButton.setStyle("-fx-background-color: #4CAF50; -fx-border-radius: 5px; -fx-padding: 5px 10px;");
        signupButton.setMinWidth(150);
        signupButton.setOnAction(e->{
            HomePage homePage=new HomePage(Primarystage);
        });

        Label loginLabel = new Label("Already have an account? Login");
        loginLabel.setTextFill(Color.BLUE);

        // ListView for interests
        Label listViewLabel = new Label("Choose your interested fields:");
        listViewLabel.setFont(Font.font("Arial", 14));

        ListView<String> listView = new ListView<>();
        listView.getItems().addAll("Student", "Teacher");
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.setPrefSize(200, 100);

        // Layout arrangements
        HBox titleLabelBox = new HBox(titleLabel);
        titleLabelBox.setAlignment(Pos.CENTER);

        HBox username = new HBox(20, nameLabel, textFieldName);
        username.setAlignment(Pos.CENTER);

        HBox password = new HBox(20, passwordLabel, passwordField);
        password.setAlignment(Pos.CENTER);

        HBox facebook = new HBox(10, facebookLabel, facebookField);
        facebook.setAlignment(Pos.CENTER);

        HBox linkedin = new HBox(10, linkedinLabel, linkedinField);
        linkedin.setAlignment(Pos.CENTER);

        HBox signup = new HBox(signupButton);
        signup.setAlignment(Pos.CENTER);

        HBox loginLabelHBox = new HBox(loginLabel);
        loginLabelHBox.setAlignment(Pos.CENTER);

        HBox comboBoxHBox = new HBox(10, listViewLabel, listView);
        comboBoxHBox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(20, titleLabelBox, username, password, facebook, linkedin, comboBoxHBox, signup, loginLabelHBox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(30));
        vbox.setStyle("-fx-background-color: #F5F5F5; -fx-border-radius: 10px;");

        // Scene setup
        scene = new Scene(vbox, 400, 600);
        Primarystage.setScene(scene);
        Primarystage.setTitle("Signup");
        Primarystage.show();
    }
}
