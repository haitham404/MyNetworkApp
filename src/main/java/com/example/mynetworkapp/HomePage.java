package com.example.mynetworkapp;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class HomePage {
    private Set<String> selectedButtons = new HashSet<>();
    private Scene scene;

    public HomePage(Stage stage) {
        // Create sidebar with profile icon
        VBox sidebar = createSidebar(stage);

        // Create main content area
        VBox contentBox = createContentArea(stage);

        // Combine sidebar and content
        HBox mainLayout = new HBox(sidebar, contentBox);
        HBox.setHgrow(contentBox, Priority.ALWAYS);

        scene = new Scene(mainLayout, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Home Page");
        stage.show();
    }

    private VBox createSidebar(Stage stage) {
        VBox sidebar = new VBox(10);
        sidebar.setMinWidth(60);
        sidebar.setMaxWidth(60);
        sidebar.setStyle("-fx-background-color: #4CAF50;");
        sidebar.setAlignment(Pos.TOP_CENTER);
        sidebar.setPadding(new Insets(10));

        // Profile icon
        ImageView profileView = new ImageView(
                new Image("https://cdn-icons-png.flaticon.com/512/3135/3135715.png")
        );
        profileView.setFitWidth(40);
        profileView.setFitHeight(40);
        profileView.setOnMouseClicked(e -> new Profile(stage));

        sidebar.getChildren().add(profileView);
        return sidebar;
    }

    private VBox createContentArea(Stage stage) {
        VBox contentBox = new VBox(20);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPadding(new Insets(20));

        // Math category buttons
        HBox mathBox = new HBox(20,
                createCategoryButton("Math: Linear Algebra"),
                createCategoryButton("Math: Calculus"),
                createCategoryButton("Math: Probability and Statistics")
        );
        mathBox.setAlignment(Pos.CENTER);

        // Backend category buttons
        HBox backendBox = new HBox(20,
                createCategoryButton("Back end: Spring"),
                createCategoryButton("Back end: .Net")
        );
        backendBox.setAlignment(Pos.CENTER);

        // Frontend category buttons
        HBox frontendBox = new HBox(20,
                createCategoryButton("Front end: Angular"),
                createCategoryButton("Front end: React")
        );
        frontendBox.setAlignment(Pos.CENTER);

//        // Search area
//        TextField searchField = new TextField();
//        searchField.setPromptText("Search for skills...");
//        searchField.setMaxWidth(300);

        Button searchButton = new Button("Search");
        searchButton.setStyle(getButtonStyle(true));
        List<String> skills = new ArrayList<>(selectedButtons);
        AtomicReference<List<User>> users = new AtomicReference<>(new ArrayList<>());

        searchButton.setOnAction(e->
        {
            try {
                users.set(UserDAO.findUsersWithSkills(skills));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            new AfterSearch(stage, users.get());
        });


        HBox searchBox = new HBox(searchButton);
        searchBox.setAlignment(Pos.CENTER);

        contentBox.getChildren().addAll(
                mathBox,
                backendBox,
                frontendBox,
                searchBox
        );

        return contentBox;
    }

    private Button createCategoryButton(String text) {
        Button button = new Button(text);
        button.setOnAction(e -> toggleButtonSelection(button));
        button.setStyle(getButtonStyle(false));
        return button;
    }

    private void toggleButtonSelection(Button button) {
        String buttonText = button.getText();
        if (selectedButtons.contains(buttonText)) {
            selectedButtons.remove(buttonText);
            button.setStyle(getButtonStyle(false));
        } else {
            selectedButtons.add(buttonText);
            button.setStyle(getButtonStyle(true));
        }
    }

    private String getButtonStyle(boolean isSelected) {
        String baseStyle = "-fx-border-radius: 5px; -fx-padding: 5px 10px;";
        if (isSelected) {
            return baseStyle + "-fx-background-color: #4CAF50; -fx-text-fill: white;";
        }
        return baseStyle + "-fx-background-color: #f0f0f0;";
    }

    public Scene getScene() {
        return scene;
    }
}