package com.oopii.wellness_centre4;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button loginBtn;

    @FXML
    void logIn(ActionEvent event) throws IOException {
        if (WelcomePage.current_page == "docLogin") {
            WelcomePage.stage.setScene(WelcomePage.doctor_page);

        } else if (WelcomePage.current_page == "patientLogin") {
            WelcomePage.stage.setScene(WelcomePage.patient_page);


        }
    }

    @FXML
    void initialize() {

    }
}
