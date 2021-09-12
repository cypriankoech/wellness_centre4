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
    static String previous_page, current_page;
    static Stage stage;
    static String[] pages = {"welcome-page.fxml","login-page.fxml","doctor-page.fxml","patient-page.fxml"} ;
    static Scene welcome_page, login_page,doctor_page,patient_page;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;

        // Initialise welcome page
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(pages[0])));
        Scene welcome_page = new Scene(root, 800, 600);
        this.welcome_page = welcome_page;

        stage.setTitle("Wellness Centre");
        stage.setScene(welcome_page);
        stage.setResizable(false);
        stage.show();

        // Initialise Login page
        Parent login_page_root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(WelcomePage.pages[1])));
        Scene login_page = new Scene(login_page_root, 800, 600);
        this.login_page = login_page;

        // Initialise doctor's page
        Parent doctor_page_root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(WelcomePage.pages[2])));
        Scene doctor_page = new Scene(doctor_page_root, 800, 600);
        this.doctor_page = doctor_page;

        // Initialise patient's page
        Parent patient_page_root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(WelcomePage.pages[3])));
        Scene patient_page = new Scene(patient_page_root, 700, 500);
        this.patient_page = patient_page;

    }

    public static void main(String[] args) {
        launch();
    }
}