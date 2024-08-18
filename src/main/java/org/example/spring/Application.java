
package org.example.spring;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication

public class Application extends javafx.application.Application {

    private ConfigurableApplicationContext context;

    public static void main(String[] args) {
        launch(Application.class, args);
    }

    @Override
    public void init() {
        this.context = SpringApplication.run(Application.class);
    }

    @Override
    public void start(Stage primaryStage) {
        JavaFxController javaFxController = context.getBean(JavaFxController.class);
        javaFxController.initialize(primaryStage);
    }

    @Override
    public void stop() {
        context.stop();
        Platform.exit();
    }
}
