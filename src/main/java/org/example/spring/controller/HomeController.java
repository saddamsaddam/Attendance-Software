// HomeController.java
package org.example.spring.controller;

import org.example.spring.model.LoginModel;

import org.example.spring.repository.RepositoryManager;
import org.example.spring.view.HomepageView;
import org.example.spring.view.LoginView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeController {
private RepositoryManager repositoryManager;
    private String username;
    private Stage primaryStage;

    public HomeController(RepositoryManager repositoryManager, String username, Stage primaryStage) {
        this.username = username;
        this.primaryStage = primaryStage;
        this.repositoryManager=repositoryManager;
    }

    public Scene createHomeScene() {
    	
    	HomepageView HomepageView=new HomepageView(repositoryManager);
  
        // Create the home scene
       return HomepageView.createHomepageScene(primaryStage);
       // return null;
        
        
    }

    private void handleLogout() {
        // Implement any logout logic here (e.g., clearing session data)
        // Switch back to the login scene
        LoginController loginController = new LoginController(repositoryManager,new LoginModel(), new LoginView(), primaryStage);
        primaryStage.setScene(loginController.createLoginScene());
    }
}
