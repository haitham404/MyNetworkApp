package com.example.mynetworkapp;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class AfterSearch {
    private VBox mainContainer = new VBox(20);
    private Scene scene;

    public AfterSearch(Stage primaryStage, List<User> users) {
        mainContainer.setStyle("-fx-padding: 20; -fx-background-color: #f5f5f5;");

        try {
            for (User user : users) {
                mainContainer.getChildren().add(handleUser(user));
            }
        } catch (SQLException e) {
            // Handle database errors gracefully
            Label errorLabel = new Label("Error loading user data: " + e.getMessage());
            errorLabel.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
            mainContainer.getChildren().add(errorLabel);
        }
        // Set the scene
        scene = new Scene(mainContainer, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Search Results");
        primaryStage.show();
    }

    public VBox getMainContainer() {
        return mainContainer;
    }

    private VBox handleUser(User user) throws SQLException {
        // Create user card container
        VBox userCard = new VBox(10);
        userCard.setStyle("-fx-padding: 15; " +
                "-fx-background-color: white; " +
                "-fx-background-radius: 8; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 0);");

        // User name with larger font
        Label nameLabel = new Label(user.getName());
        nameLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // Email label
        Label emailLabel = new Label("✉ " + user.getEmail());
        emailLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #7f8c8d;");

        // Social links section
        List<String> socialLinks = UserDAO.getsociallink(user.getId());
        VBox linksBox = new VBox(5);
        if (!socialLinks.isEmpty()) {
            Label linksHeader = new Label("Social Links:");
            linksHeader.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
            linksBox.getChildren().add(linksHeader);

            for (String link : socialLinks) {
                Label linkLabel = new Label("• " + link);
                linkLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #3498db;");
                linksBox.getChildren().add(linkLabel);
            }
        }

        // Skills section
        List<String> skills = UserDAO.getskills(user.getId());
        VBox skillsBox = new VBox(5);
        if (!skills.isEmpty()) {
            Label skillsHeader = new Label("Skills:");
            skillsHeader.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
            skillsBox.getChildren().add(skillsHeader);

            for (String skill : skills) {
                Label skillLabel = new Label("• " + skill);
                skillLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #27ae60;");
                skillsBox.getChildren().add(skillLabel);
            }
        }

        // Add all components to the user card
        userCard.getChildren().addAll(nameLabel, emailLabel, linksBox, skillsBox);
        return userCard;
    }
}