package org.example.spring;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginPage {


    public void showLogin(Stage primaryStage,LoginRepository loginRepository) {
        Stage loginStage = new Stage();
        loginStage.setTitle("Login Page");

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        TextField passwordField = new TextField();

        Button loginButton = new Button("Login");
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Save login data to the LoginTable
            LoginData loginData = new LoginData();
            loginData.setUsername(username);
            loginData.setPassword(password);
            loginRepository.save(loginData);

            System.out.println("Username: " + username + ", Password: " + password + " saved to the LoginTable");

            // You may add further logic for authentication here...

            // Close the login page
            loginStage.close();
        });

        VBox loginRoot = new VBox(usernameLabel, usernameField, passwordLabel, passwordField, loginButton);
        Scene loginScene = new Scene(loginRoot, 300, 200);

        loginStage.setScene(loginScene);
        loginStage.show();

        // You may choose to keep the registration page open or close it here...
    }
}
