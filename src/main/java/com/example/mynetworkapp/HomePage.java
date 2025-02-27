package com.example.mynetworkapp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

public class HomePage {
    private Set<String> buttons = new HashSet<>();

    public HomePage(Stage stage) {
        // Create the sidebar
        VBox sidebar = new VBox(10); // Vertical box with 10px spacing between elements
        sidebar.setMinWidth(60);
        sidebar.setMaxWidth(60);
        sidebar.setStyle("-fx-background-color: #4CAF50;"); // Green background
        sidebar.setAlignment(Pos.TOP_CENTER); // Align content to the top center
        sidebar.setPadding(new Insets(10)); // Add padding around the sidebar


        Image profileImage = new Image("https://cdn-icons-png.flaticon.com/512/3135/3135715.png");
        ImageView profileView = new ImageView(profileImage);
        profileView.setFitWidth(40);
        profileView.setFitHeight(40);

        sidebar.getChildren().add(profileView);

        // Create buttons for the content area
        Button button = generateButton("Front end: Angular");
        Button button2 = generateButton("Front end: React");
        Button button3 = generateButton("Back end: Spring");
        Button button4 = generateButton("Back end: .Net");
        Button button5 = generateButton("Math: Linear Algebra");
        Button button6 = generateButton("Math: Calculus");
        Button button7 = generateButton("Math: Probability and Statistics");

        // Create a search button
        Button searchButton = new Button("Search");
        searchButton.setFont(Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
        searchButton.setTextFill(Color.WHITE);
        searchButton.setStyle("-fx-background-color: #4CAF50; -fx-border-radius: 5px; -fx-padding: 5px 10px;");
        searchButton.setMinWidth(150);
        searchButton.setPadding(new Insets(20));

        // Arrange buttons in horizontal boxes
        HBox box = new HBox(20, button, button2);
        box.setAlignment(Pos.CENTER);
        HBox box2 = new HBox(20, button3, button4);
        box2.setAlignment(Pos.CENTER);
        HBox box3 = new HBox(20, button5, button6, button7);
        box3.setAlignment(Pos.CENTER);
        HBox searchBox = new HBox(20, searchButton);
        searchBox.setAlignment(Pos.CENTER);

        // Create the main content area
        VBox contentBox = new VBox(20, box3, box2, box, searchBox);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPadding(new Insets(20));

        // Combine sidebar and content area into the main layout
        HBox mainLayout = new HBox(20, sidebar, contentBox);
        mainLayout.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(contentBox, Priority.ALWAYS); // Allow content area to grow

        // Set up the scene and stage
        Scene scene = new Scene(mainLayout, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Home Page");
        stage.show();
    }

    // Helper method to generate buttons with toggle functionality
    public Button generateButton(String btn) {
        Button button = new Button(btn);
        button.setOnAction(e -> {
            if (buttons.contains(btn)) {
                buttons.remove(btn);
                button.setStyle(""); // Reset style
            } else {
                buttons.add(btn);
                button.setStyle("-fx-background-color: #4CAF50; -fx-border-radius: 5px; -fx-padding: 5px 10px;"); // Green background
            }
        });
        return button;
    }
}