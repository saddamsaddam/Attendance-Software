package org.example.spring;

import javafx.stage.Stage;
import org.example.spring.controller.LoginController;
import org.example.spring.model.LoginModel;
import org.example.spring.repository.RepositoryManager;
import org.example.spring.view.LoginView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class JavaFxController {
    private final RepositoryManager repositoryManager;

    @Autowired
    public JavaFxController(RepositoryManager repositoryManager) {
        this.repositoryManager = repositoryManager;
    }

    public void initialize(Stage primaryStage) {
        primaryStage.setTitle("Registration Form");
        LoginModel loginModel = new LoginModel();
        LoginView loginView = new LoginView();
        LoginController loginController = new LoginController(repositoryManager,loginModel, loginView, primaryStage);

        // Set the login scene
        primaryStage.setScene(loginController.createLoginScene());
        primaryStage.show();
    }

}
