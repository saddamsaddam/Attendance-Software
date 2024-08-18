package org.example.spring.controller;

import org.example.spring.model.LoginModel;

import org.example.spring.repository.RepositoryManager;
import org.example.spring.view.LoginView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginController {
private  RepositoryManager repositoryManager;
 private LoginModel model;
 private LoginView view;
 private Stage primaryStage;

 public LoginController(RepositoryManager repositoryManager, LoginModel model, LoginView view, Stage primaryStage) {
     this.model = model;
     this.view = view;
     this.primaryStage = primaryStage;
     this.repositoryManager= repositoryManager;
 }

 public Scene createLoginScene() {
     return view.createLoginScene(this);
     
 }

 public void handleLogin(String username, String password) {
     if (model.authenticate(username, password)) {
         // Create home controller
         HomeController homeController = new HomeController(repositoryManager,username, primaryStage);

         // Set the home scene
         primaryStage.setScene(homeController.createHomeScene());
     } else {
         // Handle authentication failure
         System.out.println("Authentication failed.");
     }
 }
 private Scene createHomeScene(String username) {
     // Components for the home scene
     Label welcomeLabel = new Label("Welcome, " + username + "!");

     Button logoutButton = new Button("Logout");
     logoutButton.setOnAction(e -> primaryStage.setScene(createLoginScene()));

     // Layout for the home scene
     VBox homeLayout = new VBox(10);
     homeLayout.getChildren().addAll(welcomeLabel, logoutButton);

     // Create the home scene
     return new Scene(homeLayout, 300, 200);
 }
}
