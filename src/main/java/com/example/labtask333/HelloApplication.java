package com.example.labtask333;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class HelloApplication extends Application {

    private Map<String, String> userCredentials = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {
        loadUserData();
        Image image = new Image(getClass().getResource("/Modern_Gaming_Cover_YouTube_Channel_Art-removebg-preview.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(400);
        imageView.setFitWidth(2000);
        imageView.setPreserveRatio(true);

        Label usernameLabel = new Label("User Name:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        TextField textField = new TextField();
        Button loginButton = new Button("Login");
        Button exitButton = new Button("Exit");
        Label notificationLabel = new Label();

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(15));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(usernameLabel, 0, 1);
        gridPane.add(usernameField, 1, 1);
        gridPane.add(passwordLabel, 0, 2);
        gridPane.add(textField, 1, 2);

        HBox buttonBox = new HBox(10, loginButton, exitButton);
        buttonBox.setAlignment(Pos.CENTER);
        gridPane.add(buttonBox, 0, 3, 2, 1);

        gridPane.add(notificationLabel, 0, 4, 2, 1);

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = textField.getText();
            if (validateCredentials(username, password)) {
                showUserTab(username);
            } else {
                notificationLabel.setText("Incorrect username or password!");
            }
        });

        exitButton.setOnAction(e -> primaryStage.close());

        loginButton.setStyle("-fx-background-color: #184e81; -fx-text-fill: white; -fx-font-size: 20px; -fx-padding: 10px;");
        exitButton.setStyle("-fx-background-color: #656262; -fx-text-fill: white; -fx-font-size: 20px; -fx-padding: 10px;");
        usernameLabel.setStyle("-fx-font-size: 20px");
        passwordLabel.setStyle("-fx-font-size: 20px");

        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.getChildren().addAll(imageView, gridPane);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(mainLayout);

        Scene scene = new Scene(borderPane, 650, 400);
        primaryStage.setTitle("Login Form");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean validateCredentials(String username, String password) {
        return userCredentials.getOrDefault(username, "").equals(password);
    }

    private void showUserTab(String username) {
        Stage userStage = new Stage();
        Label welcomeLabel = new Label("Welcome, " + username + "!");
        Scene scene = new Scene(welcomeLabel, 250, 100);
        userStage.setTitle("User Panel");
        userStage.setScene(scene);
        userStage.show();
    }

    private void loadUserData() {
        File userFile = new File("UsersData.txt");
        if (!userFile.exists()) {
            System.err.println("User data file not found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    userCredentials.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading user data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
