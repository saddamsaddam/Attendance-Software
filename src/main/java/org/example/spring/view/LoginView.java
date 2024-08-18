// LoginView.java (View)
package org.example.spring.view;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.example.spring.controller.LoginController;

import java.awt.*;

public class LoginView {

    public Scene createLoginScene(LoginController controller) {
        // Components for the login scene
        Label titleLabel = new Label("Login");
        titleLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        setPercentageWidth(usernameField, 0.3);

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        setPercentageWidth(passwordField, 0.3);

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> controller.handleLogin(usernameField.getText(), passwordField.getText()));
        loginButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        // Layout for the login scene
        VBox loginLayout = new VBox(20);
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.setPadding(new Insets(20));
        loginLayout.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        loginLayout.getChildren().addAll(titleLabel, usernameLabel, usernameField, passwordLabel, passwordField, loginButton);

        // Check if the environment is headless
        if (GraphicsEnvironment.isHeadless()) {
            // Set default scene size (modify these values as needed)
            return new Scene(loginLayout, 1200, 600);
        } else {
            // Get the screen size if the environment is not headless
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            return new Scene(loginLayout, screenSize.getWidth() - 70, screenSize.getHeight() - 70);
        }
    }

    private void setPercentageWidth(Region region, double percentage) {
	    region.setMaxWidth(Double.MAX_VALUE);
	    region.setMinWidth(Region.USE_PREF_SIZE);
	    region.setPrefWidth(Region.USE_COMPUTED_SIZE);
	    region.setMaxWidth(Region.USE_PREF_SIZE);

	    // Bind the width only if the region has a non-null parent
	    region.widthProperty().addListener((observable, oldValue, newValue) -> {
	        if (region.getParent() != null && region.getParent().getScene() != null) {
	            region.prefWidthProperty().bind(Bindings.multiply(region.getParent().getScene().widthProperty(), percentage));
	        }
	    });
	}
}
