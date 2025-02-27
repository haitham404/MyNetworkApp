package com.example.mynetworkapp;

import javafx.application.Platform;
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
import java.sql.SQLException;

public class Signup {
    private Scene scene;
    private TextField nameField, emailField;
    private PasswordField passwordField;
    private Label messageLabel;
    private ProgressIndicator progressIndicator;
    private Button signUpButton;

    public Signup(Stage primaryStage) {
        // UI Setup
        Label titleLabel = new Label("Sign Up");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        titleLabel.setTextFill(Color.DARKSLATEGRAY);

        Button backButton = createBackButton(primaryStage);
        HBox headBox = new HBox(10, backButton, titleLabel);
        headBox.setAlignment(Pos.CENTER);

        nameField = new TextField();
        nameField.setPromptText("Enter name");

        emailField = new TextField();
        emailField.setPromptText("Enter email");

        passwordField = new PasswordField();
        passwordField.setPromptText("Enter password (min 6 characters)");

        HBox nameBox = new HBox(10, new Label("Name:"), nameField);
        nameBox.setAlignment(Pos.CENTER);
        HBox emailBox = new HBox(10, new Label("Email:"), emailField);
        emailBox.setAlignment(Pos.CENTER);
        HBox passwordBox = new HBox(10, new Label("Password:"), passwordField);
        passwordBox.setAlignment(Pos.CENTER);

        signUpButton = new Button("Sign Up");
        signUpButton.setOnAction(e -> signUp(primaryStage));

        progressIndicator = new ProgressIndicator();
        progressIndicator.setVisible(false);

        messageLabel = new Label();
        messageLabel.setTextFill(Color.FIREBRICK);

        VBox vbox = new VBox(20, headBox, nameBox, emailBox, passwordBox,
                signUpButton, progressIndicator, messageLabel);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(30));
        vbox.setStyle("-fx-background-color: #F5F5F5;");

        scene = new Scene(vbox, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }

    private Button createBackButton(Stage primaryStage) {
        Button backButton = new Button("←");
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        backButton.setTextFill(Color.BLACK);
        backButton.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        backButton.setOnAction(e -> primaryStage.setScene(new login(primaryStage).getScene()));
        return backButton;
    }

    private void signUp(Stage stage) {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        if (!validateInputs(name, email, password)) {
            return;
        }

        progressIndicator.setVisible(true);
        signUpButton.setDisable(true);

        new Thread(() -> {
            try {
                boolean success = UserDAO.insertUser(name, email, password);
                if (success) {
                    User user = UserDAO.getUserByEmail(email);
                    Platform.runLater(() -> {
                        UserSession.getInstance().setUser(user);
                        messageLabel.setTextFill(Color.GREEN);
                        messageLabel.setText("Sign Up Successful!");
                        passwordField.clear();
                        stage.setScene(new HomePage(stage).getScene());
                    });
                } else {
                    Platform.runLater(() -> {
                        messageLabel.setText("Failed to sign up. Please try again.");
                        messageLabel.setTextFill(Color.FIREBRICK);
                    });
                }
            } catch (SQLException e) {
                Platform.runLater(() -> {
                    messageLabel.setText(e.getMessage().contains("duplicate") ?
                            "Email already exists" : "Database error");
                    messageLabel.setTextFill(Color.FIREBRICK);
                });
                e.printStackTrace();
            } finally {
                Platform.runLater(() -> {
                    progressIndicator.setVisible(false);
                    signUpButton.setDisable(false);
                });
            }
        }).start();
    }


    private boolean validateInputs(String name, String email, String password) {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Please fill all fields!");
            return false;
        }

        if (!email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            messageLabel.setText("Invalid email format!");
            return false;
        }

        if (password.length() < 6) {
            messageLabel.setText("Password must be at least 6 characters!");
            return false;
        }

        return true;
    }

    public Scene getScene() {
        return scene;
    }
}