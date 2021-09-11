package com.oopii.wellness_centre4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.Objects;

public class WelcomeController {

    @FXML
    private StackPane doctorPane;

    @FXML
    private Label doctorText;

    @FXML
    private Rectangle doctorCard;

    @FXML
    private StackPane patientPane;

    @FXML
    private Label patientText;

    @FXML
    private Rectangle patientCard;

    @FXML
    private Label welcomeText;

    @FXML
    private Button todocLogInBtn,topatientLogInBtn;


    @FXML
    void todocLogIn(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(WelcomePage.pages[1])));
        Scene doctor_page = new Scene(root, 800, 600);
        WelcomePage.stage.setScene(doctor_page);

    }

    @FXML
    void topatientLogIn(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(WelcomePage.pages[2])));
        Scene patient_page = new Scene(root, 800, 600);
        WelcomePage.stage.setScene(patient_page);

    }


}
