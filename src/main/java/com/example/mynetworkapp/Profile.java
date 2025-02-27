package com.example.mynetworkapp;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Profile {

    private TextField nameField = new TextField();
    private TextField emailField = new TextField();
    private ListView<String> skillsList = new ListView<>();
    private TextField linkedinField = new TextField();
    private TextField facebookField = new TextField();

    public Profile(Stage primaryStage) {
        primaryStage.setTitle("MyNetwork - Profile");

        VBox mainContainer = new VBox(20);
        mainContainer.setPadding(new Insets(20));

        // Name field
//        HBox nameBox = new HBox(10, new Label("Name:"), nameField);

        // Email field
//        HBox emailBox = new HBox(10, new Label("Email:"), emailField);

        // Skills
        Label skillsLabel = new Label("Skills:");
        skillsList.getItems().addAll(
                "Math: Linear Algebra",
                "Math: Calculus",
                "Math: Probability and Statistics",
                "Back end: Spring",
                "Back end: .Net",
                "Front end: Angular",
                "Front end: React"
        );
        skillsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        skillsList.setPrefHeight(100);


        // Social Media
        Label socialMediaLabel = new Label("Social Media:");
        linkedinField.setPromptText("Add LinkedIn URL");
        facebookField.setPromptText("Add Facebook URL");

        // Save button
        Button saveBtn = new Button("Save Profile");

        saveBtn.setOnAction(e -> {
            try {
                saveProfile();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        mainContainer.getChildren().addAll(
                skillsLabel, skillsList,
                socialMediaLabel, linkedinField, facebookField,
                saveBtn
        );

        Scene scene = new Scene(mainContainer, 400, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void saveProfile() throws SQLException {
        User currentUser = UserSession.getInstance().getUser();
        if (currentUser == null) {
            System.out.println("No user is currently logged in.");
            return;
        }

        String email = currentUser.getEmail();

        String linkedin = linkedinField.getText();
        String facebook = facebookField.getText();
        List<String> selectedSkills = skillsList.getSelectionModel().getSelectedItems();

        // Step 1: get existing user
        User user = UserDAO.getUserByEmail(email);
        if (user == null) {
            System.out.println("User not found. Cannot update profile.");
            return;
        }

        int userId = user.getId();

        // Step 2: update user_skills
        UserDAO.clearUserSkills(userId);
        for (String skill : selectedSkills) {
            int skillId = UserDAO.getSkillIdByName(skill);
            if (skillId != -1) {
                UserDAO.insertUserSkill(userId, skillId);
            }
        }

        // Step 3: update social_links
        UserDAO.clearUserSocialLinks(userId);
        if (!linkedin.isEmpty()) {
            UserDAO.insertSocialLink(userId, "LinkedIn", linkedin);
        }
        if (!facebook.isEmpty()) {
            UserDAO.insertSocialLink(userId, "Facebook", facebook);
        }

        System.out.println("Profile updated successfully!");
    }

}
