package com.example.mynetworkapp;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class login {
    private Scene scene;
    private TextField usernameField;
    private PasswordField passwordField;
    private Label messageLabel;
   private  Button loginButton = new Button("Login");


    public login(Stage primaryStage) {
        // Title Label
        Label titleLabel = new Label("Login");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        titleLabel.setTextFill(Color.DARKSLATEGRAY);

        // Username/Email Field
        Label usernameLabel = new Label("Username/Email:");
        usernameLabel.setFont(Font.font("Arial", 14));
        usernameField = new TextField();
        usernameField.setPromptText("Enter username or email");
        usernameField.setFont(Font.font("Arial", 14));
        usernameField.setMinHeight(35);
        usernameField.setStyle("-fx-border-color: #A9A9A9; -fx-border-radius: 5px; -fx-padding: 5px;");

        // Password Field
        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Arial", 14));
        passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");
        passwordField.setFont(Font.font("Arial", 14));
        passwordField.setMinHeight(35);
        passwordField.setStyle("-fx-border-color: #A9A9A9; -fx-border-radius: 5px; -fx-padding: 5px;");

        // Login Button
//        Button loginButton = new Button("Login");
        loginButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        loginButton.setTextFill(Color.WHITE);
        loginButton.setStyle("-fx-background-color: #4CAF50; -fx-border-radius: 5px; -fx-padding: 8px 20px;");
        loginButton.setMinWidth(120);
        loginButton.setOnAction(e -> handleLogin(primaryStage));

        // Signup Link
        Hyperlink signupLink = new Hyperlink("Don't have an account? Sign up");
        signupLink.setStyle("-fx-text-fill: #0066cc; -fx-font-size: 13px;");
        signupLink.setOnAction(e -> {
            new Signup(primaryStage);
        });

        // Message Label
        messageLabel = new Label();
        messageLabel.setStyle("-fx-font-size: 14px;");

        // Layout Setup
        HBox usernameBox = new HBox(10, usernameLabel, usernameField);
        usernameBox.setAlignment(Pos.CENTER);

        HBox passwordBox = new HBox(10, passwordLabel, passwordField);
        passwordBox.setAlignment(Pos.CENTER);

        HBox buttonBox = new HBox(loginButton);
        buttonBox.setAlignment(Pos.CENTER);

        HBox signupBox = new HBox(signupLink);
        signupBox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(15, titleLabel, usernameBox, passwordBox, buttonBox, messageLabel, signupBox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(25));
        vbox.setStyle("-fx-background-color: #F5F5F5; -fx-border-radius: 10px;");

        scene = new Scene(vbox, 420, 380);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }

    private void handleLogin(Stage primaryStage) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showMessage("Please enter both username and password", Color.FIREBRICK);
            return;
        }

        loginButton.setDisable(true);

        new Thread(() -> {
            try {
                User user = UserDAO.getUserByEmail(username);

                if (user != null) {
                    String query = "SELECT password FROM users WHERE email = ?";
                    try (Connection conn = DatabaseConnection.getConnectionconnect();
                         PreparedStatement stmt = conn.prepareStatement(query)) {
                        stmt.setString(1, username);
                        ResultSet rs = stmt.executeQuery();

                        if (rs.next()) {
                            String storedPassword = rs.getString("password");

                            if (password.equals(storedPassword)) {
                                // ✅ Everything is OK, proceed to login
                                Platform.runLater(() -> {
                                    UserSession.setUser(user); // مهم جداً
                                    showMessage("Login successful!", Color.GREEN);

                                    new java.util.Timer().schedule(
                                            new java.util.TimerTask() {
                                                @Override
                                                public void run() {
                                                    Platform.runLater(() -> {
                                                        new HomePage(primaryStage);
                                                    });
                                                }
                                            },
                                            1000
                                    );
                                });
                                return;
                            }
                        }
                    }
                }

                // ❌ If we reach here, either user not found or wrong password
                Platform.runLater(() -> {
                    showMessage("Invalid username or password", Color.FIREBRICK);
                });

            } catch (SQLException e) {
                Platform.runLater(() -> {
                    showMessage("Database error occurred", Color.FIREBRICK);
                });
                e.printStackTrace();
            } finally {
                Platform.runLater(() -> {
                    loginButton.setDisable(false);
                });
            }
        }).start();
    }




    private void showMessage(String text, Color color) {
        messageLabel.setText(text);
        messageLabel.setTextFill(color);
    }

    public Scene getScene() {
        return scene;
    }


}