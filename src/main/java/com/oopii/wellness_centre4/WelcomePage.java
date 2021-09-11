package com.oopii.wellness_centre4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class WelcomePage extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        String[] pages = {"doctor-page.fxml","welcome-view.fxml"} ;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(pages[0])));
        Scene scene = new Scene(root, 800, 600);



        stage.setTitle("Wellness Centre");
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}